package com.example.zhao.frameworkdemo.net.rxjava;


import android.content.Context;
import android.net.ParseException;

import com.example.zhao.frameworkdemo.R;
import com.example.zhao.frameworkdemo.entities.BaseResult;
import com.example.zhao.frameworkdemo.net.ApiException;
import com.example.zhao.frameworkdemo.net.progress.IProgressDialogCancleListener;
import com.example.zhao.frameworkdemo.net.progress.ProgressDialog;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.io.NotSerializableException;
import java.net.SocketException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public class RxSubscriber<T> extends Subscriber<T> {

    private Context context;
    private ProgressDialog progressDialog;
    private ISubscriberListener iSubscriberListener;

    public RxSubscriber(Context context, ISubscriberListener iSubscriberListener,boolean isOutSizeCancleAble) {
        this.context = context;
        this.progressDialog = new ProgressDialog(context, new IProgressDialogCancleListener() {
            @Override
            public void cancleDialog() {
                unsubscribe();
            }
        },isOutSizeCancleAble);
        this.iSubscriberListener = iSubscriberListener;
    }

    public RxSubscriber(ISubscriberListener iSubscriberListener) {
        this.iSubscriberListener = iSubscriberListener;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(context!=null){
            progressDialog.ShowProgress();
        }
    }

    @Override
    public void onCompleted() {
        if(context!=null){
            progressDialog.DismissProgress();
        }
    }

    @Override
    public void onError(Throwable e) {
        if(context!=null){
            progressDialog.DismissProgress();
        }

        if (e instanceof SocketException) {
            onException(ApiException.CONNECT_ERROR);
        } else if (e instanceof HttpException) {
            // HTTP错误
            onException(ApiException.BAD_NETWORK);
        } else if (e instanceof UnknownHostException) {
            // 无法解析该域名
            onException(ApiException.UNKNOWNHOST_ERROR);
        } else if (e instanceof InterruptedIOException) {
            // 连接超时
            onException(ApiException.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException || e instanceof JSONException ||
                e instanceof ParseException || e instanceof JsonSerializer ||
                e instanceof NotSerializableException) {
            // 解析错误
            onException(ApiException.PARSE_ERROR);
        } else {
            onException(ApiException.UNKNOWN_ERROR);
        }
    }

    @Override
    public void onNext(T t) {

        BaseResult baseResult = (BaseResult) t;
        if(baseResult.getCode().equals("400")){
            onException(ApiException.DATA_ERROR,baseResult.getMessage());
        }else{
            iSubscriberListener.onSuccess(t);
        }
    }

    private void onException(@ApiException.Error String reason,String... note){
        if(reason.equals(ApiException.DATA_ERROR)){
            iSubscriberListener.onFail(note[0]);
        }else{
            iSubscriberListener.onFail(ApiException.getStatusDesc(reason));
        }
    }

}
