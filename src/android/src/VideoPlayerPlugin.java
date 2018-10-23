package com.danleech.cordova.plugin.videoplayer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

public class VideoPlayerPlugin extends CordovaPlugin {
	public static final String ACTION_PLAY_VIDEO = "playVideo";

	private static final int ACTIVITY_CODE_PLAY_MEDIA = 7;

	private CallbackResponse callbackResponse;

	private static final String TAG = "VideoPlayerPlugin";

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		callbackResponse = new CallbackResponse(callbackContext);

		try {
			final VideoPlayerPlugin self = this;
			if (ACTION_PLAY_VIDEO.equals(action)) {
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						final Intent videoIntent = new Intent(cordova.getActivity().getApplicationContext(), VideoPlayerActivity.class);
						Bundle extras = new Bundle();
						extras.putString("mediaUrl", args.optString(0));

						videoIntent.putExtras(extras);

						cordova.startActivityForResult(self, videoIntent, ACTIVITY_CODE_PLAY_MEDIA);
					}
				});
				return true;
			} else {
				callbackResponse.send(PluginResult.Status.INVALID_ACTION, false);
				return false;
			}
		} catch (Exception e) {
			callbackResponse.send(PluginResult.Status.JSON_EXCEPTION, false);
			return false;
		}


	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.v(TAG, "onActivityResult: " + requestCode + " " + resultCode);
		super.onActivityResult(requestCode, resultCode, intent);
		if (ACTIVITY_CODE_PLAY_MEDIA == requestCode) {
			if (Activity.RESULT_OK == resultCode) {
				callbackResponse.send(PluginResult.Status.OK, false);
			} else if (Activity.RESULT_CANCELED == resultCode) {
				String errMsg = "Error";
				if (intent != null && intent.hasExtra("message")) {
					errMsg = intent.getStringExtra("message");
				}
				callbackResponse.send(PluginResult.Status.ERROR, errMsg, false);
			}
		}
	}
}
