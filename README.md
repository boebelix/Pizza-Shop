# Hettel-Teamprojekt-TeamA

## Building

### Preparing your system
Before you can build this project, you have to copy & paste the `local.properties.example` File, change it's name to
`local.properties` and change the paths inside so that they fit your system. The `local.properties` file must never be
checked in to version control.

Afterwards, you also have to make sure the environment variable `FLUTTER_ROOT` points to your flutter sdk.

### Building everything
#### Windows
`gradlew.bat clean build`
#### Linux / Mac
`./gradlew.sh clean build`
