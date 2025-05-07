# Cribarth Music Player – Build Instructions

## Overview

This document describes how to build the `.deb` package for the Cribarth Music Player Electron app using `electron-builder`.

---

## Prerequisites

- Node.js 20
- npm
- ImageMagick (for icon resizing)

```bash
sudo apt install imagemagick
```

---

## One-Time Setup

```bash
git clone https://github.com/glanyrafon01/cribarth-music-player.git
cd cribarth-music-player
npm install
chmod +x generate-icons.sh
```

---

## Steps to Build

1. **Update `package.json` version**
   - Increment `"version"` field (e.g. `"1.0.9"`)

2. **Generate multi-size icons**
   ```bash
   ./generate-icons.sh
   ```

3. **Commit and tag the release**
   ```bash
   git add package.json
   git commit -m "Bump version to 1.0.9"
   git tag v1.0.9
   git push origin main --tags
   ```

4. **GitHub Actions**
   - Runs automatically on tag
   - Produces `.deb` file and attaches to draft release

---

## Notes

- Icon sizes are generated from `build/icon.png` into `build/icons/`
- Package name and executable are defined by `name` and `executableName` in `package.json`
- Releases are manual unless auto-published
