package com.dream.will.hydrogenballoon.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.dream.will.hydrogenballoon.R;

public class MsgActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout msg_zan;
    private LinearLayout msg_shoucang;
    private LinearLayout msg_guanzhu;
    private LinearLayout msg_pl;
    private LinearLayout msg_tongzhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        initView();
        toolbar.setTitle("消息中心");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.md_nav_back);
        toolbar.setBackgroundResource(R.color.colorPrimary);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        msg_zan = (LinearLayout) findViewById(R.id.msg_zan);
        msg_shoucang = (LinearLayout) findViewById(R.id.msg_shoucang);
        msg_guanzhu = (LinearLayout) findViewById(R.id.msg_guanzhu);
        msg_pl = (LinearLayout) findViewById(R.id.msg_pl);
        msg_tongzhi = (LinearLayout) findViewById(R.id.msg_tongzhi);
    }
}
