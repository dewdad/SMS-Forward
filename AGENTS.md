
# Project Architecture

This is an Android application built using Gradle. The primary languages are Java and Kotlin. The application's purpose is to forward SMS messages.

# Agent Instructions

## Development Agent

*   **Code:** You can read and write code in Java and Kotlin.
*   **Android SDK:** You are familiar with the Android SDK and the general principles of Android development.
*   **XML Layouts:** You can modify Android XML layout files to change the user interface.
*   **Dependencies:** You can add and manage dependencies in the `app/build.gradle` file.

## Debugging Agent

*   **Testing:** Use the `./gradlew test` command to run unit tests.
*   **Logging:** You can read logs to identify and troubleshoot errors. Use `logcat` to view device logs.
*   **Debugger:** You can use a debugger to step through code, inspect variables, and analyze the application's behavior.

## Building Agent

*   **Build command:** To build the project, run the `./gradlew build` command.
*   **Build output:** The build process generates an `.apk` or `.aab` file, which can be found in the `app/build/outputs/` directory.

## Deployment Agent

*   **Release Build:** Use the `./gradlew assembleRelease` command to create a release-ready build of the application.
*   **App Signing:** Before deployment, the application must be signed with a digital certificate.
*   **Distribution:** Once signed, you can upload the application to the Google Play Store or other Android app stores.
