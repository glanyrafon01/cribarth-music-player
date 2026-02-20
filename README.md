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
- [Android Development Guide](ANDROID_GUIDE.md)
- [Audit Report](AUDIT.md)

## 📦 Releases

See [RELEASE.md](./RELEASE.md) for the changelog and version history.

## 📱 Android App

A native Android implementation is available in the `android-app` directory. This provides the same functionality as the desktop version but optimized for mobile devices.

### Android App Features

- **Native Kotlin implementation** for better performance
- **Bilingual UI** with English and Welsh support
- **WebView integration** to display Music Assistant
- **Configuration persistence** using Android SharedPreferences
- **Responsive design** for various screen sizes

### Getting Started with Android Development

See [ANDROID_GUIDE.md](ANDROID_GUIDE.md) for comprehensive instructions on:
- Setting up the Android project
- Building and running the app
- Testing and debugging
- Deploying to Google Play

### Android Project Structure

```
android-app/
├── build.gradle          # Root build configuration
├── settings.gradle       # Project settings
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml  # App manifest
│   │   │   ├── java/                # Kotlin source code
│   │   │   └── res/                 # Resources (layouts, strings, etc.)
│   └── build.gradle       # App build configuration
└── README.md             # Android-specific documentation
```

## License

MIT © Justin Horrell, Cribarth Consulting
