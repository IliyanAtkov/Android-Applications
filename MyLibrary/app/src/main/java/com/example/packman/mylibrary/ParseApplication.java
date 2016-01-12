package com.example.packman.mylibrary;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    public static final String YOUR_APPLICATION_ID = "dtxFKaLfF2gArJvKC8W8MTjNSdy3lx9LhvvjU9G9";
    public static final String YOUR_CLIENT_KEY = "nfyCghcM709zEDuiP0BmAnr6ir3FKO4WuC9DCGcU";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY );
    }
}
