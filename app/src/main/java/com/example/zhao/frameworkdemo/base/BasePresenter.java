package com.example.zhao.frameworkdemo.base;

import com.example.zhao.frameworkdemo.base.mvp_interface.IBaseModule;
import com.example.zhao.frameworkdemo.base.mvp_interface.IBaseView;
import com.example.zhao.frameworkdemo.base.mvp_interface.IPresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<V extends IBaseView, M extends IBaseModule> implements IPresenter {

    public V baseView;
    public M baseModule;

    CompositeSubscription compositeSubscription = new CompositeSubscription();

    public BasePresenter(V baseView, M baseModule) {
        this.baseView = baseView;
        this.baseModule = baseModule;
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if(compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onDestory() {
        unSubscribe();
        if(baseModule!=null){
            baseModule.onDestory();
        }
        baseModule = null;
        baseView = null;
    }
}
