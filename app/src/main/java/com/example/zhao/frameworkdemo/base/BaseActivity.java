package com.example.zhao.frameworkdemo.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.zhao.frameworkdemo.R;
import com.example.zhao.frameworkdemo.base.mvp_interface.IBaseView;

import javax.inject.Inject;

public class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView{

    @Inject
    public P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.onDestory();
        }
        presenter = null;
    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public void hindErrorPage() {

    }

    @Override
    public void showMsg(String msg) {
        if(msg!=null) Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
