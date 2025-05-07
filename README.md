# Cribarth Music Player

A lightweight Electron wrapper for Home Assistant's [Music Assistant](https://music-assistant.github.io) "This Device" player.

## Features

- Persistent, non-browser playback target
- Uses your Home Assistant instance as the source
- Integrates cleanly with Music Assistant player selection

## Installation

### From .deb

Download the latest `.deb` release from the [Releases](https://github.com/glanyrafon01/cribarth-music-player/releases) page:

```bash
sudo apt install ./cribarth-music-player_1.0.0_amd64.deb
```

Then launch from your app menu or run:

```bash
cribarth-music-player
```

## Development

```bash
npm install
npm start
```

## Packaging

```bash
npm run package
```

## License

MIT © Justin Horrell, Cribarth Consulting
