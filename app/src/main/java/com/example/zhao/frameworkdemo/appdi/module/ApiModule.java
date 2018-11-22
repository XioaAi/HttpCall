package com.example.zhao.frameworkdemo.appdi.module;

import com.example.zhao.frameworkdemo.BuildConfig;
import com.example.zhao.frameworkdemo.net.API;
import com.example.zhao.frameworkdemo.constant.APPConfig;
import com.example.zhao.frameworkdemo.net.interceptor.HttpInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AppModule.class)// includes    相当于模块与模块之间的依赖
public class ApiModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(String baseUrl, Gson gson, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public String provideBaseUrl(){
        return BuildConfig.HOST_URL+"/";
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Interceptor interceptor){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(APPConfig.NETCONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(APPConfig.NETREREAD_TIMEOUT,TimeUnit.SECONDS);
        if(interceptor!=null) builder.addInterceptor(interceptor);//添加请求头
        return builder.build();
    }

    @Provides
    @Singleton
    public Interceptor provideInterceptor(String header){
        HttpInterceptor interceptor = HttpInterceptor.getInstance();
        interceptor.setHeader(header);
        return interceptor;
    }

    @Provides
    @Singleton
    API provideApiService(Retrofit retrofit) {
        return retrofit.create(API.class);
    }

}
