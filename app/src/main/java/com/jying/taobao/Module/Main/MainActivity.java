package com.jying.taobao.Module.Main;

import android.os.Bundle;

import com.jying.taobao.Base.BaseActivity;
import com.jying.taobao.R;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }
}
