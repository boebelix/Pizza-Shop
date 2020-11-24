#!/bin/bash
sed -s "s/%flutterSdk%/$(echo $FLUTTER_HOME)/g" local.properties.example > local.properties