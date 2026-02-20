# Android App Development Guide

This guide provides step-by-step instructions for creating, building, and deploying the Android version of Cribarth Music Player.

## Overview

The Android app is a native Kotlin implementation that provides the same functionality as the Electron desktop app:
- Connects to Music Assistant server
- Displays the Music Assistant web interface in a WebView
- Supports bilingual UI (English/Welsh)
- Persists configuration between sessions

## Prerequisites

### Required Software

1. **Android Studio** (latest version recommended)
   - Download: https://developer.android.com/studio
   - Includes Android SDK, build tools, and emulator

2. **Java JDK 17 or higher**
   - Required for Android development
   - Android Studio typically includes OpenJDK

3. **Git** (for version control)
   - Download: https://git-scm.com/

### Hardware Requirements

- **For development**: Any modern computer (Windows, macOS, or Linux)
- **For testing**: Android device or emulator
  - Minimum API level: 21 (Android 5.0 Lollipop)
  - Recommended: API level 34 (Android 14)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/glanyrafon01/cribarth-music-player.git
cd cribarth-music-player
```

### 2. Open Project in Android Studio

1. Launch Android Studio
2. Select "Open an existing Android Studio project"
3. Navigate to the `android-app` directory
4. Click "OK" to open the project
5. Wait for Gradle sync to complete

### 3. Set Up Emulator (Optional)

If you don't have a physical Android device:

1. Open Android Studio
2. Click on the device selector (top bar)
3. Click "Create Virtual Device"
4. Select a device (e.g., Pixel 6, Nexus 5)
5. Select system image (API 34, Android 14 recommended)
6. Click "Finish" to create the emulator

## Building the App

### Using Android Studio

1. **Build the project**:
   - Click "Build" → "Make Project" in the menu
   - Or click the hammer icon in the toolbar

2. **Run on device/emulator**:
   - Click the green "Run" button (▶️) in the toolbar
   - Select your target device from the dropdown
   - The app will install and launch automatically

3. **Generate APK**:
   - Click "Build" → "Build Bundle(s) / APK(s)" → "Build APK(s)"
   - The APK will be generated in:
     `app/build/outputs/apk/debug/app-debug.apk`

### Using Command Line

```bash
# Navigate to the android-app directory
cd android-app

# Build the project
./gradlew build

# Generate debug APK
./gradlew assembleDebug

# Generate release APK (requires signing config)
./gradlew assembleRelease

# Install on connected device
./gradlew installDebug

# Run on connected device
./gradlew connectedDebugAndroidTest
```

## Project Structure

```
android-app/
├── build.gradle          # Root build configuration
├── settings.gradle       # Project settings
├── gradle.properties     # Gradle properties
├── local.properties      # Local SDK path (auto-generated)
└── app/                  # Main app module
    ├── build.gradle      # App-specific build configuration
    ├── src/              # Source code
    │   ├── main/          # Main source set
    │   │   ├── AndroidManifest.xml  # App manifest
    │   │   ├── java/          # Kotlin/Java source
    │   │   ├── res/           # Resources
    │   │   │   ├── layout/      # XML layouts
    │   │   │   ├── values/      # Strings, colors, themes
    │   │   │   ├── mipmap/      # App icons
    │   │   │   └── ...         # Other resources
    │   │   └── assets/       # Asset files
    │   └── ...             # Other source sets (debug, release)
    └── build/            # Build output (auto-generated)
```

## Key Files Explained

### MainActivity.kt

The main activity that:
- Creates and manages the WebView
- Loads configuration on startup
- Handles navigation to settings
- Manages WebView lifecycle

### SettingsActivity.kt

The settings activity that:
- Provides UI for configuring server IP
- Supports bilingual interface
- Validates IP address format
- Returns configuration to MainActivity

### AndroidManifest.xml

Defines app metadata and permissions:
- Application ID: `cymru.cribarth.musicplayer`
- Minimum SDK: 21
- Target SDK: 34
- Required permissions: INTERNET, ACCESS_NETWORK_STATE

### build.gradle (app level)

Key dependencies:
- `androidx.core:core-ktx`: Core Android functionality
- `androidx.appcompat:appcompat`: AppCompat library
- `com.google.android.material:material`: Material Design components
- `androidx.preference:preference-ktx`: SharedPreferences support
- `androidx.webkit:webkit`: WebView support

## Development Workflow

### Adding New Features

1. **Plan the feature**:
   - What functionality should it provide?
   - Which activity should handle it?
   - What UI changes are needed?

2. **Update layout files**:
   - Modify XML files in `res/layout/`
   - Add new views and update IDs

3. **Update string resources**:
   - Add English and Welsh translations in `res/values/strings.xml`
   - Follow the existing pattern for bilingual support

4. **Implement logic**:
   - Update Kotlin files in `java/cymru/cribarth/musicplayer/`
   - Add new methods and classes as needed

5. **Test the feature**:
   - Run on emulator or device
   - Test both English and Welsh interfaces
   - Verify configuration persistence

### Making UI Changes

1. **Edit XML layouts**:
   - Use Android Studio's layout editor or manual XML editing
   - Test different screen sizes and orientations

2. **Update strings**:
   - Always provide both English and Welsh translations
   - Use descriptive string names

3. **Test responsiveness**:
   - Use Android Studio's preview tools
   - Test on multiple device sizes

### Debugging

1. **View logs**:
   - Use Logcat in Android Studio (View → Tool Windows → Logcat)
   - Filter by your app's package name

2. **Breakpoints**:
   - Set breakpoints in Kotlin code
   - Debug step-by-step

3. **Inspect views**:
   - Use Layout Inspector (Tools → Layout Inspector)
   - Check view hierarchies and properties

## Testing

### Unit Testing

```bash
# Run unit tests
./gradlew test

# Run specific test class
./gradlew test --tests "cymru.cribarth.musicplayer.YourTestClass"
```

### Instrumentation Testing

```bash
# Run instrumentation tests
./gradlew connectedAndroidTest

# Run on specific device
./gradlew connectedDebugAndroidTest -PadbDevice=your_device_serial
```

### Manual Testing Checklist

1. **First run**:
   - [ ] App shows settings screen
   - [ ] Language switcher works
   - [ ] IP validation works
   - [ ] Configuration is saved

2. **Subsequent runs**:
   - [ ] App automatically connects to saved server
   - [ ] WebView loads Music Assistant
   - [ ] Navigation works correctly

3. **Bilingual support**:
   - [ ] All text displays in English
   - [ ] All text displays in Welsh
   - [ ] Language preference is saved

4. **Edge cases**:
   - [ ] Invalid IP addresses are rejected
   - [ ] Empty IP addresses show settings
   - [ ] Back button navigation works
   - [ ] App handles network errors gracefully

## Deployment

### Generating Signed APK

1. **Create keystore** (if you don't have one):
   ```bash
   keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-alias
   ```

2. **Update build.gradle**:
   Add signing config to `app/build.gradle`:
   ```gradle
   android {
       signingConfigs {
           release {
               storeFile file('my-release-key.jks')
               storePassword 'your_password'
               keyAlias 'my-alias'
               keyPassword 'your_password'
           }
       }
       buildTypes {
           release {
               signingConfig signingConfigs.release
           }
       }
   }
   ```

3. **Build release APK**:
   ```bash
   ./gradlew assembleRelease
   ```

4. **Find the APK**:
   - Location: `app/build/outputs/apk/release/app-release.apk`

### Publishing to Google Play

1. **Create Google Play Developer account**:
   - https://play.google.com/console/
   - One-time fee: $25

2. **Prepare store listing**:
   - App title and description
   - High-resolution icons
   - Screenshots
   - Feature graphic

3. **Upload APK**:
   - Create new app in Play Console
   - Upload your signed APK
   - Fill out all required information

4. **Testing track**:
   - Upload to internal or open testing track
   - Get feedback from testers

5. **Production release**:
   - Promote from testing to production
   - App will be reviewed by Google
   - Available in Play Store within hours

## Troubleshooting

### Common Issues and Solutions

#### Gradle sync fails
- **Symptom**: Red error messages in Android Studio
- **Solution**:
  - File → Invalidate Caches / Restart
  - Check internet connection
  - Update Gradle plugin version

#### App won't install on device
- **Symptom**: Installation fails with error
- **Solution**:
  - Check device compatibility (API level)
  - Uninstall previous versions
  - Check USB debugging settings

#### WebView not loading
- **Symptom**: WebView shows blank or error page
- **Solution**:
  - Verify Music Assistant is running
  - Check IP address and port (8095)
  - Test URL in browser first
  - Add internet permission to manifest

#### Bilingual text not showing
- **Symptom**: Only one language displays
- **Solution**:
  - Check string resource names
  - Verify language preference is saved
  - Restart app after language change

#### Build fails with dependency errors
- **Symptom**: Gradle build fails
- **Solution**:
  - Sync Gradle files
  - Check dependency versions
  - Clean project (Build → Clean Project)
  - Rebuild project

### Getting Help

- **Android Studio documentation**: https://developer.android.com/studio
- **Kotlin documentation**: https://kotlinlang.org/docs/home.html
- **Stack Overflow**: https://stackoverflow.com/questions/tagged/android
- **GitHub Issues**: Report bugs in the project repository

## Best Practices

### Code Quality

1. **Follow Kotlin conventions**:
   - Use camelCase for variable names
   - Use PascalCase for class names
   - Keep functions focused and single-purpose

2. **Error handling**:
   - Handle network errors gracefully
   - Provide user-friendly error messages
   - Log errors for debugging

3. **Performance**:
   - Avoid memory leaks
   - Use view binding for efficient view access
   - Optimize WebView settings

### UI/UX

1. **Responsive design**:
   - Test on multiple screen sizes
   - Use constraint layouts for flexibility
   - Handle orientation changes properly

2. **Accessibility**:
   - Provide content descriptions
   - Ensure sufficient color contrast
   - Support screen readers

3. **Bilingual consistency**:
   - Keep English and Welsh text aligned
   - Test both languages thoroughly
   - Maintain consistent UI structure

### Configuration Management

1. **Use SharedPreferences**:
   - For simple key-value storage
   - For user preferences
   - For configuration persistence

2. **Avoid hardcoding**:
   - Use string resources for all text
   - Use dimensions for sizes and spacing
   - Use colors for color values

## Next Steps

### After Initial Setup

1. **Run the app** on emulator or device
2. **Test all functionality**
3. **Fix any bugs** found during testing
4. **Add missing features** as needed

### For Production

1. **Create release build** with signing
2. **Test thoroughly** on multiple devices
3. **Prepare store listing** materials
4. **Publish to Google Play**

### For Ongoing Development

1. **Monitor crash reports**
2. **Gather user feedback**
3. **Fix bugs** and improve stability
4. **Add new features** based on user needs
5. **Update dependencies** regularly

## Resources

### Official Documentation

- Android Developer Guide: https://developer.android.com/guide
- Kotlin Language Docs: https://kotlinlang.org/docs/home.html
- Material Design: https://material.io/design

### Learning Materials

- Android Basics in Kotlin: https://developer.android.com/courses/android-basics-kotlin/course
- Kotlin for Java Developers: https://developer.android.com/kotlin/java-to-kotlin
- WebView Guide: https://developer.android.com/guide/webapps/webview

### Community

- Android Developers Subreddit: https://www.reddit.com/r/androiddev/
- Kotlin Slack: https://surveys.jetbrains.com/s3/kotlin-slack-sign-up
- Stack Overflow: https://stackoverflow.com/

---

This guide should provide everything you need to develop, build, and deploy the Android version of Cribarth Music Player. If you encounter any issues not covered here, check the official Android documentation or create an issue in the project repository.