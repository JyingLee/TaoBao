package com.jying.taobao.Module.Home;

/**
 * Created by Jying on 2017/11/21.
 */

public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View mView;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}
