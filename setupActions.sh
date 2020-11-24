#!/bin/bash
sed -s "s/%flutterSdk%/$(echo $FLUTTER_ROOT)/g" local.properties.example > local.properties