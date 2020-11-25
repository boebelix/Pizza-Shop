#!/bin/bash
sed -e 's#%flutterSdk%#'$(echo $FLUTTER_HOME)'#g' local.properties.example > local.properties
