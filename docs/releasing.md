# Publishing releases

`.github/workflows/build-and-publish.yml` builds and checks pull requests and pushes
on `7.5.x`. These runs save the distribution JAR as a GitHub Actions artifact and
**never publish**. Publishing a GitHub release (including a prerelease) builds its
tag, validates the JAR, then uploads that same JAR to CurseForge and Modrinth in
independent jobs. A tag push, draft release, or release edit does not publish.

## One-time repository setup

Under **Settings → Secrets and variables → Actions**, configure:

| Kind | Name | Value |
| --- | --- | --- |
| Variable | `CURSEFORGE_PROJECT_ID` | Numeric ID of the CurseForge project to upload to |
| Variable | `MODRINTH_PROJECT_ID` | ID of the Modrinth project to upload to |
| Secret | `CURSEFORGE_TOKEN` | CurseForge upload API token with access to that project |
| Secret | `MODRINTH_TOKEN` | Modrinth API token with permission to create versions on that project |

Enable GitHub Actions if it is disabled on the fork. Project IDs are intentionally
not hardcoded: forks must choose their own destinations and credentials. Do not
configure upstream publishing credentials on a review/testing fork. Missing
configuration fails the affected publishing job with an explanatory error; it
does not silently report a successful deployment.

The workflow needs only `contents: read` for GitHub. Publishing tokens are provided
only to release publishing jobs; the build job does not receive them. Third-party
actions are pinned to commit SHAs. Only publish tags whose source and workflow you
have reviewed: release workflows execute trusted repository code.

## Cut a release

1. Merge the workflow and the changes being released.
2. Update `mod_version` in `gradle.properties`, commit it, and let the build check
   pass. The workflow reads the Minecraft version from `gradle/libs.versions.toml`.
3. Create a tag **exactly equal** to `mod_version` on that commit (for example,
   `2.1.1`, not `v2.1.1`). The tagged commit must contain this workflow. A mismatched
   tag fails validation before either upload job starts.
4. Create and publish a GitHub release for that tag. Its title (or tag if blank)
   is the upload display name; its release notes are the changelog on both sites.
   Normal GitHub releases publish as `release`; GitHub prereleases publish as
   `beta` (including tags containing `alpha`; there is no automatic alpha mode).
5. Check both publishing jobs and the new versions on both distribution sites.
   CurseForge moderation may delay public availability after a successful upload.

Builds use Java 17 and `bash ./gradlew --no-daemon build` (the upstream wrapper is
not executable). The sole uploaded file is
`build/libs/<mod_id>-<minecraft>-<mod_version>.jar`, the full reobfuscated `reobfJar`
output. Its embedded mod ID/version is checked before upload. Development, slim,
and sources JARs are excluded. The workflow advertises Forge and the exact
Minecraft version, GregTech CEu Modern as required, and JEI/EMI as optional.
It does not create, edit, or attach files to GitHub releases.

## Failures and retries

The two upload jobs are independent. If only one destination fails, inspect that
platform first, fix its configuration if necessary, then use **Re-run failed
jobs**, not **Re-run all jobs**. An upload may have succeeded remotely before a
network failure was reported, so verify the platform before retrying. This workflow
does not promise duplicate-safe uploads or roll back a successful publication.
Build artifacts are retained for seven days; after expiry, a failed upload cannot
reuse the artifact without rebuilding. Do not move a published tag or reuse a
published version to work around a failure.

No publishing tokens or destination projects are needed to review this PR or run
the build. Actual uploads require the above configuration and an intentionally
published release; PR builds do not exercise the authenticated upload APIs.
