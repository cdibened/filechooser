package com.cesidiodibenedetto.filechooser;

import java.util.HashMap;
import java.util.Map;

import org.apache.cordova.CordovaActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.PluginResult;

/**
 * FileChooser is a PhoneGap plugin that acts as polyfill for Android KitKat and web
 * applications that need support for <input type="file">
 * 
 */
public class FileChooser extends CordovaPlugin {

    private CallbackContext callbackContext = null;
    private static final String TAG = "FileChooser";
    private static final int REQUEST_CODE = 6666; // onActivityResult request code

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        try {
            this.callbackContext = callbackContext;

            if (action.equals("openFileChooser")) {
                if (args.length() != 1) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
                    return false;
                }
                showChooser();
        } catch (JSONException e) {
            e.printStackTrace();
            String errorMessage=e.getMessage();
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION,errorMessage));
            return false;
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (this.callbackContext != null) {
            this.callbackContext.success(intent.getDataString());
        }
    }

       private void showChooser() {
           // Use the GET_CONTENT intent from the utility class
           Intent target = FileUtils.createGetContentIntent();
           // Create the chooser Intent
           Intent intent = Intent.createChooser(
                   target, getString(R.string.chooser_title));
           try {
               startActivityForResult(intent, REQUEST_CODE);
           } catch (ActivityNotFoundException e) {
               // The reason for the existence of aFileChooser
           }
       }

       @Override
       protected void onActivityResult(int requestCode, int resultCode, Intent data) {
           switch (requestCode) {
               case REQUEST_CODE:
                   // If the file selection was successful
                   if (resultCode == RESULT_OK) {
                       if (data != null) {
                           // Get the URI of the selected file
                           final Uri uri = data.getData();
                           Log.i(TAG, "Uri = " + uri.toString());
                           try {
                               // Get the file path from the URI
                               final String path = FileUtils.getPath(this, uri);
                           } catch (Exception e) {
                               Log.e("FileSelectorTestActivity", "File select error", e);
                           }
                       }
                   }
                   break;
           }
       }    
}
