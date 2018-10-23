/**
 * An Video player plugin for Cordova
 *
 * Developed by Daniil Kostin
 */

function VideoPlayer() {
}

VideoPlayer.prototype.play = function (url, options) {
	options = options || {};
	cordova.exec(options.successCallback || null, options.errorCallback || null, "VideoPlayerPlugin", "playVideo", [url, options]);
};

var videoPlayer = new VideoPlayer();

module.exports = videoPlayer;
