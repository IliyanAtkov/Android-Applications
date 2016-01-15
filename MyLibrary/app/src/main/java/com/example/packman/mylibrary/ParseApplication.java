package com.example.packman.mylibrary;

import android.app.Application;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.models.Authors;
import com.example.packman.mylibrary.models.Books;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Books.class);
        ParseObject.registerSubclass(Authors.class);
        //Parse.enableLocalDatastore(this);
        Parse.initialize(this, GlobalConstants.YOUR_APPLICATION_ID, GlobalConstants.YOUR_CLIENT_KEY );
    }
}
