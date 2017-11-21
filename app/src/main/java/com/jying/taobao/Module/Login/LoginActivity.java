package com.jying.taobao.Module.Login;

import android.os.Bundle;

import com.jying.taobao.Base.BaseActivity;
import com.jying.taobao.R;

/**
 * Created by Jying on 2017/11/20.
 */

public class LoginActivity extends BaseActivity<LoginContract.Presenter>implements LoginContract.View{


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
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }
}
