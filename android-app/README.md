# Cribarth Music Player - Android App

A native Android implementation of the Cribarth Music Player that connects to Music Assistant.

## Features

- **Bilingual UI**: Full support for English and Welsh (Cymraeg)
- **Direct Connection**: Connects directly to Music Assistant on port 8095
- **Persistent Configuration**: Saves server IP and language preferences
- **WebView Integration**: Displays Music Assistant web interface
- **Easy Setup**: Simple settings screen for configuration

## Project Structure

```
android-app/
├── build.gradle          # Project build configuration
├── settings.gradle       # Project settings
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml  # App manifest
│   │   │   ├── java/
│   │   │   │   └── cymru/cribarth/musicplayer/
│   │   │   │       ├── MainActivity.kt      # Main WebView activity
│   │   │   │       └── SettingsActivity.kt  # Settings configuration
│   │   │   └── res/
│   │   │       ├── layout/              # XML layouts
│   │   │       ├── values/              # Strings and themes
│   │   │       └── mipmap/              # App icons
│   └── build.gradle       # App build configuration
└── README.md             # This file
```

## Building the App

### Prerequisites

- Android Studio (recommended)
- Java JDK 17 or higher
- Android SDK with API level 21+ (target API 34)
- Kotlin plugin

### Build Instructions

1. **Using Android Studio**:
   - Open the `android-app` directory as a project
   - Sync Gradle files
   - Build the project (Build → Make Project)
   - Run on device/emulator (Run → Run 'app')

2. **Using Command Line**:
   ```bash
   cd android-app
   ./gradlew build
   ```

3. **Generate APK**:
   ```bash
   ./gradlew assembleDebug  # Debug APK
   ./gradlew assembleRelease  # Release APK
   ```

### APK Location

- Debug APK: `app/build/outputs/apk/debug/app-debug.apk`
- Release APK: `app/build/outputs/apk/release/app-release.apk`

## Usage

1. **First Run**:
   - Launch the app
   - You'll be taken to the settings screen
   - Enter your Music Assistant server IP address
   - Select language (English or Welsh)
   - Tap "Save and Connect"

2. **Subsequent Runs**:
   - The app will automatically connect to the saved server
   - Use the back button to navigate back in the WebView
   - Long-press back button to exit the app

## Configuration

The app stores configuration in Android SharedPreferences:
- `server_ip`: The IP address of your Music Assistant server
- `language`: Language preference ('en' or 'cy')

## Architecture

### MainActivity
- Displays WebView with Music Assistant interface
- Loads saved configuration on startup
- Handles navigation between settings and player
- Manages WebView settings and behavior

### SettingsActivity
- Provides UI for configuring server IP
- Supports bilingual interface
- Validates IP address format
- Returns configuration to MainActivity

### WebView Configuration
- JavaScript enabled for Music Assistant functionality
- DOM storage enabled
- Zoom controls for better mobile experience
- Proper URL handling for external links

## Bilingual Support

The app supports both English and Welsh:
- All UI strings are localized
- Language preference is saved and restored
- Language switcher in settings screen

## Permissions

The app requires:
- `INTERNET`: To connect to Music Assistant server
- `ACCESS_NETWORK_STATE`: To check network connectivity

## Troubleshooting

### WebView not loading
- Ensure Music Assistant is running on the specified IP
- Check that port 8095 is open and accessible
- Verify the IP address is correct

### App crashes on launch
- Clear app data and try again
- Check Android Studio logs for errors
- Ensure all dependencies are properly installed

### Language not switching
- Restart the app after changing language
- Check that the language preference is saved correctly

## Future Enhancements

- Add menu options (settings, about, reload)
- Implement full-screen mode toggle
- Add developer tools access
- Support for HTTPS connections
- Auto-discovery of Music Assistant servers on local network
- Notification integration for playback status

## License

MIT © Justin Horrell, Cribarth Consulting