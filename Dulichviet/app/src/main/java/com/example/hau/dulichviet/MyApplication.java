package com.example.hau.dulichviet;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.multidex.MultiDex;
import android.util.Base64;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.example.hau.dulichviet.models.database.Category;
import com.example.hau.dulichviet.models.database.Place;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
