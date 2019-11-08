package ua.valeriipsol;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

import android.util.Log;
import android.os.Build;
import android.content.res.Configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class ThemeDetection extends CordovaPlugin {

    // To see logs: adb logcat -s "THEMEDETECTION"
    private static final String TAG = "THEMEDETECTION";

    private static final int MINIMUM_SDK = 28;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "Starting checking Dark Mode");
        return isDarkMode(callbackContext);
    }

      private boolean isDarkMode(CallbackContext callbackContext) {
        try {
          Log.d(TAG, "Opening dark mode function");
          int systemSDK = Build.VERSION.SDK_INT;
          Log.d(TAG, systemSDK);
          boolean enabled = systemSDK >= MINIMUM_SDK;
          Log.d(TAG, enabled);

          String responseMessage = "Theme detection is not available. You need at least SDK = 28, but you have installed SDK = " + systemSDK;

          if(enabled) {
            int uiMode = this.cordova.getActivity().getResources().getConfiguration().uiMode
                                 & Configuration.UI_MODE_NIGHT_MASK;
            Log.d(TAG, uiMode);
            enabled = uiMode == Configuration.UI_MODE_NIGHT_YES;

            if(enabled) {
              responseMessage = "Dark mode";
            } else {
              responseMessage = "Light mode";
            }
          }

          Log.d(TAG, enabled);
          Log.d(TAG, responseMessage);

          JSONObject obj = createReturnObject(enabled, responseMessage);
          callbackContext.success(obj);
        } catch (Exception e) {
            JSONObject obj = createReturnObject(false, e.getMessage());
            callbackContext.error(obj);
            return false;
        }
        return true;
      }

      // creates a return object with boolean value and string message attributes
      private JSONObject createReturnObject(boolean value, String message) {
        try {
          JSONObject obj = new JSONObject();
          obj.put("value", value);
          obj.put("message", message);
          return obj;
        } catch (Exception e) {
            System.out.println("JSONObject error: " + e.getMessage());
        }
        return null;
      }
}
