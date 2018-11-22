package com.example.zhao.frameworkdemo.mvp.login;

import com.example.zhao.frameworkdemo.base.mvp_interface.IBaseModule;
import com.example.zhao.frameworkdemo.base.mvp_interface.IBaseView;
import com.example.zhao.frameworkdemo.entities.LoginReqEntity;

import rx.Subscriber;
import rx.Subscription;

public interface ILoginContract {
    interface ILoginModule extends IBaseModule{
        Subscription login(LoginReqEntity loginReqEntity, Subscriber subscriber);
    }

    interface ILoginView<T> extends IBaseView{
        String getEmail();
        String getPass();

        void onNext(T t);
        void onError(int code,String msg);
    }
}
