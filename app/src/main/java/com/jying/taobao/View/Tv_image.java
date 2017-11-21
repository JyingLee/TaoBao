package com.jying.taobao.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jying.taobao.R;

/**
 * Created by Jying on 2017/11/9.
 */

public class Tv_image extends LinearLayout {
    ImageView image;
    TextView tv;

    public Tv_image(Context context) {
        super(context);
        init(context);
    }

    public Tv_image(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Tv_image(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setGravity(Gravity.CENTER);
        setPadding(5, 5, 5, 5);
        View view = LayoutInflater.from(context).inflate(R.layout.view_tv_image, this, true);
        image = (ImageView) view.findViewById(R.id.view_image);
        tv = (TextView) view.findViewById(R.id.view_tv);
    }

    public void setText(String s) {
        tv.setText(s);
    }

    public void setImage(int src) {
        image.setImageResource(src);
    }

    public void setTextColor(int color) {
        tv.setTextColor(color);
    }
}
