package com.jying.taobao.Module.Weitao;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.jying.taobao.Adapter.WeitaoFragmentAdapter;
import com.jying.taobao.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jying on 2017/11/21.
 */

public class WeitaoFragment extends Fragment {
    @BindView(R.id.weitao_pst)
    PagerSlidingTabStrip pst;
    @BindView(R.id.weibao_viewpager)
    ViewPager viewPager;

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
        View view = inflater.inflate(R.layout.fragment_weitao, container, false);
        ButterKnife.bind(this, view);
        viewPager.setAdapter(new WeitaoFragmentAdapter(getActivity().getSupportFragmentManager()));
        pst.setTextSize(30);
        pst.setIndicatorHeight(5);
        pst.setDividerColorResource(R.color.colorPrimary);
        pst.setUnderlineHeight(0);
        pst.setUnderlineColorResource(R.color.colorPrimary);
        pst.setIndicatorColorResource(R.color.bg_main_color);
        pst.setTextColorResource(R.color.bg_main_color);
        pst.setViewPager(viewPager);
        return view;
    }
}
