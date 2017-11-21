package com.jying.taobao.Module.Wendajia;

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

public class WendajiaFragment extends Fragment {

    public static WendajiaFragment newInstance() {
        WendajiaFragment fragment = new WendajiaFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wendajia, container, false);
        return view;
    }
}
