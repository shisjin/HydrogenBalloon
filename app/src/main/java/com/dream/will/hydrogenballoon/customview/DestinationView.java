package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.Destinations;
import com.dream.will.hydrogenballoon.content.DestinationConstent;
import com.dream.will.hydrogenballoon.inter.OnDestinationClickListener;
import com.dream.will.hydrogenballoon.utils.DisplayUtil;

import java.util.List;



public class DestinationView extends HorizontalScrollView implements View.OnClickListener {
    private Context context;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams childParams;
    private int childViewWidth;
    private OnDestinationClickListener listener;

    public DestinationView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public DestinationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        setHorizontalScrollBarEnabled(false);
        HorizontalScrollView.LayoutParams layoutParams = new HorizontalScrollView.LayoutParams(HorizontalScrollView.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(linearLayout, layoutParams);
//        int pxW = DisplayUtil.dip2px(context, 70);
//        int pxH = DisplayUtil.dip2px(context, 90);
        childViewWidth = DisplayUtil.dip2px(context, 100);
        childParams = new LinearLayout.LayoutParams(childViewWidth, LinearLayout.LayoutParams.WRAP_CONTENT);


    }

    public void setData(List<Destinations.DataBean.SectionsBean.ModelsBean> models) {
        linearLayout.setWeightSum(models.size());
        for (Destinations.DataBean.SectionsBean.ModelsBean modelsBean : models) {
            ChildView childView = new ChildView(context);
            childView.setTag(modelsBean.getId());
//            childView.setTag(R.string.destination_name,modelsBean.getName());
            childView.setTv_Title(modelsBean.getName());
            childView.setTv_Title_en(modelsBean.getName_en());
            childView.setImg(modelsBean.getPhoto_url());
            childView.setOnClickListener(this);
            linearLayout.addView(childView, childParams);
        }
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(DestinationConstent.SECTIONS_DESTINATION, v);
        }
    }

    public void setListener(OnDestinationClickListener listener) {
        this.listener = listener;
    }

    class ChildView extends LinearLayout {
        private TextView tv_Title;
        private TextView tv_Title_en;
        private ImageView iv;
        private final int TV_EN = 1;
        private final int TV_NORMAL = 2;

        public ChildView(Context context, AttributeSet attrs) {
            super(context, attrs);
            initLinerLayout();
        }

        public ChildView(Context context) {
            super(context);
            initLinerLayout();
        }

        private void initLinerLayout() {
            setOrientation(VERTICAL);
            setGravity(Gravity.CENTER);
            setPadding(5, 5, 5, 5);
//            int titleSize = DisplayUtil.px2sp(context,16);
//            int en_titleSize = DisplayUtil.px2sp(context,14);
            LayoutParams iv_params = new LayoutParams(childViewWidth, childViewWidth);
            LayoutParams tv_params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            tv_Title = createTextView(TV_NORMAL);
            tv_Title_en = createTextView(TV_EN);
            iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            tv_params.setMargins(0, 5, 0, 0);
            addView(iv, iv_params);
            addView(tv_Title, tv_params);
            tv_params.setMargins(0, 0, 0, 0);
            addView(tv_Title_en, tv_params);
        }

        private TextView createTextView(int type) {
            TextView tv = new TextView(context);
            tv.setGravity(Gravity.CENTER);
            tv.setMaxLines(1);
            tv.setEllipsize(TextUtils.TruncateAt.END);
            if (type == TV_EN) {
                tv.setTextSize(12);
            } else {
                tv.setTextSize(14);
            }
            return tv;
        }

        public void setTv_Title(String title) {
            tv_Title.setText(title);
        }

        public void setTv_Title_en(String title_en) {
            tv_Title_en.setText(title_en);
        }

        public void setImg(String url) {
            Glide.with(context).load(url).asBitmap().dontAnimate().placeholder(R.color.picture_placeholder).into(iv);
        }

    }
}
