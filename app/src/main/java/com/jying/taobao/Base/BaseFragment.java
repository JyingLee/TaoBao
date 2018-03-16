package com.jying.taobao.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jying.taobao.TaoBaoApplication;
import com.jying.taobao.Utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * Created by Jying on 2017/11/19.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView<P> {
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
        View root = inflater.inflate(bindLayout(), container, false);
        ButterKnife.bind(this,root);
        initView();
        initData(savedInstanceState);
        initEvent();
        return root;
    }

    @Override
    public void showToast(CharSequence s) {
        ToastUtils.shortToast(TaoBaoApplication.getAppContext(), s);
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroyView();
    }

    protected abstract void initView();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initEvent();

    protected abstract int bindLayout();

    protected abstract P createPresenter();
}
