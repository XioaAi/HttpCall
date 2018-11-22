package com.example.zhao.frameworkdemo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.example.zhao.frameworkdemo.appdi.component.ApiComponent;
import com.example.zhao.frameworkdemo.appdi.component.DaggerApiComponent;
import com.example.zhao.frameworkdemo.appdi.module.ApiModule;
import com.example.zhao.frameworkdemo.appdi.module.AppModule;


import javax.inject.Inject;

public class APP extends Application {

    private static APP app = null;

    @Inject public ApiComponent apiComponent;

    @Inject public Handler handler;

    @Inject public SharedPreferences sharedPreferences;

    public static APP getApp(Context context){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //注入Modle  以及创建ApiComponent
        DaggerApiComponent.builder().apiModule(new ApiModule()).appModule(new AppModule(this)).build().inject(this);

    }


}
