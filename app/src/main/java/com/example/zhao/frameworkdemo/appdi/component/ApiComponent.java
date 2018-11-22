package com.example.zhao.frameworkdemo.appdi.component;

import com.example.zhao.frameworkdemo.APP;
import com.example.zhao.frameworkdemo.activity.WelcomeActivity;
import com.example.zhao.frameworkdemo.appdi.Scoped.ActivityScoped;
import com.example.zhao.frameworkdemo.appdi.module.ActivityModule;
import com.example.zhao.frameworkdemo.appdi.module.ApiModule;
import com.example.zhao.frameworkdemo.appdi.module.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
    void inject(APP app);
    void inject(WelcomeActivity welcomeActivity);

    LoginComponent login(LoginModule loginModule, ActivityModule activityModule);
}
