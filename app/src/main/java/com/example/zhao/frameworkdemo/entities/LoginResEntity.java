package com.example.zhao.frameworkdemo.entities;

public class LoginResEntity extends BaseResult{
    private String name;

    @Override
    public String toString() {
        return "LoginResEntity{" +
                ", name='" + name + '\'' +
                '}';
    }

    public LoginResEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
