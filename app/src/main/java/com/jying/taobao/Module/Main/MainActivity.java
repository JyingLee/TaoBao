package com.jying.taobao.Module.Main;

import android.graphics.Color;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.jying.taobao.Adapter.ViewpagerAdapter;
import com.jying.taobao.Base.BaseActivity;
import com.jying.taobao.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {
    @BindView(R.id.bottom_nav)
    AHBottomNavigation ahBottomNavigation;
    @BindView(R.id.activity_viewpager)
    AHBottomNavigationViewPager viewPager;
    private List<AHBottomNavigationItem> bottomLists = new ArrayList<>();
    ViewpagerAdapter viewpagerAdapter;


    @Override
    protected void initView() {
        ButterKnife.bind(this);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.nav_home, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.weiTao, R.drawable.navtab_we, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.xiaoxi, R.drawable.navtab_help, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.gouwuche, R.drawable.navtab_cart, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.wodetaobao, R.drawable.navtab_user, R.color.colorBottomNavigationActiveColored);
        bottomLists.add(item1);
        bottomLists.add(item2);
        bottomLists.add(item3);
        bottomLists.add(item4);
        bottomLists.add(item5);
        ahBottomNavigation.addItems(bottomLists);
        ahBottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        ahBottomNavigation.setAccentColor(Color.parseColor("#FF5000"));//点击后的颜色
        ahBottomNavigation.setInactiveColor(Color.parseColor("#949494"));//默认每个item颜色
        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position, false);
                return true;
            }
        });
        viewPager.setOffscreenPageLimit(5);
        viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewpagerAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

}
