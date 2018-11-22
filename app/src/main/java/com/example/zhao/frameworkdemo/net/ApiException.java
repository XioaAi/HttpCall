package com.example.zhao.frameworkdemo.net;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ApiException {
    /**
     * 解析数据失败
     **/
    public static final String PARSE_ERROR = "parse_error";

    /**
     * 网络问题
     **/
    public static final String BAD_NETWORK = "bad_network";

    /**
     * 未知主机错误
     */
    public static final String UNKNOWNHOST_ERROR = "unknownhost_error";

    /**
     * 连接错误
     **/
    public static final String CONNECT_ERROR = "connect_error";

    /**
     * 连接超时
     **/
    public static final String CONNECT_TIMEOUT = "connect_timeout";
    /**
     * baseEntity中的状态码非成功
     **/
    public static final String DATA_ERROR = "data_error";
    /**
     * 未知错误
     **/
    public static final String UNKNOWN_ERROR = "unknown_error";


    public static String getStatusDesc(@Error String errorInfo) {
        String desc = "";
        switch (errorInfo){
            case PARSE_ERROR:
                desc = "解析服务器响应数据失败";
                break;
            case BAD_NETWORK:
                desc = "服务器异常";
                break;
            case UNKNOWNHOST_ERROR:
                desc = "无法解析该主机域名";
                break;
            case CONNECT_ERROR:
                desc = "网络连接失败,请检查网络";
                break;
            case UNKNOWN_ERROR:
                desc = "未知错误";
                break;
            case CONNECT_TIMEOUT:
                desc = "连接超时,请稍后再试";
                break;
            case DATA_ERROR:
                desc = "";
                break;
        }
        return desc;
    }


    @StringDef({
            PARSE_ERROR, BAD_NETWORK,UNKNOWNHOST_ERROR,CONNECT_ERROR,CONNECT_TIMEOUT,DATA_ERROR,UNKNOWN_ERROR
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Error {

    }
}
