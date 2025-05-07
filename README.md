# Cribarth Music Player

A lightweight Electron wrapper for Home Assistant's [Music Assistant](https://music-assistant.github.io) "This Device" player.

## Features

- Persistent, non-browser playback target
- Uses your Home Assistant instance as the source
- Integrates cleanly with Music Assistant player selection
- Launchable from system menu with custom icon
- Distributed as a `.deb` package for easy manual install

## Installation

See [docs/INSTALLING.md](docs/INSTALLING.md) for full instructions.

### Quick Install

Download the latest `.deb` release from the [Releases](https://github.com/glanyrafon01/cribarth-music-player/releases) page:

```bash
sudo apt install ./cribarth-music-player_1.0.X_amd64.deb
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

See [docs/BUILD.md](docs/BUILD.md) for full details.

```bash
npm run package
```

## Releasing

See [docs/RELEASING.md](docs/RELEASING.md) for the versioning and tagging workflow.

## License

MIT © Justin Horrell, Cribarth Consulting
