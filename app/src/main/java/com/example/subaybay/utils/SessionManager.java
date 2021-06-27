package com.example.subaybay.utils;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.subaybay.LogIn;
import com.example.subaybay.MainActivity;
import com.example.subaybay.dto.LogInResponse;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Secret";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // jwt token (make variable public to access from outside)
    public static final String KEY_AUTHENTICATIONTOKEN = "authenticationToken";

    // mobile number (make variable public to access from outside)
    public static final String KEY_MOBILENUMBER = "mobileNumber";

    // refresh token
    public static final String KEY_REFTOKEN = "refreshToken";

    // expiresAt
    public static final String KEY_EXPIRESAT = "expiresAt";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(LogInResponse logInResponse){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing authenticationToken in pref
        editor.putString(KEY_AUTHENTICATIONTOKEN, logInResponse.getAuthenticationToken());

        // Storing mobileNumber in pref
        editor.putString(KEY_MOBILENUMBER, logInResponse.getMobileNumber());

        // storing refresh token in pref
        editor.putString(KEY_REFTOKEN, logInResponse.getRefreshToken());

        // storing expires at
        editor.putString(KEY_EXPIRESAT, logInResponse.getExpiresAt());

        // commit changes
        editor.commit();
    }


//    /**
//     * Get stored session data
//     * */
//    public HashMap<String, String> getUserDetails(){
//        HashMap<String, String> user = new HashMap<String, String>();
//        // user name
//        user.put(KEY_AUTHENTICATIONTOKEN, pref.getString(KEY_NAME, null));
//
//        // user email id
//        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
//
//        // return user
//        return user;
//    }

    // to retrieve all pref
    public String token(){
        return pref.getString(KEY_AUTHENTICATIONTOKEN, null);
    }

    public String mobileNumber(){
        return pref.getString(KEY_MOBILENUMBER, null);
    }

    public String refreshToken(){
        return pref.getString(KEY_REFTOKEN, null);
    }

    public String expiresAt(){
        return pref.getString(KEY_EXPIRESAT, null);
    }
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LogIn.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LogIn.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }


    public void alreadyLogIn() {
        // Check login status
        if(this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);
            // Staring Login Activity
            _context.startActivity(i);
        }else{
            Intent i = new Intent(_context, LogIn.class);
            // Staring Login Activity
            _context.startActivity(i);
        }
    }
}
