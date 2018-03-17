package com.jying.taobao.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jying.taobao.Module.Gouwuche.GouwucheFragment;
import com.jying.taobao.Module.Home.HomeFragment;
import com.jying.taobao.Module.Me.MeFragment;
import com.jying.taobao.Module.Weitao.WeitaoFragment;
import com.jying.taobao.Module.Xiaoxi.XiaoxiFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jying on 2017/11/21.
 */

public class ViewpagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments = new ArrayList<>();


    public ViewpagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        fragments.clear();
        fragments.add(HomeFragment.newInstance());
        fragments.add(WeitaoFragment.newInstance());
        fragments.add(XiaoxiFragment.newInstance());
        fragments.add(GouwucheFragment.newInstance());
        fragments.add(MeFragment.newInstance());
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
