package com.example.zhao.frameworkdemo.entities;

import java.io.Serializable;

/**
 * Created by Azhao on 2017/8/28.
 */

public class BaseReqEntity implements Serializable{
    private String platform = "Android";
    private String  deviceToken;
    private String appVersion;

    public String getPlatForm() {
        return platform;
    }

    public void setPlatForm(String platform) {
        this.platform = platform;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
