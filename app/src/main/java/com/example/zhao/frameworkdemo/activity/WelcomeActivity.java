package com.example.zhao.frameworkdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.zhao.frameworkdemo.APP;
import com.example.zhao.frameworkdemo.R;
import com.example.zhao.frameworkdemo.appdi.component.ApiComponent;
import com.example.zhao.frameworkdemo.base.BaseActivity;

import javax.inject.Inject;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        APP.getApp(this).apiComponent.inject(this);

        APP.getApp(this).handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        },3000);
    }
}
