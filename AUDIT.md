# Cribarth Music Player - Code Audit

Date: 2026-01-23
Scope: static review of main process, preload, renderer HTML, and package metadata.

## Summary

The app is a small Electron wrapper with a minimal attack surface, but it loads a remote web UI and bridges a small amount of IPC from renderer to main. The biggest risks are around message validation in the preload bridge and missing hardening for the initial local page. No high severity issues were found.

Severity scale: High, Medium, Low, Info.

## Findings

1) Message bridge accepts unvalidated postMessage input (Medium)
Location: `preload.js`
Impact: Any loaded page can send `set-music-assistant-url` and overwrite the stored URL, including remote content after navigation.
Recommendation:
- Validate `event.source === window` and only accept messages when the current URL is the local `first-run.html` page.
- Consider using `contextBridge.exposeInMainWorld` to provide a narrowly scoped API instead of `postMessage`.

2) Renderer uses `require('electron')` with nodeIntegration disabled (Low)
Location: `first-run.html`
Impact: `require` throws in the renderer, leaving a console error and a dead redirect listener.
Recommendation:
- Remove the direct `require('electron')` usage and rely on the preload bridge for redirects.
- Alternatively, expose a safe `onRedirect` handler via `contextBridge`.

3) No Content Security Policy on the local first-run page (Low)
Location: `first-run.html`
Impact: The page allows inline script and styles with no CSP protection.
Recommendation:
- Add a CSP meta tag and move scripts to the preload or a separate file to allow a stricter policy.

4) URL validation is minimal and allows non-TLS endpoints (Low)
Location: `main.js`, `first-run.html`
Impact: Any http(s) URL is accepted and persisted without additional checks.
Recommendation:
- If feasible, prefer `https://` or allowlist known hosts/paths.
- Reject malformed URLs using `new URL()` parsing instead of regex.

5) Unused dialog prompt helper (Info)
Location: `main.js`
Impact: `promptForMusicAssistantUrl` is unused and references unsupported dialog input.
Recommendation:
- Remove the helper or replace it with a supported prompt mechanism.

## Hardening Suggestions

- Set `sandbox: true` on the `BrowserWindow` if it does not break the Music Assistant UI.
- Keep Electron updated; run `npm audit` periodically and review advisories.
- Consider enabling auto-updates or at least documenting upgrade cadence.

## Notes

- No production dependencies are present; only `electron` and `electron-builder` are used in dev.
- The app stores the Music Assistant URL in `config.json` under `app.getPath('userData')` without encryption. This is typical for desktop apps, but document it if privacy requirements apply.
