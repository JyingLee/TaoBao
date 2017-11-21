package com.jying.taobao.Base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jying.taobao.Utils.ToastUtils;

/**
 * Created by Jying on 2017/11/19.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView<P> {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(bindLayout());
        mPresenter = createPresenter();
        if (mPresenter != null) {
            // 调用Presenter初始化方法
            mPresenter.onStart();
        }

        initView();
        initData(savedInstanceState);
        initEvent();

    }

    @Override
    public void showToast(CharSequence s) {
        ToastUtils.shortToast(this, s);
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
        Log.e("test", "实现BasePresenter的setPresenter方法");
    }

    protected abstract void initEvent();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract P createPresenter();

    protected abstract int bindLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
