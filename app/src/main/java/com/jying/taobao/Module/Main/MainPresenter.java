package com.jying.taobao.Module.Main;

/**
 * Created by Jying on 2017/11/20.
 */

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}
