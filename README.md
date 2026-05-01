Simpletask Revived
==================

Simpletask Revived is a maintained Android fork of Simpletask, built around the [todo.txt](http://todotxt.com) workflow by [Gina Trapani](http://ginatrapani.org/). This fork keeps the familiar task-management model while establishing its own app identity, support path, and release metadata.

- Documentation: [in-app help source](./app/src/main/assets/index.en.md)
- Repository: https://github.com/scaprisecca/simpletask-android
- Issue tracker: https://github.com/scaprisecca/simpletask-android/issues

## Project status

This repository is being prepared as a clean fork for renewed maintenance and F-Droid distribution. The first release path is centered on the `cloudless` flavor. Other flavors remain in the source tree, but they are not the primary submission target for the initial revived release.

## Flavors

### Cloudless

The cloudless build stores tasks in a local `todo.txt` file on the device. Use this if you sync files with another tool such as Syncthing, or if you want a local-first setup without built-in remote sync.

Because the task file can live anywhere on the device, this flavor requests broad storage access.

### Nextcloud

The Nextcloud flavor keeps task files on a Nextcloud-backed todo.txt file.

### WebDAV

The WebDAV flavor stores task files on a generic WebDAV server.

### Dropbox

The Dropbox flavor remains in the repository for now, but it is not part of the initial F-Droid path.

## F-Droid

F-Droid metadata and listing copy are now tracked in-repo. The revived fork uses distinct application IDs, so do not rely on historical F-Droid package links from the original project.

## Local development build

Use the helper script from the repo root:

```bash
./scripts/build_cloudless.sh
```

It defaults to:

```bash
./gradlew assembleCloudlessDebug
```

Other common uses:

```bash
./scripts/build_cloudless.sh installCloudlessDebug
./scripts/build_cloudless.sh assembleCloudlessRelease -- --stacktrace
```

Machine-local overrides:

```bash
JAVA11_HOME=/path/to/jdk11 ANDROID_SDK_ROOT=/path/to/android-sdk ./scripts/build_cloudless.sh
```

If `local.properties` is missing, the script creates it automatically. Do not commit machine-local SDK paths.

## Translation

The repo still contains the original translation set. Translation workflow/tooling can be refreshed for the revived fork later; until then, pull requests improving high-value strings are welcome.
