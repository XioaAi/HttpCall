package com.example.zhao.frameworkdemo.base.mvp_interface;

/**
 * 定义OnDestory   主要是防止对象依赖Activity等引起的内存泄漏
 */
public interface IModule {
    void onDestory();
}
