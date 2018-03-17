package com.jying.taobao.Module.Weitao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jying.taobao.R;

/**
 * Created by Jying on 2018/3/17.
 */

public class GuanzhuFragment extends Fragment {

    public static GuanzhuFragment getInstance() {
        GuanzhuFragment guanzhuFragment = new GuanzhuFragment();
        Bundle bundle = new Bundle();
        guanzhuFragment.setArguments(bundle);
        return guanzhuFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weibao_guanzhu, container, false);
        return view;
    }
}
