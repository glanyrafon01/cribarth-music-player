# Cribarth Music Player – Installation Instructions

This document explains how to install the Cribarth Music Player on your own system from GitHub Releases.

---

## Manual Installation

1. **Download the latest `.deb` file**

Go to:
```
https://github.com/glanyrafon01/cribarth-music-player/releases
```

Download the file:
```
cribarth-music-player_1.0.X_amd64.deb
```

2. **Install using APT**

```bash
sudo apt install ./cribarth-music-player_1.0.X_amd64.deb
```

> If you see a warning about sandboxing and `_apt`, move the file to `/tmp` first:
```bash
mv ~/Downloads/cribarth-music-player_1.0.X_amd64.deb /tmp/
sudo apt install /tmp/cribarth-music-player_1.0.X_amd64.deb
```

3. **Launch the app**

- Search for “Cribarth Music Player” in your system menu
- Or run:
```bash
cribarth-music-player
```

---

## Uninstall

```bash
sudo apt remove cribarth-music-player
```

