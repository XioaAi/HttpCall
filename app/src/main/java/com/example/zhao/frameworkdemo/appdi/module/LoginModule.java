package com.example.zhao.frameworkdemo.appdi.module;

import com.example.zhao.frameworkdemo.mvp.login.ILoginContract;
import com.example.zhao.frameworkdemo.mvp.login.LoginMVPModule;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    ILoginContract.ILoginView iLoginView;

    public LoginModule(ILoginContract.ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Provides
    public ILoginContract.ILoginModule providerLoginModule(LoginMVPModule loginMVPModule){
        return loginMVPModule;
    }

    @Provides
    public ILoginContract.ILoginView providerLoginView(){
        return iLoginView;
    }


}
