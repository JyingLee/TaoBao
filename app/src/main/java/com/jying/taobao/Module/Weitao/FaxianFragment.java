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

public class FaxianFragment extends Fragment {

    public static FaxianFragment getInstance() {
        FaxianFragment faxianFragment = new FaxianFragment();
        Bundle bundle = new Bundle();
        faxianFragment.setArguments(bundle);
        return faxianFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weibao_faxian, container,false);
        return view;
    }
}
