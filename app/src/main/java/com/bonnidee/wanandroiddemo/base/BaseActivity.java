package com.bonnidee.wanandroiddemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initInjector();
        bindView();
        initData();
    }

    protected abstract void initData();

    protected abstract void bindView();

    private void initInjector() {


    }

    public abstract int getLayoutId();
}
