# Cordova plugin for theme detection

ATTENTION:
FIRST RELEASE VERSION IS 2.0.2, DON'T USE LOWER VERSIONS

## Description

Cordova plugin for detect dark mode on Android.

For using dark mode you should have at least Android 9 (Pie).

## Installation

Add the plugin with the following command:

`cordova plugin add cordova-plugin-android-theme-detection`

## Usage

```js
cordova.plugins.ThemeDetection.isDarkMode
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

**ThemeDetectionResponse Examples**:

```js
ThemeDetectionResponse {
    value: true;
    message: 'Dark mode';
}
```

```js
ThemeDetectionResponse {
    value: false;
    message: 'Light mode';
}
```

```js
ThemeDetectionResponse {
    value: false;
    message: 'Theme detection is not available. You need at least SDK = 28, but you have installed SDK = <YOUR_SYSTEM_SDK>';
}
```

## Changelog

- 2.0.2: First release.
- 2.0.1: First trying release.
- 2.0.0: Re-created plugin with plugman.
- 1.0.5: Fixed @Override.
- 1.0.4: Fixed class name.
- 1.0.3: Fixed class name.
- 1.0.2: Fixed Android Min SDK.
- 1.0.1: Add to github.
- 1.0.0: Initial version.
