# Agent Guide — Cribarth Music Player (Desktop App)

This repository is an Electron-based desktop application (typically Node.js + a
frontend framework). Work includes code, packaging, documentation, and CI.

This repository may be public. Do not include secrets, credentials, private
keys, personal data, or internal strategy in any generated files.

The player is intended to provide a persistent wrapper for the Music Assistant add on provided by Home Assistant.

---

## Primary Intent

Default agent intent is to:
- audit and document the codebase
- improve maintainability with small, safe changes
- keep builds reproducible
- avoid breaking packaging/release pipelines

Prefer documentation and plans before implementation.

---

## Scope and Permissions

Allowed by default:
- Read all repository files
- Create and edit documentation under `/docs`
- Propose changes as a plan with file lists and risks
- Make small, isolated fixes (typos, dead code removal, small refactors) only
  when explicitly requested

Avoid unless explicitly instructed:
- Dependency upgrades (`package.json`, lockfiles)
- Large refactors or architectural rewrites
- Changes to `.github/workflows` or release/publish automation
- Changes to code signing, notarization, or publishing credentials
- Broad formatting-only changes that create noisy diffs

Never:
- Add or expose secrets/tokens/keys
- Modify or commit build artifacts (e.g. `dist/`, `out/`, packaged installers)
  unless the workflow explicitly requires it
- Introduce new telemetry or data collection without explicit instruction

---

## Workflow Rules

1. **Plan first**
   - Propose approach, affected files, and risks before edits.
2. **Small diffs**
   - One concern per change; avoid sweeping edits.
3. **Reproducible builds**
   - Prefer deterministic steps (`npm ci` over `npm install` when lockfile exists).
4. **Validate changes**
   - Run the relevant checks (lint/test/build) after changes.
5. **Document intent**
   - Update documentation when behavior or setup changes.

---

## Commands and Validation

Use the repo’s existing scripts. Common patterns include:

- Check runtime:
  - `node --version`
  - `npm --version`
- Install dependencies (prefer lockfile-driven):
  - `npm ci`
- Development run (examples; use what exists in package.json):
  - `npm run dev`
  - `npm start`
- Lint / format:
  - `npm run lint`
  - `npm run format`
- Tests (if present):
  - `npm test`
  - `npm run test`
- Build / package (if present):
  - `npm run build`
  - `npm run package`
  - `npm run make`
  - `npm run dist`

If scripts are not present, document what exists rather than inventing a new
toolchain.

---

## Repository Structure (typical)

Actual structure varies. Common directories:
- `src/` — application source
- `electron/` or `main/` — main process code
- `renderer/` — UI/renderer code (or within `src/`)
- `preload/` — preload scripts for IPC boundary
- `assets/` — icons, images
- `docs/` — documentation
- `scripts/` — helper scripts
- `.github/workflows/` — CI and releases
- `dist/`, `out/`, `release/` — build artifacts (do not commit unless required)

Document deviations in `/docs`.

---

## Architecture Conventions (Electron)

When documenting or changing the app, distinguish:
- **Main process**: app lifecycle, windows, native integrations
- **Renderer process**: UI
- **Preload**: secure bridge (IPC exposure)
- **IPC**: message channels between renderer and main

Security rule of thumb:
- Keep Node integration off in the renderer unless required.
- Expose minimal APIs via preload; avoid broad `ipcRenderer` exposure.
- Prefer context isolation where possible.

Do not change security posture without explicit instruction.

---

## Documentation Requirements

Documentation should be written for:
- technically literate non-developers
- contributors who know Git but not Electron internals

Guidelines:
- explain Electron concepts briefly (main/renderer/preload/IPC)
- define acronyms on first use
- include “what it does” before “how it works”
- keep setup instructions copy/paste friendly

Recommended docs outputs (when auditing):
- `docs/overview.md` — what the app is, user-facing behavior
- `docs/architecture.md` — processes, modules, data flow
- `docs/dev-setup.md` — run/debug locally
- `docs/build-and-release.md` — packaging, GitHub Actions summary
- `docs/configuration.md` — env vars, config files
- `docs/known-issues.md` — risks, tech debt, TODOs

---

## Dependency and Lockfile Policy

- Do not upgrade dependencies unless explicitly requested.
- Do not rewrite lockfiles as a side-effect.
- If a dependency issue is discovered, document it and propose options.

---

## Git and CI/CD

- Keep commits focused and readable.
- Do not modify CI/release workflows unless explicitly instructed.
- If you must change workflows, propose a plan and explain impact on releases.

---

## Data, Privacy, and Telemetry

- Do not introduce telemetry, analytics, or data collection.
- Do not add new external network calls without explicit instruction.
- Document any existing data storage (local files, user settings) and how it is
  handled.

---

## Error Handling and Debugging

- Prefer diagnosing via logs, reproducible steps, and small fixes.
- When proposing fixes, include:
  - root cause hypothesis
  - minimal change
  - how to validate

---

## References

- Electron security guidance: https://www.electronjs.org/docs/latest/tutorial/security
- Repository-specific workflow notes may exist under `/docs`
