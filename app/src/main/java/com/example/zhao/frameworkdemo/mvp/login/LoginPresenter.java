package com.example.zhao.frameworkdemo.mvp.login;

import android.app.Activity;

import com.example.zhao.frameworkdemo.appdi.module.ActivityModule;
import com.example.zhao.frameworkdemo.base.BasePresenter;
import com.example.zhao.frameworkdemo.entities.LoginReqEntity;
import com.example.zhao.frameworkdemo.entities.LoginResEntity;
import com.example.zhao.frameworkdemo.net.rxjava.ISubscriberListener;
import com.example.zhao.frameworkdemo.net.rxjava.RxSubscriber;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<ILoginContract.ILoginView,LoginMVPModule> {

    //若不需要Activity 则不需要在ApiComponent中添加Module
    private Activity activity;

    @Inject
    public LoginPresenter(ILoginContract.ILoginView baseView, LoginMVPModule baseModule ,Activity activity) {
        super(baseView, baseModule);
        this.activity = activity;
    }

    public void login(){

        String email = baseView.getEmail();
        String pass = baseView.getPass();
        if(email == null || email.length()<=0){
            baseView.showMsg("请输入邮箱");
            return;
        }

        if(pass == null || pass.length()<=0){
            baseView.showMsg("请输入密码");
            return;
        }
        final LoginReqEntity loginReqEntity = new LoginReqEntity();
        loginReqEntity.setEmail(baseView.getEmail());
        loginReqEntity.setPassword(baseView.getPass());
        addSubscription(baseModule.login(loginReqEntity, new RxSubscriber<LoginResEntity>(activity,new ISubscriberListener<LoginResEntity>() {
            @Override
            public void onSuccess(LoginResEntity loginResEntity) {
                baseView.onNext(loginReqEntity);
            }

            @Override
            public void onFail(String msg) {
                baseView.onError(400,msg);
            }

        },false)));
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }
}
