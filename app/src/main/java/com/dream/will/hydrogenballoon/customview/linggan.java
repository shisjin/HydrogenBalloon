package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.ui.NearDestinationLingganActivity;

/**
 * Author：Will on 2016/12/20 17:35
 * Mail：heheheqin.will@gmail.com
 */

public class linggan extends LinearLayout {

    Context context;
    public linggan(Context context) {
        super(context);
        initData();
        this.context = context;
    }

    public linggan(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        inflate(getContext(), R.layout.gonglv_detail_list_item1,this);
        RelativeLayout r = (RelativeLayout) findViewById(R.id.rLayout);
        r.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
//                Toast.makeText(getContext(), "灵感", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, NearDestinationLingganActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
