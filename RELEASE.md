# Cribarth Music Player – Release Notes

## v1.0.10 – Unified Cross-Platform Build

### ✨ Features
- Added Windows `.exe` packaging with NSIS installer
- Provided multi-resolution `.ico` file for Windows branding

### 🔧 Improvements
- Created unified GitHub Actions workflow to build `.deb` and `.exe` on tag
- Enabled automatic publishing to GitHub Releases via `GH_TOKEN`
- Removed previous `build-deb.yml` in favour of integrated pipeline

### 📦 Release Assets
- `Cribarth-Music-Player_1.0.10_amd64.deb`
- `Cribarth Music Player Setup 1.0.10.exe`

---

## v1.0.9 – Initial Linux Release

### ✨ Features
- First public `.deb` package
- Electron wrapper for Music Assistant's "This Device" player

### 🛠 Platform
- Linux only
- Manually uploaded via GitHub Actions artifacts
