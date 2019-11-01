package ua.valeriipsol.cordova.plugin;

import android.os.Build;
import android.content.res.Configuration;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

public class ThemeDetection extends CordovaPlugin {

  private CallbackContext callback = null;

  // Android 9 is needed for dark theme availability
  private static final int MINIMUM_SDK = 28;

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    return isDarkMode(callbackContext);
  }

  private boolean isDarkMode(CallbackContext callbackContext) {
    callback = callbackContext;
    try {
      int systemSDK = Build.VERSION.SDK_INT;
      boolean enabled = systemSDK >= MINIMUM_SDK;

      String responseMessage = "Theme detection is not available. You need at least SDK = 28, but you have installed SDK = " + systemSDK;

      if(enabled) {
        int uiMode = this.cordova.getActivity().getResources().getConfiguration().uiMode
                             & Configuration.UI_MODE_NIGHT_MASK;
        enabled = uiMode == Configuration.UI_MODE_NIGHT_YES;

        if(enabled) {
          responseMessage = "Dark mode";
        } else {
          responseMessage = "Light mode";
        }
      }

      JSONObject obj = createReturnObject(enabled, responseMessage);
      returnCordovaPluginResult(PluginResult.Status.OK, obj, false);
    } catch (Exception e) {
        JSONObject obj = createReturnObject(false, e.getMessage());
        returnCordovaPluginResult(PluginResult.Status.ERROR, obj, true);
        return false;
    }
    return true;
  }

  // creates a return object with all needed information
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

// returns the plugin result to javascript interface
  private void returnCordovaPluginResult(Status status, JSONObject obj, boolean setKeepCallback) {
    PluginResult result = new PluginResult(status, obj);
    if(!setKeepCallback) {
        result.setKeepCallback(false);
    }
    callback.sendPluginResult(result);
  }
}
