package org.premiumapp.androidapp;

import android.app.Application;
import android.content.Context;

import org.premiumapp.androidapp.data.JsonStoreAdapter;

import timber.log.Timber;

public class ThisApp extends Application {


    public static ThisApp get(Context ctx) {
        return (ThisApp) ctx.getApplicationContext();
    }

    public JsonStoreAdapter adapter;

    @Override
    public void onCreate() {
        super.onCreate();

        adapter = new JsonStoreAdapter(this);

        if (BuildConfig.DEBUG) {

            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) +
                            ":timber: line=" + element.getLineNumber() +
                            " method: " + element.getMethodName();
                }
            });
        }
    }

    public JsonStoreAdapter getAdapter() {
        return adapter;
    }
}
