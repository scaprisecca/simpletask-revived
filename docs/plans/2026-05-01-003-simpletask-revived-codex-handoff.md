---
title: codex handoff: simpletask revived rebrand and f-droid prep
type: implementation-handoff
status: ready
date: 2026-05-01
plan_path: docs/plans/2026-05-01-002-simpletask-revived-rebrand-and-fdroid-plan.md
repo_root: /home/agent/dev/simpletasks_app
branch: new-features
---

# codex handoff: simpletask revived rebrand and f-droid prep

## purpose
This note is the execution handoff for Codex.

Use it together with:
- `docs/plans/2026-05-01-002-simpletask-revived-rebrand-and-fdroid-plan.md`

The implementation goal is to rebrand the app/repo as **Simpletask Revived**, prepare a clean fork identity, and get the project into shape for an initial **cloudless-only F-Droid submission** without starting the future website work.

## locked decisions
These are approved. Do not change them unless Scott explicitly says to.

- app name: `Simpletask Revived`
- base application ID: `io.scott.simpletaskrevived`
- keep internal source package/class namespace `nl.mpcjanssen.simpletask` for now unless a concrete blocker forces deeper change
- F-Droid v1 scope: `cloudless` only
- branding scope: text-only rebrand for v1
- temporary canonical repo URL: `https://github.com/scaprisecca/simpletask-android`
- temporary issues URL: `https://github.com/scaprisecca/simpletask-android/issues`
- temporary website/docs URL: use the fork GitHub repo/docs surface, not the original maintainer site
- donation links: remove from user-visible surfaces for now; optional note/comment is fine for future fork-owned support links

## non-goals
Do not do these in this pass:
- do not build the future VPS-hosted website
- do not do a full package/source rename
- do not widen scope into unrelated feature work
- do not create a visual/icon redesign unless a release-blocking contradiction is found

## required implementation posture
- treat this as a **fork identity cleanup**, not a vanity refactor
- prefer editing resource strings, manifests, build config, docs, and runtime URLs over moving code
- preserve internal/historical `Simpletask` references when they are not user-facing and changing them adds risk
- update `CHANGELOG.md` because this is user-facing
- if there is any uncertainty about a public URL or ownership surface, prefer the approved fork URLs above

## highest-priority surfaces to update
1. `app/build.gradle`
   - switch shipped application IDs to the fork-owned namespace
2. flavor labels and core strings
   - `app/src/cloudless/res/values/strings.xml`
   - `app/src/encrypted/res/values/strings.xml`
   - `app/src/nextcloud/res/values/strings.xml`
   - `app/src/webdav/res/values/strings.xml`
   - `app/src/dropbox/res/values/strings.xml`
   - `app/src/main/res/values/strings.xml`
3. repo ownership/docs
   - `README.md`
   - `CONTRIBUTE.md`
   - `CHANGELOG.md`
   - `scripts/build_cloudless.sh`
4. in-app help/runtime link surfaces
   - `app/src/main/assets/*.md`
   - `app/src/main/java/nl/mpcjanssen/simpletask/HelpScreen.kt`
   - `app/src/main/java/nl/mpcjanssen/simpletask/util/Util.kt`
5. onboarding/sample content
   - `defaulttasks.txt`
6. optional release metadata scaffolding
   - add only if done coherently; do not half-create it

## execution checks already completed
Environment/readiness checks passed before handoff:
- repo root: `/home/agent/dev/simpletasks_app`
- current branch: `new-features`
- codex binary present: `codex-cli 0.118.0`
- codex auth status: logged in
- Compound Engineering prompt scaffold present:
  - `~/.codex/prompts/ce-work.md`
  - `~/.codex/skills/ce-work/SKILL.md`

Current repo state when this handoff was prepared:
- repo is **dirty** because planning/handoff docs are uncommitted
- observed untracked file before writing this handoff: `docs/plans/2026-05-01-002-simpletask-revived-rebrand-and-fdroid-plan.md`
- expect this handoff file to also appear as untracked until committed

## exact plan to execute
Primary plan file:
- `docs/plans/2026-05-01-002-simpletask-revived-rebrand-and-fdroid-plan.md`

Preferred Codex launch command:

```bash
codex exec --full-auto -C /home/agent/dev/simpletasks_app '/prompts:ce-work docs/plans/2026-05-01-002-simpletask-revived-rebrand-and-fdroid-plan.md
User approval is granted to proceed on the current branch new-features. Do not ask again whether to proceed on this branch. Execute the plan now.'
```

## required verification before claiming completion
At minimum, after implementation:

1. run targeted cleanup searches for old public-facing references such as:
   - `Simpletask`
   - `Simpletask Cloudless`
   - `Simpletask Nextcloud`
   - `Simpletask WebDAV`
   - `mpcjanssen/simpletask-android`
   - `mpcjanssen.nl`
   - `simpletask-android/issues`
   - `hosted.weblate.org/engage/simpletask`
   - `f-droid.org`
2. distinguish between:
   - must-fix public-facing references
   - acceptable internal/historical references
3. run the preferred cloudless build:

```bash
cd /home/agent/dev/simpletasks_app
./scripts/build_cloudless.sh
```

4. confirm these outcomes:
   - app installs as a distinct package under the new application ID
   - launcher/app label shows `Simpletask Revived` or agreed flavor variant
   - help/support links point to the fork-owned repo/issues surfaces
   - default/sample content no longer markets the original project
   - README/CONTRIBUTE/CHANGELOG are coherent for a maintained fork

## stop-and-ask conditions
Stop instead of guessing if any of these come up:
- a public-facing URL is needed but not derivable from the approved fork URLs above
- application ID changes reveal a hidden runtime/account/provider constraint that requires a bigger identity decision
- release metadata scaffolding would require product-copy decisions not already covered by the plan
- a package/class rename appears necessary instead of optional

## finish-line summary shape
The completion report should include:
- what was changed
- which user-facing old-brand references remain intentionally unchanged, if any
- build/test results
- any F-Droid metadata work added or explicitly deferred
- any follow-up items for the future website/docs hosting phase
