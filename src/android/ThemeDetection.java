package ua.valeriipsol;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

import android.os.Build;
import android.content.res.Configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThemeDetection extends CordovaPlugin {

    private static final int MINIMUM_SDK = 28;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        return isDarkMode(callbackContext);
    }

      private boolean isDarkMode(CallbackContext callbackContext) {
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
          callbackContext.success(obj);
        } catch (Exception e) {
            JSONObject obj = createReturnObject(false, e.getMessage());
            callbackContext.error(obj);
            return false;
        }
        return true;
      }

      // creates a return JSON object {value: boolean, message: string}
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
