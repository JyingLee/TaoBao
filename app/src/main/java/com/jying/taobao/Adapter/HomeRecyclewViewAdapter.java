package com.jying.taobao.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jying.taobao.Bean.HomeBase;
import com.jying.taobao.R;
import com.jying.taobao.Utils.Type;
import com.jying.taobao.View.Carousel;
import com.jying.taobao.View.FooterLoading;
import com.oushangfeng.marqueelayout.MarqueeLayout;
import com.oushangfeng.marqueelayout.MarqueeLayoutAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jying on 2017/11/23.
 */

public class HomeRecyclewViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> carouselImag = new ArrayList<>();
    private List<HomeBase> lists = new ArrayList<>();
    private List<HomeBase> headlineList;
    private SpanSizeLookup spanSizeLookup = new SpanSizeLookup();
    private LayoutInflater inflater;

    public HomeRecyclewViewAdapter(Context context, List<HomeBase> lists) {
        this.context = context;
        this.lists = lists;
        inflater = LayoutInflater.from(context);
    }

    public void setHeadlineList(List<HomeBase> headlineList) {
        this.headlineList = headlineList;
    }

    private class SpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        @Override
        public int getSpanSize(int position) {
            return lists.get(position).getSpanCount();
        }
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return spanSizeLookup;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HomeBase.TYPE_CAROUSEL:
                view = inflater.inflate(R.layout.item_home_carousel, parent, false);
                return new CarouselHolder(view);
            case HomeBase.TYPE_CATEGORY:
                view = inflater.inflate(R.layout.item_home_top, parent, false);
                return new CategoryHolder(view);
            case HomeBase.TYPE_HEADLINE:
                view = inflater.inflate(R.layout.item_home_type_headline, parent, false);
                return new HeadlineHolder(view);
            case HomeBase.TYPE_HEADER:
                view = inflater.inflate(R.layout.item_home_type_header, parent, false);
                return new HeaderHolder(view);
            case HomeBase.TYPE_DIVIDER:
                view = inflater.inflate(R.layout.item_home_type_divider, parent, false);
                return new PlaceHolder(view);
            case HomeBase.TYPE_LIVE:
                view = inflater.inflate(R.layout.item_home_type_live, parent, false);
                return new LiveHolder(view);
            case HomeBase.TYPE_HOT:
                view = inflater.inflate(R.layout.item_home_type_hot, parent, false);
                return new CommonHolder(view);
            case HomeBase.TYPE_RECOMMEND:
                view = inflater.inflate(R.layout.item_home_type_recommend, parent, false);
                return new RecommendHolder(view);
            case HomeBase.TYPE_PLACE:
                view = inflater.inflate(R.layout.item_place, parent, false);
                return new PlaceHolder(view);
            default:
                view = inflater.inflate(R.layout.item_footer_loading, parent, false);
                return new FooterHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        HomeBase bean = lists.get(position);
        if (viewHolder instanceof CarouselHolder) {
            CarouselHolder vh_carousel = (CarouselHolder) viewHolder;
            if (carouselImag.size() != 0) {
                vh_carousel.carousel.setImagesUrl(carouselImag);
            }
        } else if (viewHolder instanceof HeadlineHolder) {
            HeadlineHolder holder = (HeadlineHolder) viewHolder;
            MarqueeLayoutAdapter<HomeBase> topAdapter = new MarqueeLayoutAdapter<HomeBase>(headlineList) {
                @Override
                protected int getItemLayoutId() {
                    return R.layout.item_marquee;
                }

                @Override
                protected void initView(View view, int position, HomeBase item) {
                    ((TextView) view).setText(item.getName());

                }
            };
            holder.marqueeLayout.setAdapter(topAdapter);
            holder.marqueeLayout.start();
        } else if (viewHolder instanceof HeaderHolder) {
            HeaderHolder holder = (HeaderHolder) viewHolder;
            holder.tvTitle.setText(bean.getName());
        } else if (viewHolder instanceof LiveHolder) {
            LiveHolder holder = (LiveHolder) viewHolder;
            Glide.with(context).load(bean.getUrl()).into(holder.ivHome);
            holder.tvTitle.setText(bean.getName());
        } else if (viewHolder instanceof CommonHolder) {
            CommonHolder holder = (CommonHolder) viewHolder;
            Glide.with(context).load(bean.getUrl()).into(holder.ivHome);
            holder.tvTitle.setText(bean.getName());
            holder.tvPrice.setText(String.format("%s%s", bean.getCurrency(), bean.getPrice()));
        } else if (viewHolder instanceof RecommendHolder) {
            RecommendHolder holder = (RecommendHolder) viewHolder;
            Glide.with(context).load(bean.getUrl()).into(holder.ivHome);
            holder.tvTitle.setText(bean.getName());
            holder.tvPrice.setText(String.format("%s%s", bean.getCurrency(), bean.getPrice()));
            holder.tvCount.setText(String.format("%d人付款", new Random().nextInt(10000)));

        } else if (viewHolder instanceof FooterHolder) {
            FooterHolder holder = (FooterHolder) viewHolder;
            holder.footerLoading.onLoad(Type.TYPE_FOOTER_FULL == lists.get(position).getType());
        }

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return lists.get(position).getType();
    }

    class CarouselHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_carousel)
        Carousel carousel;

        public CarouselHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }
    }

    public void setCarouselImage(List<String> carouselImages) {
        this.carouselImag = carouselImages;
    }

    class HeadlineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.marquee_layout)
        MarqueeLayout marqueeLayout;

        HeadlineHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class CommonHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home)
        ImageView ivHome;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_price)
        TextView tvPrice;


        CommonHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    class RecommendHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home)
        ImageView ivHome;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_count)
        TextView tvCount;

        RecommendHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    class LiveHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home)
        ImageView ivHome;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        LiveHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class PlaceHolder extends RecyclerView.ViewHolder {
        PlaceHolder(View itemView) {
            super(itemView);
        }
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        CategoryHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.footer)
        FooterLoading footerLoading;

        FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
