package com.example.zhao.frameworkdemo.net.interceptor;

import android.util.Log;

import com.example.zhao.frameworkdemo.BuildConfig;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by MrLee on 15/10/15.
 * 拦截器
 */
public class HttpInterceptor implements Interceptor {
    private final Charset UTF8 = Charset.forName("UTF-8");
    private String header;
    private HttpInterceptor() {

    }
    private static HttpInterceptor httpInterceptor = null;
    public static synchronized HttpInterceptor getInstance(){
        if (httpInterceptor == null){
                httpInterceptor =  new HttpInterceptor();
        }
        return httpInterceptor;
    }

    public void setHeader(String header){
        this.header = header;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request()
                .newBuilder()
                .addHeader("humpJsonStyle", "true")
                .addHeader("loginToken",header)
                .build();

        Response response = chain.proceed(request);
        if(BuildConfig.BUILD_TYPE.equals("debug")){
            RequestBody requestBody = request.body();
            String body = null;
            if (requestBody != null) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                body = buffer.readString(charset);
            }
            long startNs = System.nanoTime();

            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
            ResponseBody responseBody = response.body();
            String rBody = null;
            if (HttpEngine.hasBody(response)) {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        e.printStackTrace();
                    }
                }
                rBody = buffer.clone().readString(charset);
            }
            Log.e("EYM","收到响应 "+response.code()+" "+response.message()+"  \n耗时:"+tookMs+
                    "\nmethod:" + request.method()+"\nheaders: "+request.headers()+"  \n请求url:"+response.request().url()+
                    "\n请求body:"+body+"\n响应body："+rBody+"\n");
        }
        return response;
    }
}
