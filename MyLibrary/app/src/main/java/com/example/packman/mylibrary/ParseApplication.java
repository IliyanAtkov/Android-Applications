package com.example.packman.mylibrary;

import android.app.Application;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.Models.Books;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Books.class);
        //Parse.enableLocalDatastore(this);
        Parse.initialize(this, GlobalConstants.YOUR_APPLICATION_ID, GlobalConstants.YOUR_CLIENT_KEY );
    }
}
