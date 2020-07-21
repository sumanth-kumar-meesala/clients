# ME Android Code Challenge

# Android Application

This is an Android Application README to show briefly about setup.

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/sumanth-kumar-meesala/mebank-challenge.git
```

## Configuration
### Keystores:
open `app/build.gradle`:
```gradle
storeFile='...'
storePassword='...'
keyAlias='...'
keyPassword='...'
```
And place both keystore under `app/artifacts/` directory:
- `production_keystore.jks`


## Build variants
Use the Android Studio *Build Variants* button to choose between **production** and **development** flavors combined with debug and release build types


## Generating signed APK
From Android Studio:
1. ***Build Variants*** menu
2. ***Select debug or release apk***
3. Build APK

## Chainging API URL
open `app/build.gradle`:
1. Go to buildTypes
2. Change the value of BASE_URL and sync the project