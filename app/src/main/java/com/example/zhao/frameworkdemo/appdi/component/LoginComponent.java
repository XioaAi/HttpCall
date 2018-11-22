package com.example.zhao.frameworkdemo.appdi.component;

import com.example.zhao.frameworkdemo.activity.LoginActivity;
import com.example.zhao.frameworkdemo.appdi.Scoped.ActivityScoped;
import com.example.zhao.frameworkdemo.appdi.module.ActivityModule;
import com.example.zhao.frameworkdemo.appdi.module.LoginModule;

import dagger.Subcomponent;

@ActivityScoped
@Subcomponent(modules = {LoginModule.class, ActivityModule.class})
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
