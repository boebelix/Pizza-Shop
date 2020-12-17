# Hettel-Teamprojekt-TeamA

## Building

### Building Flutter
### Preparing your system
1. Before you can build this project, you have to copy & paste the `local.properties.example` file. You can find the file in **`./apps/mobileApp/android`**.
2. Change the name to `local.properties`
3. Change the following paths inside it so that they fit your system.
```properties
sdk.dir=<<path to android sdk>>
flutter.sdk=<<path to flutter sdk (without /bin at the end)>>
```

The `local.properties` file must never be checked in to version control. The example-File must always stay in the repository.

#### Building APKs
To build the APKs, navigate into `./apps/mobileApp/android` and run `./gradlew clean build` (on Linux) or `gradlew.bat clean build` (on Windows)


### Building all Services (except the Flutter App)
#### Windows
`gradlew.bat clean build`
#### Linux / Mac
`./gradlew.sh clean build`

### Building Docker Containers
Ensure that the services are built and run `gradlew buildImage`. Alternatively you can build the services and the
images in one go by running `gradlew clean build buildImage`.

## Integration Tests
You can run the integration tests by executing `gradlew runIntegrationTest`. This will start the system via docker,
run the tests, and shut down the containers. Be sure that you build the containers beforehand.


##
You can start the docker containers with following command `gradlew execComposeUp` to stop with 'gradlew execComposeDown'
