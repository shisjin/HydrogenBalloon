package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.content.DestinationConstent;
import com.dream.will.hydrogenballoon.inter.OnDestinationClickListener;


public class CollectionView extends FrameLayout implements View.OnClickListener {
    private ImageView iv;
    private TextView tv_title;
    private TextView tv_subtitle;
    private Context context;
    private OnDestinationClickListener listener;

    public CollectionView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CollectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        setOnClickListener(this);
        iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        FrameLayout.LayoutParams params = new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        tv_title = new TextView(context);
        tv_title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tv_title.setTextColor(Color.WHITE);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        titleParams.setMargins(0, 0, 0, 5);
        tv_subtitle = new TextView(context);
        tv_subtitle.setTextColor(Color.WHITE);
        layout.addView(tv_title, titleParams);
        layout.addView(tv_subtitle, titleParams);
        addView(iv, params);
        addView(layout, params);
    }

    public void setTv_title(String title) {
        if (tv_title != null) {
            tv_title.setText(title);
        }
    }

    public void setTv_titleSize(float size) {
        tv_title.setTextSize(size);
    }

    public void setTv_subtitle(String subtitle) {
        if (tv_subtitle != null) {
            tv_subtitle.setText(subtitle);
        }
    }

    public void setTv_subtitleSize(float size) {
        tv_subtitle.setTextSize(size);
    }

    public void setImg(String url) {
        Glide.with(context).load(url).asBitmap().dontAnimate().placeholder(R.color.picture_placeholder).into(iv);
    }

    public void setListener(OnDestinationClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(DestinationConstent.SECTIONS_COLLECTION, v);
        }
    }
}
