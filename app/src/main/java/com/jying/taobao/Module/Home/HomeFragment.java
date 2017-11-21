package com.jying.taobao.Module.Home;

import android.os.Bundle;

import com.jying.taobao.Base.BaseFragment;
import com.jying.taobao.R;

/**
 * Created by Jying on 2017/11/21.
 */

public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }
}
