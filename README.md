# Cordova Video Player plugin

For iOS and Android, remake of [Nicholas Hutchind](https://github.com/nchutchind)

## Description

This plugin allows you to stream video in a fullscreen, native player on iOS and Android.

* 1.0.1+ Works with Cordova >= 4.0

## Installation

```
cordova plugin add https://github.com/dan-leech/com.danleech.cordova.plugin.videoplayer
```

To remove
```
cordova plugin remove com.danleech.cordova.plugin.videoplayer
```

### iOS specifics
* Uses the AVPlayerViewController, supported for iOS 9+.
* Tested on iOS 11

### Android specifics
* Uses ExoPlayer2 (com.google.android.exoplayer:exoplayer:2.8.2).
* Creates two activities in your AndroidManifest.xml file.
* Tested on Android 4.0+

## Usage

```javascript
  var videoUrl = STREAMING_VIDEO_URL;

  // Just play a video
  window.plugins.VideoPlayer.play(videoUrl);

  // Play a video with callbacks
  var options = {
    successCallback: function() {
      console.log("Video was closed without error.");
    },
    errorCallback: function(errMsg) {
      console.log("Error! " + errMsg);
    },
    orientation: 'landscape',
    shouldAutoClose: true,  // true(default)/false
    controls: true // true(default)/false. Used to hide controls on fullscreen
  };
  window.plugins.VideoPlayer.play(videoUrl, options);
```

## Special Thanks

[Nicholas Hutchind](https://github.com/nchutchind)

[Michael Robinson (@faceleg)](https://github.com/faceleg)

[Timothy Shamilov (@shamilovtim)](https://github.com/shamilovtim)
