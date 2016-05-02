package com.example.hau.dulichviet;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.example.hau.dulichviet.models.database.Category;
import com.example.hau.dulichviet.models.database.Place;


import timber.log.Timber;

/**
 * Created by HAU on 12/3/2015.
 */
public class MyApplication extends Application {
    public static boolean activityVisible;

    @Override
    public void onCreate() {
        super.onCreate();
        Dependencies.init();
        initActiveAndroid();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }


    @SuppressWarnings("unchecked")
    private void initActiveAndroid() {

        Configuration.Builder configurationBuilder = new Configuration.Builder(this);

        // Setup ActiveAndroid database model
        configurationBuilder.addModelClasses(Category.class);
        configurationBuilder.addModelClasses(Place.class);

        ActiveAndroid.initialize(configurationBuilder.create());
        ActiveAndroid.setLoggingEnabled(false);
    }

}
