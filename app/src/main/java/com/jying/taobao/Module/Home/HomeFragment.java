package com.jying.taobao.Module.Home;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.widget.TextView;

import com.jying.taobao.Adapter.HomeRecyclewViewAdapter;
import com.jying.taobao.Base.BaseFragment;
import com.jying.taobao.Bean.HomeBase;
import com.jying.taobao.Bean.HomeBottom;
import com.jying.taobao.Bean.HomeTop;
import com.jying.taobao.R;
import com.jying.taobao.Utils.Type;
import com.jying.taobao.View.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by Jying on 2017/11/21.
 */

public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener,
        LoadMoreRecyclerView.OnLoadMoreListener {

    @BindView(R.id.home_recyclewview)
    LoadMoreRecyclerView recyclerView;
    @BindView(R.id.home_refreshLayout)
    SwipeRefreshLayout refreshLayout;
    Context context;
    HomeRecyclewViewAdapter adapter;
    private List<HomeBase> lists = new ArrayList<>();
    private HomeBase footerItem = new HomeBase();
    private int page = 1;
    @BindView(R.id.tv_search_tips)
    TextView tip;
    private CountDownTimer timer;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    protected void initView() {
        context = getActivity();
        refreshLayout.setColorSchemeResources(R.color.font_orange_color);
        refreshLayout.setOnRefreshListener(this);
        initRecyclewView();
    }

    private void initRecyclewView() {
        int spanCount = getResources().getInteger(R.integer.grid_span_count);
        adapter = new HomeRecyclewViewAdapter(context, lists);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        recyclerView.setAdapter(adapter);
        recyclerView.setOnLoadMoreListener(this);
        footerItem.setType(Type.TYPE_FOOTER_LOAD);
        footerItem.setSpanCount(spanCount);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        final List<String> tips = new ArrayList<>();
        tips.add("JyingLee");
        tips.add("github.com/JyingLee");
        timer = new CountDownTimer(3000000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tip.setText(tips.get(new Random().nextInt(2)));
            }

            @Override
            public void onFinish() {

            }
        };
        if (adapter != null) {
            adapter.setCarouselImage(mPresenter.getCarouselImages());
        }
        mPresenter.getTopLists();
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onRefresh() {
        setRefreshLoading(false);
        page = 1;
        mPresenter.getTopLists();
    }

    @Override
    public void showTop(HomeTop homeTop) {
        lists.clear();
        adapter.setHeadlineList(homeTop.getHeadlines());
        lists.addAll(homeTop.getList());
        lists.add(footerItem);
        adapter.notifyDataSetChanged();
        mPresenter.getBottomLists(page);
    }

    @Override
    public void showBottom(HomeBottom homeBottom) {
        recyclerView.setPage(page, homeBottom.getTotalPage());
        footerItem.setType(page < homeBottom.getTotalPage() ? Type.TYPE_FOOTER_LOAD : Type.TYPE_FOOTER_FULL);
        lists.addAll(lists.size() - 1, homeBottom.getData());
        adapter.notifyDataSetChanged();
        setRefreshLoading(false);
    }

    /**
     * 设置刷新和加载更多的状态
     *
     * @param isLoading 加载更多状态
     */
    private void setRefreshLoading(boolean isLoading) {
        if (!isLoading) {
            recyclerView.setLoading(false);
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoadMore() {
        setRefreshLoading(true);
        page++;
        mPresenter.getBottomLists(page);
    }

    @Override
    public void onStart() {
        super.onStart();
        timer.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }
}
