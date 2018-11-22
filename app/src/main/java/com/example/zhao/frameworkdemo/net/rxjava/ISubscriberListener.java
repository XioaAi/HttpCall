package com.example.zhao.frameworkdemo.net.rxjava;

public interface ISubscriberListener<T> {
    void onSuccess(T t);
    void onFail(String msg);
}
