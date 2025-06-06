const { contextBridge, ipcRenderer } = require('electron');

window.addEventListener('DOMContentLoaded', () => {
  // Listen for postMessage from first-run.html
  window.addEventListener('message', (event) => {
    if (event.data && event.data.type === 'set-music-assistant-url') {
      ipcRenderer.send('set-music-assistant-url', event.data.url);
    }
  });

  // Listen for redirect from main process
  ipcRenderer.on('redirect', (event, url) => {
    window.location = url;
  });
});
