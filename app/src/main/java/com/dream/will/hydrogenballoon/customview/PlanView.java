package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.Destinations;
import com.dream.will.hydrogenballoon.content.DestinationConstent;
import com.dream.will.hydrogenballoon.inter.OnDestinationClickListener;

import java.util.List;


/**
 * 经典路线 行程
 */

public class PlanView extends HorizontalScrollView implements RadioGroup.OnCheckedChangeListener {
    private Context context;
    private RadioGroup mRG;
    private OnDestinationClickListener listener;
    private ColorStateList colorStateList;

    public PlanView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PlanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        HorizontalScrollView.LayoutParams params = new HorizontalScrollView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRG = new RadioGroup(context);
        mRG.setOrientation(RadioGroup.HORIZONTAL);
        mRG.setGravity(Gravity.CENTER);
        mRG.setPadding(0, 10, 0, 10);
        mRG.setOnCheckedChangeListener(this);
        addView(mRG, params);
        int[] colors = new int[]{Color.WHITE, Color.BLACK};
        int[][] state = new int[2][1];
        state[0][0] = android.R.attr.state_checked;
        state[1][0] = -android.R.attr.state_checked;
        colorStateList = new ColorStateList(state, colors);
    }

    public void setData(@NonNull List<Destinations.DataBean.SectionsBean.ModelsBean> models) {
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(10, 10, 10, 10);
        boolean isFirst = true;
        for (Destinations.DataBean.SectionsBean.ModelsBean bean : models) {
            RadioButton rb = new RadioButton(context);
            rb.setButtonDrawable(null);
            rb.setPadding(20, 10, 20, 10);
            rb.setGravity(Gravity.CENTER);
            rb.setBackgroundResource(R.drawable.destination_plans_rb_selected);
            rb.setTextSize(16);
            rb.setTextColor(colorStateList);
            rb.setTag(bean);
            rb.setText(String.valueOf(bean.getDays_count() + "日行程"));
            mRG.addView(rb, params);
            if (isFirst) {
                isFirst = false;
                rb.setChecked(true);
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.i("google.karlo", "onCheckedChanged: " + checkedId);
        if (null != listener) {
            listener.onClick(DestinationConstent.SECTIONS_PLAM, group.findViewById(checkedId));
        }
    }

    public void setListener(OnDestinationClickListener listener) {
        this.listener = listener;
    }
}
