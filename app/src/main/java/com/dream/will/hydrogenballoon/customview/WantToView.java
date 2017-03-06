package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.will.hydrogenballoon.MyApp;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.DbUserBean;
import com.dream.will.hydrogenballoon.content.IntentConstant;
import com.dream.will.hydrogenballoon.db.DbDao;
import com.dream.will.hydrogenballoon.ui.DestinationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class WantToView extends LinearLayout {

    List<DbUserBean> strings;
    private HorizontalScrollView hsv;
    private TextView wantto;
    private LinearLayout ll;

    public WantToView(Context context) {
        super(context);
        initData();
    }

    public WantToView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        strings = new ArrayList<>();
        inflate(getContext(), R.layout.gonglv_list_head_wantto_view, this);
        hsv = (HorizontalScrollView) findViewById(R.id.hsv);
        ll = (LinearLayout) findViewById(R.id.linearlayout);
        wantto = (TextView) findViewById(R.id.wantto);
//        wantto.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setText(new DbUserBean("heh",1+""));
//            }
//        });
    }

    public void setText(DbUserBean dbUserBean) {
        Log.i("TAG", "setText: -------判断--" + dbUserBean.getCityName() + "::" + dbUserBean.getCityid());
        //查询数据库  如果有相应城市 存储点击次数，并且把位置移动到最前面。 没有存储并显示城市
        if (DbDao.find(MyApp.getInstance().dBhelper,dbUserBean)){
            Log.i("TAG", "setText: -------数据库中有--" );
            String cityName = dbUserBean.getCityName();
            // 移动dbUserBean 到第一条 显示
            for (int i = 0; i < strings.size(); i++) {
                if (strings.get(i).getCityName().equals(cityName)) {
                    strings.remove(i);
                    strings.add(0,dbUserBean);
                    break;
                }
            }
        }else {
            Log.i("TAG", "setText: ------数据库中没有---" );
            strings.add(0,dbUserBean);
        }
        //添加功能两个：  一个是存储初始城市 一个存储点击次数
        DbDao.addLocation(MyApp.getInstance().dBhelper,dbUserBean);
        showText();
    }

    public void setText(@NonNull List<DbUserBean> str) {
        if (str.size()==0) {
            return;
        }
        for (DbUserBean dbUserBean : str) {
            strings.add(dbUserBean);
        }
        showText();
    }

    private void showText() {
        int size = strings.size();
        if (size != 0) {
            ll.removeAllViews();
            for (int i = 0; i < size; i++) {
                LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 8, 8, 8);
                TextView textView = new TextView(getContext());
                textView.setBackgroundResource(R.drawable.wanto_text_shape);
                textView.setTextSize(13);
                textView.setPadding(15, 4, 15, 4);
                textView.setTextColor(getResources().getColor(R.color.wanto_text_gray));
                textView.setText(strings.get(i).getCityName());
                final int finalI = i;
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), DestinationActivity.class);
                        intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,Integer.parseInt(strings.get(finalI).getCityid()));
                        getContext().startActivity(intent);
                    }
                });
                ll.addView(textView, params);
//                Log.i("TAG", "setText: ---------" + i);
            }
            requestLayout();
        }
    }


}
