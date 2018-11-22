package com.example.zhao.frameworkdemo.net;

import com.example.zhao.frameworkdemo.entities.LoginReqEntity;
import com.example.zhao.frameworkdemo.entities.LoginResEntity;


import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface API {

    @POST("loginInfo")
    Observable<LoginResEntity> login(@Body LoginReqEntity loginReqEntity);

}
