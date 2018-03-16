package com.jying.taobao.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Jying on 2018/3/16.
 */

public class LoadMoreRecyclerView extends RecyclerView {
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading = false;
    private int page = 1;
    private int lastPage = 1;

    public LoadMoreRecyclerView(Context context) {
        super(context);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        int lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        int totalItemCount = getLayoutManager().getItemCount();

        if (lastVisibleItem == totalItemCount - 1 && dy > 0) {
            if (!isLoading && page < lastPage) {
                isLoading = true;
                onLoadMoreListener.onLoadMore();
            }
        }
    }

    public void setPage(int page, int lastPage) {
        this.page = page;
        this.lastPage = lastPage;
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public void setOnLoadMoreListener(final OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
