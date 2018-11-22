package com.example.zhao.frameworkdemo.appdi.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.example.zhao.frameworkdemo.APP;
import com.example.zhao.frameworkdemo.constant.APPConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    private APP context;
    public AppModule(APP context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context.getApplicationContext();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSpf(){
        return context.getApplicationContext().getSharedPreferences(APPConfig.SPF_NAME,Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public Handler provideHandler(){
        return new Handler();
    }
}
