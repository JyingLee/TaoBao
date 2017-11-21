package com.jying.taobao.Base;

/**
 * Created by Jying on 2017/11/19.
 */

public interface BaseView<P> {
    void setPresenter(P presenter);
    void showToast(CharSequence s);
}
