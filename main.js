const { app, BrowserWindow, dialog, ipcMain } = require('electron');
const path = require('path');
const fs = require('fs');

const CONFIG_PATH = path.join(app.getPath('userData'), 'config.json');

function getMusicAssistantUrl() {
  if (fs.existsSync(CONFIG_PATH)) {
    try {
      const config = JSON.parse(fs.readFileSync(CONFIG_PATH, 'utf-8'));
      if (config.musicAssistantUrl) return config.musicAssistantUrl;
    } catch (e) {}
  }
  return null;
}

async function promptForMusicAssistantUrl(win) {
  const { response, checkboxChecked } = await dialog.showMessageBox(win, {
    type: 'info',
    buttons: ['OK'],
    title: 'Music Assistant URL Required',
    message: 'Please enter the URL of your Music Assistant instance (e.g. http://192.168.1.100:8123/d5369777_music_assistant/ingress):',
    detail: '',
    input: true // Not natively supported, will fallback to prompt in renderer if needed
  });
  // Electron's dialog does not support input natively; fallback to prompt in renderer if needed
  return null;
}

async function createWindow() {
  const win = new BrowserWindow({
    width: 1000,
    height: 700,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      nodeIntegration: false,
      contextIsolation: true,
    },
    title: 'Cribarth Music Player',
  });

  let url = getMusicAssistantUrl();
  if (!url) {
    url = process.env.MUSIC_ASSISTANT_URL;
    if (!url) {
      // Use absolute path for packaged app
      const firstRunPath = app.isPackaged
        ? path.join(process.resourcesPath, 'app.asar.unpacked', 'first-run.html')
        : path.join(__dirname, 'first-run.html');
      win.loadFile(firstRunPath);
      // Listen for URL from renderer
      ipcMain.once('set-music-assistant-url', (event, userUrl) => {
        if (userUrl && /^https?:\/\//.test(userUrl)) {
          fs.writeFileSync(CONFIG_PATH, JSON.stringify({ musicAssistantUrl: userUrl }));
          win.webContents.send('redirect', userUrl);
        }
      });
      return;
    }
    fs.writeFileSync(CONFIG_PATH, JSON.stringify({ musicAssistantUrl: url }));
  }
  win.loadURL(url);
}

app.whenReady().then(() => {
  createWindow();

  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  });
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') app.quit();
});
