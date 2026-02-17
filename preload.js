const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('api', {
  saveSettings: (data) => ipcRenderer.send('save-settings', data),
  requestSettings: () => ipcRenderer.send('request-settings'),
  onLoadSettings: (callback) => ipcRenderer.on('load-settings', (event, ...args) => callback(...args)),
  onRedirect: (callback) => ipcRenderer.on('redirect', (event, ...args) => callback(...args))
});
