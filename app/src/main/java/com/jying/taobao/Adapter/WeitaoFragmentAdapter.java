package com.jying.taobao.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jying.taobao.Module.Weitao.FaxianFragment;
import com.jying.taobao.Module.Weitao.GuanzhuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jying on 2018/3/17.
 */

public class WeitaoFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private String[] title = {"发现", "关注"};

    public WeitaoFragmentAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(FaxianFragment.getInstance());
        fragments.add(GuanzhuFragment.getInstance());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
