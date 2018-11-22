package com.example.zhao.frameworkdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhao.frameworkdemo.APP;
import com.example.zhao.frameworkdemo.R;
import com.example.zhao.frameworkdemo.appdi.module.ActivityModule;
import com.example.zhao.frameworkdemo.appdi.module.LoginModule;
import com.example.zhao.frameworkdemo.base.BaseActivity;
import com.example.zhao.frameworkdemo.entities.LoginResEntity;
import com.example.zhao.frameworkdemo.mvp.login.ILoginContract;
import com.example.zhao.frameworkdemo.mvp.login.LoginPresenter;


public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContract.ILoginView<LoginResEntity> {

    private EditText mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        APP.getApp(this).apiComponent.login(new LoginModule(this),new ActivityModule(this)).inject(this);
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login();
            }
        });
    }


    @Override
    public String getEmail() {
        return mEmailView.getText().toString();
    }

    @Override
    public String getPass() {
        return mPasswordView.getText().toString();
    }

    @Override
    public void onNext(LoginResEntity loginResEntity) {
        Toast.makeText(this,loginResEntity.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(int code, String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}

