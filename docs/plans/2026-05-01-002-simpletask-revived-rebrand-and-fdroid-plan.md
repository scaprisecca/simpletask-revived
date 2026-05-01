---
title: feat: simpletask revived rebrand and f-droid prep plan
type: implementation-plan
status: active
date: 2026-05-01
origin: chat
source_doc: none
related_docs:
  - README.md
  - CONTRIBUTE.md
  - CHANGELOG.md
  - app/build.gradle
  - app/src/main/AndroidManifest.xml
  - app/src/main/assets/index.en.md
---

# simpletask revived rebrand and f-droid prep plan

> **For Hermes / Codex:** Execute this as a clean fork rebrand for F-Droid readiness. Keep scope on app identity, support/documentation ownership, release metadata, and verification. Do **not** start the future website implementation in this pass; only leave the codebase and docs ready for that later phase.

created: 2026-05-01
status: active
source_doc: none
branch_at_planning_time: `new-features`

## objective
- rebrand the app and repo from **Simpletask** to **Simpletask Revived** in user-visible surfaces
- establish this fork as a distinct maintained project with its own package IDs, support URLs, and release metadata
- keep the implementation low-risk by avoiding a full source-package refactor unless technically required
- prepare the project for F-Droid submission with clean ownership language, updated app metadata, and clear verification steps
- leave the documentation content in a shape that can later be moved onto a small app website

## fixed_decisions
- app name: **Simpletask Revived**
- recommendation accepted: **use new application IDs**
- keep the existing Kotlin/Java source package structure (`nl.mpcjanssen.simpletask`) **for now** unless a concrete build/runtime blocker forces deeper changes
- future website work is **out of scope for this implementation pass**

## architecture
This should be treated as a **clean identity fork**, not just a string rename. The implementation should separate:
1. **distribution identity**: application IDs, provider authorities, account types, app labels, release metadata
2. **user-facing product language**: strings, onboarding text, help text, docs, support links, changelog wording
3. **repo/project ownership**: README, contribution policy, issue tracker, release process, future docs hosting references

The safest path is to update distribution identity and user-visible surfaces while leaving internal source packages and class names alone for now. That minimizes regression risk while still producing a proper fork suitable for F-Droid.

## scope
### included
- application ID fork for shipped flavors
- app label and user-visible brand updates
- support/tracker/website URL updates to fork-owned destinations
- repo docs rewrite for fork ownership and current maintenance state
- in-app help/doc updates needed for coherent branding
- sample/default content updates
- review of launcher/store-facing assets and release metadata needs
- changelog entry for the rebrand work
- build and verification steps for the cloudless flavor first

### excluded
- implementing the future website itself
- large visual redesign beyond branding assets needed for release
- full source-package rename from `nl.mpcjanssen.simpletask` to a new namespace
- feature changes unrelated to rebranding/F-Droid prep
- translation-quality perfection across every locale in this pass
- Play Store publishing work

## locked implementation decisions
These are now approved and should be treated as fixed unless Scott explicitly changes them later.

### 1. canonical reverse-domain package prefix
- base application ID: `io.scott.simpletaskrevived`

Implementation note:
- keep flavor suffixes predictable off this base
- do not invent a different namespace during execution

### 2. support URLs
Use interim fallback URLs until the website exists.

Approved rule:
- repo URL: use the fork GitHub repository URL
- issues URL: use the fork GitHub issues URL
- website/docs URL for now: use the fork repo URL or repo-root docs landing surface as the temporary canonical destination
- do not point users to the original maintainer’s site

### 3. flavor strategy for F-Droid v1
- ship `cloudless` only for the first F-Droid submission path

Implementation note:
- keep other flavors coherent where practical, but they must not block the primary cloudless rebrand/release path

### 4. visual branding depth for v1
- use a text-only rebrand for v1

Implementation note:
- review icons/assets for obvious contradiction, but do not expand this into a full art redesign

### 5. donation/support link behavior
- remove donation links for now from user-visible surfaces
- if helpful, leave a code or docs comment noting that a fork-owned support/donation destination can be added later
- do not ship links that send users to the original maintainer’s donation flow

## current_findings
### repo/docs ownership still point to the original project
- `README.md` is still titled `Simpletask`
- `README.md` points at original F-Droid package pages and original Weblate project
- `CONTRIBUTE.md` is written in the original maintainer’s voice
- `scripts/build_cloudless.sh` help text still says `Build the Simpletask cloudless/local Android flavor.`

### distributed app identity still uses the original package IDs
In `app/build.gradle`:
- `nl.mpcjanssen.simpletask`
- `nl.mpcjanssen.simpletask.encrypted`
- `nl.mpcjanssen.simpletask.nextcloud`
- `nl.mpcjanssen.simpletask.webdav`

### user-visible labels still use the old brand
Examples:
- `app/src/cloudless/res/values/strings.xml`
- `app/src/nextcloud/res/values/strings.xml`
- `app/src/webdav/res/values/strings.xml`
- `app/src/dropbox/res/values/strings.xml`
- `app/src/encrypted/res/values/strings.xml`
- many `app/src/main/res/values*/strings.xml` localized files

### in-app help/docs still reference old brand and old ownership URLs
Examples:
- `app/src/main/assets/index.en.md`
- `app/src/main/assets/index.de.md`
- `app/src/main/assets/index.es.md`
- `app/src/main/assets/intents.*.md`
- `app/src/main/assets/versions.*.md`
- `app/src/main/assets/changelog.en.md`

### runtime code still opens original support locations
Examples:
- `app/src/main/java/nl/mpcjanssen/simpletask/HelpScreen.kt`
- `app/src/main/java/nl/mpcjanssen/simpletask/util/Util.kt`
- many `visit_tracker_data` and `visit_website_data` string resources

### sample/onboarding data still references the old project
- `defaulttasks.txt` contains `SIMPLETASK` copy and an old GitHub issues link

### there is no obvious repo-local fastlane/F-Droid metadata structure yet
No repo-local `fastlane/metadata/android/...` or similar store metadata directory was found, so this likely needs to be added if Scott wants store listing content tracked in-repo.

## rebrand_strategy
### preferred fork shape
- keep internal source packages and class names unchanged for now
- change **application IDs**, **user-visible names**, **support URLs**, and **ownership docs**
- keep flavor support coherent, but optimize the release path around cloudless first
- avoid churn in tests and implementation where the old internal class name `Simpletask` is not user-facing

### why this is the right compromise
- avoids a risky source-wide refactor with little user value
- still produces a genuine fork that does not impersonate the original maintainer
- makes F-Droid review and user expectations cleaner
- leaves room for a later internal namespace cleanup if desired

## implementation_units

### 1. lock the new distribution identity and release constants
**Objective:** Define the canonical fork identity in one place before touching strings/docs.

**Files:**
- modify: `app/build.gradle`
- modify: `app/src/main/AndroidManifest.xml`
- modify: `app/src/cloudless/AndroidManifest.xml`
- modify: `app/src/encrypted/AndroidManifest.xml`
- modify: `app/src/nextcloud/AndroidManifest.xml`
- modify: `app/src/webdav/AndroidManifest.xml`
- modify: `app/src/dropbox/AndroidManifest.xml`
- search/review: any flavor-specific resources or code that persist or compare application/account/provider IDs

**Changes:**
- replace shipped `applicationId` values with the new fork-owned IDs
- keep flavor suffixes consistent and predictable
  - example pattern only: `base`, `base.nextcloud`, `base.webdav`, `base.encrypted`
- ensure manifest/provider authority patterns still derive from `${applicationId}` correctly after the change
- review any account-type strings that are part of Android account integration; update them so they do not continue using the original ID namespace
- confirm whether any intent actions or shared filenames should remain stable vs become fork-specific

**Checklist:**
- [ ] base `applicationId` changed to new fork namespace
- [ ] flavor IDs updated consistently
- [ ] provider authorities still resolve correctly
- [ ] account type strings reviewed and updated where needed
- [ ] no release-facing identifier still claims the original maintainer namespace unless intentionally retained for compatibility

**Risks:**
- changing IDs may affect upgrade/coexistence behavior; that is intentional here, but must be acknowledged
- any hardcoded account/provider/action strings missed in code/resources can cause runtime failures

**Acceptance:**
- build outputs install as a distinct forked app
- manifest/provider/account identifiers are internally consistent

---

### 2. update primary app labels and core English branding strings
**Objective:** Make the shipped app visibly present itself as Simpletask Revived.

**Files:**
- modify: `app/src/cloudless/res/values/strings.xml`
- modify: `app/src/encrypted/res/values/strings.xml`
- modify: `app/src/nextcloud/res/values/strings.xml`
- modify: `app/src/webdav/res/values/strings.xml`
- modify: `app/src/dropbox/res/values/strings.xml`
- modify: `app/src/main/res/values/strings.xml`

**Changes:**
- update `app_label` entries to the new brand per flavor
- update `first_title_word`
- update obvious English brand strings such as:
  - share targets
  - calendar/reminder titles
  - onboarding/login copy
  - task-added/share success text
  - support/website/tracker labels and data fields
- review whether flavor names should be shown as:
  - `Simpletask Revived`
  - `Simpletask Revived Nextcloud`
  - `Simpletask Revived WebDAV`
  - etc.
- remove stale references to Play Store-specific “Cloudless” copy unless still relevant

**Checklist:**
- [ ] cloudless label updated
- [ ] nextcloud label updated
- [ ] webdav label updated
- [ ] encrypted label updated if still relevant
- [ ] dropbox label updated if still kept in repo
- [ ] English share/reminder/help strings updated
- [ ] `visit_website_*` and `visit_tracker_*` base English strings updated

**Risks:**
- inconsistent flavor naming creates a messy release surface
- old support copy can survive in secondary strings if only `app_label` is changed

**Acceptance:**
- launcher/app title/help/share surfaces present the new brand in English

---

### 3. audit and update localized brand-bearing strings with pragmatic scope
**Objective:** Prevent the app from looking half-renamed in common localized surfaces.

**Files:**
- modify as needed: `app/src/main/res/values-*/strings.xml`
- likely review targets include locales where brand text appears in:
  - `app_label`
  - `first_title_word`
  - `share_addtask_name`
  - `share_title`
  - `calendar_title`
  - `calendar_disp_name`
  - `task_added`
  - `visit_website_summary`
  - `visit_website_data`
  - `visit_tracker_data`
  - `login_cloudless`
  - `share_task_show_edit_summary`

**Changes:**
- update direct brand mentions from `Simpletask` to `Simpletask Revived`
- update URLs in localized string files to the fork-owned URLs
- do **not** attempt perfect translation rewriting unless needed for coherence
- if a localized string embeds stale old-site or old-tracker URLs, update the URL even if the surrounding translation is untouched

**Checklist:**
- [ ] localized tracker URLs updated everywhere they appear
- [ ] localized website URLs updated everywhere they appear
- [ ] localized app labels reviewed for all shipped flavors
- [ ] obvious brand-bearing user-facing strings updated in major locales present in repo
- [ ] no locale still points users to the original issue tracker by default

**Risks:**
- high churn across many locale files; keep changes targeted to branding/support ownership
- avoid introducing broken XML or encoding errors in translated files

**Acceptance:**
- users in translated locales are routed to the fork’s support channels and see consistent branding in key surfaces

---

### 4. rewrite runtime support/help behavior to point at the fork
**Objective:** Ensure in-app actions take users to the revived project, not the original one.

**Files:**
- modify: `app/src/main/java/nl/mpcjanssen/simpletask/HelpScreen.kt`
- modify: `app/src/main/java/nl/mpcjanssen/simpletask/util/Util.kt`
- modify as needed: string-resource-backed URL fields in `app/src/main/res/values*.xml`

**Changes:**
- change help/menu repo link target from the original GitHub repo to the fork repo
- update any auto-generated issue-link logic so `#123` style references point to the fork’s issues page
- review any donation/support links and either:
  - replace with a valid fork-owned destination, or
  - remove/hide them if not ready yet
- ensure help menu labels still make sense after ownership change

**Checklist:**
- [ ] help menu repo link updated
- [ ] tracker URL logic updated
- [ ] stale donation/support links removed or replaced
- [ ] no in-app route opens the original maintainer’s homepage unless intentionally referenced as project history

**Risks:**
- forgotten hardcoded URLs will make the fork look abandoned or misleading

**Acceptance:**
- all in-app support/help actions resolve to fork-owned destinations

---

### 5. rewrite repo-facing ownership docs and release framing
**Objective:** Make the repository self-describe as an actively maintained fork.

**Files:**
- modify: `README.md`
- modify: `CONTRIBUTE.md`
- modify: `CHANGELOG.md`
- modify: `scripts/build_cloudless.sh`
- optionally create: release/support docs if helpful

**Changes:**
- rewrite `README.md` title, intro, support links, release sections, and F-Droid framing
- explicitly state that this is a maintained fork/revival because the original is no longer maintained
- update or remove original F-Droid package links depending on whether new IDs/listings exist yet
- remove original-maintainer first-person language from `CONTRIBUTE.md`
- replace it with Scott’s fork policy and current contribution expectations
- update script help text to the new project name where user-visible
- add a changelog entry for the rebrand/fork identity work

**Checklist:**
- [ ] README title and intro updated
- [ ] README support and docs links updated
- [ ] README F-Droid messaging updated to the new release path
- [ ] CONTRIBUTE ownership voice rewritten
- [ ] build script help text updated
- [ ] CHANGELOG entry added in same commit as user-facing rebrand work

**Risks:**
- docs that keep speaking as the original maintainer undermine the whole fork
- linking to not-yet-created F-Droid pages should be avoided; use neutral wording until live

**Acceptance:**
- a new contributor can read the repo and immediately understand the fork identity, support path, and maintenance status

---

### 6. update in-app documentation assets enough for coherent release quality
**Objective:** Prevent the built-in help system from contradicting the new brand.

**Files:**
- modify: `app/src/main/assets/index.en.md`
- modify: `app/src/main/assets/index.de.md`
- modify: `app/src/main/assets/index.es.md`
- modify: `app/src/main/assets/intents.en.md`
- modify: `app/src/main/assets/intents.de.md`
- modify: `app/src/main/assets/intents.es.md`
- modify: `app/src/main/assets/versions.en.md`
- modify: `app/src/main/assets/versions.es.md`
- modify: `app/src/main/assets/versions.hu.md`
- modify: `app/src/main/assets/changelog.en.md` only if necessary for branding context, not to rewrite historic entries
- review additional `ui.*`, `listsandtags.*`, `MYN.*`, `script.*`, `extensions.*`, `design.*`, `defertasks.*` files for stale support links or obvious brand contradictions

**Changes:**
- update product naming to `Simpletask Revived` where user-visible and current
- update repo/issue/community/support links to the fork
- remove or rewrite stale “I maintain both versions” / original maintainer statements
- keep historical changelog entries historical; avoid rewriting release history except where headers/intro framing need clarification
- where docs discuss future website/docs hosting, point to the repo docs or a placeholder that can later be swapped cleanly

**Checklist:**
- [ ] English help landing page updated
- [ ] old repo links in help assets updated
- [ ] old support/community references reviewed
- [ ] versions docs no longer describe the original release/distribution model as current truth if that is no longer true
- [ ] built-in docs do not send users to abandoned project destinations

**Risks:**
- there are many asset files; focus first on landing pages, support links, and the most visible docs
- do not let this turn into a full docs rewrite; this pass is coherence and ownership

**Acceptance:**
- the in-app help experience feels like it belongs to the revived fork

---

### 7. update sample/default content and onboarding text
**Objective:** Ensure first-run content reflects the revived project.

**Files:**
- modify: `defaulttasks.txt`
- review: onboarding/help strings in `app/src/main/res/values*.xml`

**Changes:**
- replace `SIMPLETASK` references with `Simpletask Revived`
- replace old GitHub issue example links with the fork’s issue tracker or neutral examples
- keep sample tasks useful; do not overload them with fork meta-commentary

**Checklist:**
- [ ] defaulttasks brand updated
- [ ] defaulttasks issue example link updated or neutralized
- [ ] no onboarding sample still advertises the original repo

**Acceptance:**
- new users see fork-owned identity from first launch onward

---

### 8. review launcher/store-facing visual assets and widget preview
**Objective:** Avoid shipping a renamed app with misleading original branding art.

**Files:**
- review/modify as needed: `app/src/main/res/drawable-*/ic_launcher.png`
- review/modify as needed: `app/src/debug/res/drawable-*/ic_launcher.png`
- review/modify as needed: `app/src/main/res/drawable-nodpi/appwidget_preview.png`
- review/modify as needed: `extras/*`

**Changes:**
- inspect current icons and preview assets for explicit old-brand text or logos
- if icons are brand-neutral, they may be retained for v1
- if any asset includes old name text or misleading store branding, update it
- keep this pragmatic; do not block the release on a full visual redesign unless the current art is clearly wrong

**Checklist:**
- [ ] launcher icon reviewed
- [ ] debug icon reviewed
- [ ] widget preview reviewed
- [ ] any explicitly old-branded art updated or queued with an explicit follow-up note

**Risks:**
- shipping old art with new text may look sloppy or confusing

**Acceptance:**
- app visuals are at least not contradictory to the new name

---

### 9. add repo-local F-Droid/release metadata scaffolding
**Objective:** Track the fork’s release-facing listing content in the repo.

**Files:**
- create if Scott wants repo-local metadata tracking: `fastlane/metadata/android/en-US/`
- likely create under that directory:
  - `title.txt`
  - `short_description.txt`
  - `full_description.txt`
  - `changelogs/<versionCode>.txt` when appropriate
- optionally create docs note describing the chosen F-Droid submission metadata workflow

**Changes:**
- add canonical app title and descriptions suitable for F-Droid
- keep the copy aligned with the revived fork identity and todo.txt positioning
- do not over-claim maintenance or roadmap promises
- if screenshots are planned later, note them as a follow-up rather than blocking the text metadata setup

**Checklist:**
- [ ] metadata directory created if desired
- [ ] title drafted
- [ ] short description drafted
- [ ] full description drafted
- [ ] copy reflects fork identity and current supported flavors

**Risks:**
- the repo may not currently use fastlane; if Scott prefers to manage F-Droid metadata elsewhere, document that explicitly instead of adding dead scaffolding

**Acceptance:**
- there is a canonical, reusable listing copy source for F-Droid submission prep

---

### 10. verify build, support flows, and flavor coherence
**Objective:** Confirm the rebrand is technically and behaviorally consistent.

**Files:**
- no primary code target; verification across changed files

**Changes:**
- run focused searches for stale old-brand/public-URL references after edits
- build the cloudless flavor with the repo helper
- if flavor scope includes nextcloud/webdav, at least confirm they still compile or document why they are deferred
- inspect installed app label, share target text, help links, and issue-link behavior

**Checklist:**
- [ ] stale public `Simpletask` references searched and triaged
- [ ] stale original repo URLs searched and triaged
- [ ] cloudless build passes
- [ ] primary help/support links verified
- [ ] application ID verified in built output/installed app
- [ ] changelog included in same implementation commit

**Acceptance:**
- the fork builds, presents itself consistently, and no longer routes users to the original project by default

## sequencing
1. lock the new package/application ID strategy and support URLs
2. update distribution identity files (`build.gradle`, manifest/account/provider surfaces)
3. update primary English strings and runtime support/help routes
4. rewrite repo docs and changelog
5. update in-app help assets and default sample content
6. review/update localized brand-bearing strings
7. review visual/release assets
8. add release metadata scaffolding if Scott wants it in-repo
9. run final searches/build verification

## search_queries_to_run_during_execution
Use targeted searches after each unit to keep the rename honest.

- search for old public brand text:
  - `Simpletask`
  - `Simpletask Cloudless`
  - `Simpletask Nextcloud`
  - `Simpletask WebDAV`
- search for old repo/support URLs:
  - `mpcjanssen/simpletask-android`
  - `mpcjanssen.nl`
  - `simpletask-android/issues`
  - `hosted.weblate.org/engage/simpletask`
- search for old package IDs:
  - `nl.mpcjanssen.simpletask`
- search for stale F-Droid package links:
  - `f-droid.org`

When reviewing search results, distinguish between:
- **must change now**: user-facing strings, support links, application IDs, listing metadata
- **acceptable to keep for now**: internal class names, source packages, test class names, historical changelog entries

## validation_strategy
### targeted static verification
- search for remaining old public-facing brand/support references after edits
- inspect the exact changed files before build

### build verification
Preferred build from repo root:
```bash
cd /home/agent/dev/simpletasks_app
./scripts/build_cloudless.sh
```

If using focused unit tests for newly extracted helpers:
```bash
JAVA_HOME="$HOME/.local/jdks/temurin-11" PATH="$HOME/.local/jdks/temurin-11/bin:$PATH" \
./gradlew :app:testCloudlessDebugUnitTest --tests nl.mpcjanssen.simpletask.SomeNewOrAffectedTest
```

### manual verification checklist
#### install/build identity
- [ ] built app installs as a distinct app, not the original package
- [ ] launcher label shows `Simpletask Revived` (or the agreed flavor-specific variant)
- [ ] app info/package name matches the new application ID

#### in-app branding
- [ ] settings/about/help surfaces show the new name
- [ ] share target text uses the new name
- [ ] reminder/calendar titles use the new name where applicable
- [ ] default/sample tasks reference `Simpletask Revived` or neutral examples

#### support ownership
- [ ] help menu opens the fork repo or fork website
- [ ] “report bug/request feature” goes to the fork’s issue tracker
- [ ] issue auto-linking from markdown/help content targets the fork tracker
- [ ] no primary user path sends users to the original maintainer’s homepage or issue tracker

#### docs/repo coherence
- [ ] README clearly states this is a maintained fork/revival
- [ ] CONTRIBUTE no longer speaks in the original maintainer’s voice
- [ ] changelog contains a user-visible note about the rebrand/fork prep

#### flavor sanity
- [ ] cloudless build succeeds
- [ ] if nextcloud/webdav are still in scope, their labels and account/support strings are coherent

## risks_and_unknowns
- **application ID changes may reveal hidden compatibility assumptions**
  - mitigation: search thoroughly for account types, provider authorities, and hardcoded package references before building
- **localized files create high-churn rename work**
  - mitigation: prioritize URLs and obvious brand-bearing strings; do not attempt full translation polishing in this pass
- **historical docs may mix current guidance with old release context**
  - mitigation: update landing/help/support pages first; leave deep historical content only if it is clearly historical
- **visual assets may lag the new name**
  - mitigation: explicitly review them and either update them now or note that they are intentionally brand-neutral
- **F-Droid metadata workflow may not yet be decided**
  - mitigation: if repo-local metadata is not adopted now, document that decision rather than half-adding scaffolding

## handoff_notes_for_codex
- keep the implementation practical: this is a fork identity pass, not a codebase refactor vanity project
- do not rename internal packages/classes unless a concrete build/runtime issue requires it
- prefer changing resource strings and runtime URLs over invasive code movement
- treat `CHANGELOG.md` as mandatory because the rebrand is user-facing
- if you encounter unresolved decisions about package prefix or support URLs, stop and ask rather than guessing
- when uncertain whether a `Simpletask` string is historical/internal vs user-facing, preserve internal/historical references and change only current outward-facing ones
- after the main edits, run cleanup searches specifically for public-facing old-brand references before claiming the work is done

## website_follow_on_note
The future website should happen **after** this plan is implemented. This rebrand pass should leave docs/support URLs in a state where they can later be swapped from repo-based docs to a VPS-hosted site with minimal additional code changes.
