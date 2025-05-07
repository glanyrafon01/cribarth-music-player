# Cribarth Music Player – Release Process

This guide describes how to publish a new version of the Cribarth Music Player.

---

## Prerequisites

- Ensure the build process is documented in `docs/BUILD.md`
- You are on the `main` branch
- Your local Git is up-to-date with `origin/main`

---

## Steps to Release

1. **Update version**

Edit the `version` field in `package.json`:

```json
"version": "1.0.X"
```

2. **Generate icons**

Run the icon generation script:

```bash
./generate-icons.sh
```

3. **Commit and tag**

```bash
git add package.json
git commit -m "Release v1.0.X"
git tag v1.0.X
git push origin main --tags
```

4. **Wait for GitHub Actions**

- Navigate to GitHub → Actions
- Confirm that the build succeeded and the `.deb` was uploaded to the draft release

5. **Publish the draft release (optional)**

- Go to the "Releases" tab
- Edit the draft
- Click “Publish release” to make it public

---

## Notes

- Releases are draft by default
- The `.deb` includes versioned metadata and the app icon
