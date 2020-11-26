# Hettel-Teamprojekt-TeamA

## Building

### Building Flutter
### Preparing your system
Before you can build this project, you have to copy & paste the `local.properties.example` File (in ./apps/mobileApp/android), change it's name to
`local.properties` and change the following paths inside it so that they fit your system.
```properties
sdk.dir=<<path to android sdk>>
flutter.sdk=<<path to flutter sdk (without /bin at the end)>>
```

The `local.properties` file must never be checked in to version control. The example-File must always stay in the repository.

#### Building APKs
To build the APKs, navigate into `./apps/mobileApp/android` and run `./gradlew clean build` (on Linux) or `gradlew.bat clean build` (on Windows)


### Building everything
#### Windows
`gradlew.bat clean build`
#### Linux / Mac
`./gradlew.sh clean build`
