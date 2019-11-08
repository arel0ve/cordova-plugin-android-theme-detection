package cordova-plugin-android-theme-detection;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class ThemeDetection extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("isDarkMode")) {
            String message = args.getString(0);
            this.isDarkMode(message, callbackContext);
            return true;
        }
        return false;
    }

    private void isDarkMode(CallbackContext callbackContext) {
        try {
            callbackContext.success(true);
        } catch(e) {
            callbackContext.error("error");
        }
    }
}
