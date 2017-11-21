package com.jying.taobao.Module.Weitao;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jying.taobao.R;

/**
 * Created by Jying on 2017/11/21.
 */

public class WeitaoFragment extends Fragment {
    public static WeitaoFragment newInstance() {
        WeitaoFragment fragment = new WeitaoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weitao, container, false);
    }
}
