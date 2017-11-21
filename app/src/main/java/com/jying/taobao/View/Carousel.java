package com.jying.taobao.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jying.taobao.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Jying on 2017/11/13.
 */

public class Carousel extends RelativeLayout {
    private static final int RMP = LayoutParams.MATCH_PARENT;
    private static final int RWP = LayoutParams.WRAP_CONTENT;
    private static final int LWC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private static final int AUTO_PLAY = 0x123;//message.what
    public static final int CENTER = 0x0;
    public static final int LEFT = 0x1;
    public static final int RIGHT = 0X2;//指示点布局的位置

    private LinearLayout mPointContainerLl;//指示器的布局
    private ViewPager mViewPager;
    private List<Integer> mImages;//本体图片
    private List<String> mImageUrls;//网络图片
    private boolean mIsImageUrl = false;//是否是网络图片
    private boolean mIsOneImag = false;//是否只有一张图片
    private boolean mIsAutoPlay = true;//是否可以自动播放
    private boolean mIsAutoPlaying = false;//是否正在播放
    private int mAutoPlayTime = 3000;//自动播放时间
    private int mCurrentPositon;//当前页面位置
    private int mPointPosition = CENTER;//指示器位置
    private int mPointDrawable = R.drawable.selector_carousel;//指示点的图片资源
    private Drawable mPointContainerBg;//指示器布局的背景
    private LayoutParams mPointContaninerRule;//指示器布局规则
    private TextView mTips;//提示语
    private List<String> mTipsDatas;
    private boolean mPointsIsVisible = true;//指示点是否可见

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mCurrentPositon++;
            mViewPager.setCurrentItem(mCurrentPositon);
            handler.sendEmptyMessageDelayed(AUTO_PLAY, mAutoPlayTime);
        }
    };

    public Carousel(Context context) {
        this(context, null);
    }

    public Carousel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Carousel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Carousel);
        mPointsIsVisible = a.getBoolean(R.styleable.Carousel_points_visibility, true);
        mPointPosition = a.getInt(R.styleable.Carousel_points_position, CENTER);
        mPointContainerBg = a.getDrawable(R.styleable.Carousel_points_container_bg);
        a.recycle();
        setLayout(context);
    }

    private void setLayout(Context context) {
        //关闭view的OverScroll
        setOverScrollMode(OVER_SCROLL_NEVER);
        //设置指示器背景
        if (mPointContainerBg == null) {
            mPointContainerBg = new ColorDrawable(Color.parseColor("#00aaaaaa"));
        }
        //添加ViewPager
        mViewPager = new ViewPager(context);
        addView(mViewPager, new LayoutParams(RMP, RMP));
        //设置指示器背景容器
        RelativeLayout pointContainerRl = new RelativeLayout(context);
        if (Build.VERSION.SDK_INT >= 16) {
            pointContainerRl.setBackground(mPointContainerBg);
        } else {
            pointContainerRl.setBackgroundDrawable(mPointContainerBg);
        }
        //设置内边距
        pointContainerRl.setPadding(20, 10, 40, 10);
        //设定指示器容器布局及位置
        LayoutParams pointContainerLp = new LayoutParams(RMP, RWP);
        pointContainerLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(pointContainerRl, pointContainerLp);//在总布局李添加一个RelativeLayout
        //设置指示器容器    在relativeLayout里面嵌套一个LinearLayout
        mPointContainerLl = new LinearLayout(context);
        mPointContainerLl.setOrientation(LinearLayout.HORIZONTAL);
        mPointContaninerRule = new LayoutParams(RWP, RWP);
        mPointContaninerRule.bottomMargin = 7;
        pointContainerRl.addView(mPointContainerLl, mPointContaninerRule);//在relativeLayout里添加LinearLayout
        //设置指示器容器是否可见
        if (mPointContainerLl != null) {
            if (mPointsIsVisible) {
                mPointContainerLl.setVisibility(View.VISIBLE);
            } else {
                mPointContainerLl.setVisibility(View.GONE);
            }
        }
        //设置指示器布局位置
        if (mPointPosition == CENTER) {
            mPointContaninerRule.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else if (mPointPosition == LEFT) {
            mPointContaninerRule.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else if (mPointPosition == RIGHT) {
            mPointContaninerRule.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
    }

    /**
     * 设置本地图片
     *
     * @param images
     */
    public void setImages(List<Integer> images) {
        //加载本地图片
        mIsImageUrl = false;
        this.mImages = images;
        if (images.size() <= 1)
            mIsOneImag = true;
        //初始化ViewPager
        initViewPager();
    }

    /**
     * 设置网络图片
     *
     * @param urls
     */
    public void setImagesUrl(List<String> urls) {
        //加载网络图片
        mIsImageUrl = true;
        this.mImageUrls = urls;
        if (urls.size() <= 1)
            mIsOneImag = true;
        //初始化ViewPager
        initViewPager();
    }

    private void initViewPager() {
        //当图片多于1张时添加指示点
        if (!mIsOneImag) {
            addPoints();//根据图片数量添加指示点
        }
        //设置ViewPager
        CarouselAdapter adapter = new CarouselAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener); //添加监听器
        //跳转到首页
        mViewPager.setCurrentItem(1, false);
        //当图片多于1张时开始轮播
        if (!mIsOneImag) {
            startAutoPlay();
        }
    }

    /**
     * 开始播放
     */
    public void startAutoPlay() {
        if (mIsAutoPlay && !mIsAutoPlaying) {
            mIsAutoPlaying = true;
            handler.sendEmptyMessageDelayed(AUTO_PLAY, mAutoPlayTime);
        }
    }

    /**
     * 添加指示点
     */
    private void addPoints() {
        mPointContainerLl.removeAllViews();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LWC, LWC);
        lp.setMargins(10, 10, 10, 10);
        ImageView imageView;
        int length = mIsImageUrl ? mImageUrls.size() : mImages.size();
        for (int i = 0; i < length; i++) {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(lp);
            imageView.setImageResource(mPointDrawable);
            mPointContainerLl.addView(imageView);
        }

        switchToPoint(0);
    }

    /**
     * 设置指示点是否可见
     *
     * @param isVisible
     */
    public void setPointsIsVisible(boolean isVisible) {
        if (mPointContainerLl != null) {
            if (isVisible) {
                mPointContainerLl.setVisibility(View.VISIBLE);
            } else {
                mPointContainerLl.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 对应三个位置 CENTER,RIGHT,LEFT
     *
     * @param position
     */
    public void setPoinstPosition(int position) {
        //设置指示器布局位置
        if (position == CENTER) {
            mPointContaninerRule.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else if (position == LEFT) {
            mPointContaninerRule.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else if (position == RIGHT) {
            mPointContaninerRule.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
    }

    private class CarouselAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            //当只有一张图片时返回1
            if (mIsOneImag) {
                return 1;
            }
            //当为网络图片，返回网页图片长度
            if (mIsImageUrl)
                return mImageUrls.size() + 2;
            //当为本地图片，返回本地图片长度
            return mImages.size() + 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(toRealPosition(position));
                    }
                }
            });
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (mIsImageUrl) {
                Picasso.with(getContext())
                        .load(mImageUrls.get(toRealPosition(position)))
                        .into(imageView);
            } else {
                imageView.setImageResource(mImages.get(toRealPosition(position)));
            }
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            if (object != null)
                object = null;
        }
    }

    /**
     * 添加viewpager的监听器
     */
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (mIsImageUrl) {
                mCurrentPositon = position % (mImageUrls.size() + 2);
            } else {
                mCurrentPositon = position % (mImages.size() + 2);
            }
            switchToPoint(toRealPosition(mCurrentPositon));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int current = mViewPager.getCurrentItem();
                int lastReal = mViewPager.getAdapter().getCount() - 2;
                if (current == 0) {
                    mViewPager.setCurrentItem(lastReal, false);
                } else if (current == lastReal + 1) {
                    mViewPager.setCurrentItem(1, false);
                }
            }
        }
    };

    /**
     * 切换指示器
     *
     * @param currentPoint
     */
    private void switchToPoint(final int currentPoint) {
        for (int i = 0; i < mPointContainerLl.getChildCount(); i++) {
            mPointContainerLl.getChildAt(i).setEnabled(false);
        }
        mPointContainerLl.getChildAt(currentPoint).setEnabled(true);

    }


    /**
     * 返回真实的位置
     *
     * @param position
     * @return
     */
    private int toRealPosition(int position) {
        int realPosition;
        if (mIsImageUrl) {
            realPosition = (position - 1) % mImageUrls.size();
            if (realPosition < 0)
                realPosition += mImageUrls.size();
        } else {
            realPosition = (position - 1) % mImages.size();
            if (realPosition < 0)
                realPosition += mImages.size();
        }

        return realPosition;
    }

    /**
     * 停止播放
     */
    public void stopAutoPlay() {
        if (mIsAutoPlay && mIsAutoPlaying) {
            mIsAutoPlaying = false;
            handler.removeMessages(AUTO_PLAY);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsAutoPlay && !mIsOneImag) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    stopAutoPlay();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_OUTSIDE:
                    startAutoPlay();
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
