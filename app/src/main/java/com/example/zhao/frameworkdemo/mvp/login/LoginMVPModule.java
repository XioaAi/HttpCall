package com.example.zhao.frameworkdemo.mvp.login;

import com.example.zhao.frameworkdemo.entities.LoginResEntity;
import com.example.zhao.frameworkdemo.net.API;
import com.example.zhao.frameworkdemo.entities.LoginReqEntity;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginMVPModule implements ILoginContract.ILoginModule {

    private API api;

    @Inject
    public LoginMVPModule(API api) {
        this.api = api;
    }


    @Override
    public Subscription login(LoginReqEntity loginReqEntity, Subscriber subscriber) {
        Observable<LoginResEntity> observable = api.login(loginReqEntity);
        return observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void onDestory() {

    }

}
