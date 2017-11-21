package com.jying.taobao.Module.Gouwuche;

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

public class GouwucheFragment extends Fragment {

    public static GouwucheFragment newInstance() {
        GouwucheFragment fragment = new GouwucheFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gouwuche, container, false);
    }
}
