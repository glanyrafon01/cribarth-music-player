# Cribarth Music Player

A lightweight Electron wrapper for [Music Assistant](https://music-assistant.github.io) "This Device" player.

## Features

- **Bilingual UI**: Fully supports English and Welsh (Cymraeg).
- **Direct Connection**: Connects directly to Music Assistant on port 8095.
- **Persistent Playback**: Non-browser playback target that integrates cleanly with Music Assistant.
- **Easy Configuration**: Change your server IP at any time via the application menu.
- **APT Repository**: Automatic updates on Linux via our hosted repository.

## Installation

### Recommended: APT Repository (Linux)

To enable automatic updates, add our repository to your system:

```bash
curl -sL https://glanyrafon01.github.io/cribarth-music-player/setup-repo.sh | sudo bash
```

Then install the player:

```bash
sudo apt update
sudo apt install cribarth-music-player
```

### Manual Installation

Download the latest `.deb` or `.exe` release from the [Releases](https://github.com/glanyrafon01/cribarth-music-player/releases) page.

For Linux manual installation:
```bash
sudo apt install ./cribarth-music-player_1.2.1_amd64.deb
```

## Development

```bash
npm install
npm start
```

## Documentation

- [Installing Guide](docs/INSTALLING.md)
- [First Run Setup](docs/FIRST_RUN.md)
- [How It Works](docs/HOW_IT_WORKS.md)
- [Audit Report](AUDIT.md)

## 📦 Releases

See [RELEASE.md](./RELEASE.md) for the changelog and version history.

## License

MIT © Justin Horrell, Cribarth Consulting
