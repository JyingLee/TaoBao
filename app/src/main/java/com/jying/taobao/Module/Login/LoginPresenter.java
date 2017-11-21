package com.jying.taobao.Module.Login;

/**
 * Created by Jying on 2017/11/20.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mView;

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}
