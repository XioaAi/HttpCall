package com.example.zhao.frameworkdemo.appdi.module;

import android.app.Activity;

import com.example.zhao.frameworkdemo.appdi.Scoped.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    public Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScoped
    public Activity providerActivity(){
        return activity;
    }
}
