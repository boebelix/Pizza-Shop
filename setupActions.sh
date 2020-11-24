#!/bin/bash
sed "s/%flutterSdk%/$(echo $FLUTTER_HOME)/g" local.properties.example > local.properties
