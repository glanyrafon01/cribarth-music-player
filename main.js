const { app, BrowserWindow, Menu, ipcMain, shell } = require('electron');
const path = require('path');
const fs = require('fs');

const CONFIG_PATH = path.join(app.getPath('userData'), 'config.json');

// Store reference to the main window
let mainWindow = null;
let currentConfig = {
  serverIp: null,
  musicAssistantUrl: null,
  language: 'en'
};

function loadConfig() {
  if (fs.existsSync(CONFIG_PATH)) {
    try {
      const data = JSON.parse(fs.readFileSync(CONFIG_PATH, 'utf-8'));
      currentConfig = { ...currentConfig, ...data };
      return true;
    } catch (e) {
      console.error('Error reading config:', e);
    }
  }
  return false;
}

function saveConfig(config) {
  try {
    fs.writeFileSync(CONFIG_PATH, JSON.stringify(config, null, 2));
    currentConfig = config;
    return true;
  } catch (e) {
    console.error('Error saving config:', e);
    return false;
  }
}

function connectToMusicAssistant() {
  if (mainWindow && currentConfig.musicAssistantUrl) {
    mainWindow.loadURL(currentConfig.musicAssistantUrl).catch(err => {
      console.error('Failed to load Music Assistant URL:', err);
      showSettings();
    });
  } else {
    showSettings();
  }
}

function showSettings() {
  if (mainWindow) {
    mainWindow.loadFile('settings.html');
  }
}

function createMenu() {
  const isCy = currentConfig.language === 'cy';

  const template = [
    {
      label: isCy ? 'Ffeil' : 'File',
      submenu: [
        {
          label: isCy ? 'Gosodiadau / Newid Gweinydd' : 'Settings / Change Server',
          click: () => showSettings()
        },
        { type: 'separator' },
        { role: 'quit', label: isCy ? 'Gadael' : 'Quit' }
      ]
    },
    {
      label: isCy ? 'Golwg' : 'View',
      submenu: [
        { role: 'reload', label: isCy ? 'Ail-lwytho' : 'Reload' },
        { role: 'forceReload', label: isCy ? 'Gorfodi Ail-lwytho' : 'Force Reload' },
        { role: 'toggleDevTools', label: isCy ? 'Offer Datblygu' : 'Toggle Developer Tools' },
        { type: 'separator' },
        { role: 'togglefullscreen', label: isCy ? 'Sgrin Lawn' : 'Toggle Full Screen' }
      ]
    },
    {
      label: 'Help',
      submenu: [
        {
          label: isCy ? 'Amdanom ni' : 'About',
          click: async () => {
            await shell.openExternal('https://github.com/glanyrafon01/cribarth-music-player');
          }
        }
      ]
    }
  ];

  const menu = Menu.buildFromTemplate(template);
  Menu.setApplicationMenu(menu);
}

async function createWindow() {
  mainWindow = new BrowserWindow({
    width: 1024,
    height: 768,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      nodeIntegration: false,
      contextIsolation: true,
    },
    title: 'Cribarth Music Player',
  });

  loadConfig();
  createMenu();

  if (currentConfig.musicAssistantUrl) {
    connectToMusicAssistant();
  } else {
    showSettings();
  }

  // Handle IPC from settings.html
  ipcMain.on('save-settings', (event, data) => {
    saveConfig(data);
    createMenu(); // Rebuild menu to update language if changed
    connectToMusicAssistant();
  });

  ipcMain.on('request-settings', (event) => {
    event.reply('load-settings', currentConfig);
  });
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
