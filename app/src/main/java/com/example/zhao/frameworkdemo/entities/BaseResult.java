package com.example.zhao.frameworkdemo.entities;

import java.io.Serializable;

public class BaseResult implements Serializable {

	private String message; // 返回内容

	private String code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
