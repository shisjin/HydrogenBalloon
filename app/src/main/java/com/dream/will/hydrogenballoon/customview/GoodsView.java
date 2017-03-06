package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.Destinations;
import com.dream.will.hydrogenballoon.content.DestinationConstent;
import com.dream.will.hydrogenballoon.inter.OnDestinationClickListener;
import com.dream.will.hydrogenballoon.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Goods :攻略，酒店，机票，自由行，跟团游，门票
 */


public class GoodsView extends ViewPager {
    private Context context;
    private OnDestinationClickListener listener;
    private int goods_type_resId;

    public GoodsView(Context context) {
        super(context);
        this.context = context;
        goods_type_resId = R.string.destination_goods_type;
    }

    public GoodsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        goods_type_resId = R.string.destination_goods_type;
    }

    public void setData(@NonNull List<Destinations.DataBean.GoodsBean> goods) {
        if (goods.size() == 0) {
            return;
        }
        List<View> list = new ArrayList<>();
        GoodView goodView = null;
        for (int i = 0; i <= (goods.size() - 1) / 4; i++) {
            goodView = new GoodView(context);
            int count = goods.size() <= (i + 1) * 4 ? goods.size() : (i + 1) * 4;
            for (int j = i * 4; j < count; j++) {
                goodView.addView(goods.get(j));
            }
            list.add(goodView);
        }

        DestinationGoodsAdapter adapter = new DestinationGoodsAdapter(list);
        setAdapter(adapter);
    }

    public void setListener(OnDestinationClickListener listener) {
        this.listener = listener;
    }

    private class GoodView extends LinearLayout implements OnClickListener {
        private LayoutParams params;

        public GoodView(Context context) {
            super(context);
            init();
        }

        public GoodView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            setBackgroundColor(Color.WHITE);
            params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            setOrientation(HORIZONTAL);
            setWeightSum(4f);
        }

        public void addView(Destinations.DataBean.GoodsBean bean) {
            ChildView childView = new ChildView(context);
            childView.setChildData(bean);
            childView.setOnClickListener(this);
            addView(childView, params);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(((String) v.getTag(goods_type_resId)), v);
            }
        }

        public class ChildView extends LinearLayout {
            private TextView tv_text;
            private ImageView iv_icon;

            public ChildView(Context context) {
                super(context);
                initChild();
            }

            public ChildView(Context context, AttributeSet attrs) {
                super(context, attrs);
                initChild();
            }

            private void initChild() {
                setOrientation(VERTICAL);
                setBackgroundColor(Color.WHITE);
                setGravity(Gravity.CENTER);
                setPadding(0, 10, 0, 10);
                LayoutParams tv_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LayoutParams iv_params = new LinearLayout.LayoutParams(DisplayUtil.dip2px(context, 25), DisplayUtil.dip2px(context, 25));
                iv_params.setMargins(0, 0, 0, 10);
                tv_text = new TextView(context);
                tv_text.setGravity(Gravity.CENTER);
                iv_icon = new ImageView(context);
                iv_icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
                addView(iv_icon, iv_params);
                addView(tv_text, tv_params);
            }

            public void setChildData(Destinations.DataBean.GoodsBean bean) {
                Glide.with(context).load(bean.getPhoto_url()).asBitmap().dontAnimate().into(iv_icon);
                tv_text.setText(bean.getTitle());
                String type = bean.getType();
                this.setTag(goods_type_resId, type);
                if (DestinationConstent.GOODS_WIKI.equals(type)) {
                    int id = bean.getWiki_destination().getId();
                    this.setTag(id);
                }
            }
        }
    }

    private class DestinationGoodsAdapter extends PagerAdapter {
        private List<View> list;

        public DestinationGoodsAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public DestinationGoodsAdapter() {
            super();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = list.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
