# Cordova plugin for theme detection

## Description

Cordova plugin for detect dark mode on Android.

For using dark mode you should have at least Android 9 (Pie).

## Installation

Add the plugin with the following command:

`cordova plugin add cordova-plugin-android-theme-detection`

## Usage

```js
cordova.themeDetection.isDarkMode(
  (res) => {
    console.log(res);
  },
  (error) => {
    console.log(error);
  }
);
```

### Methods

#### isDarkMode

`cordova.plugins.ThemeDetection.isDarkMode()`

Checks if the dark mode is enabled on device and returns an object with a boolean value and a message.

### Responses

**ThemeDetectionResponse**:

```js
ThemeDetectionResponse {
    value: boolean;
    message: string;
}
```

## Changelog

- 1.0.5: Fixed @Override.
- 1.0.4: Fixed class name.
- 1.0.3: Fixed class name.
- 1.0.2: Fixed Android Min SDK.
- 1.0.1: Add to github.
- 1.0.0: Initial version.
