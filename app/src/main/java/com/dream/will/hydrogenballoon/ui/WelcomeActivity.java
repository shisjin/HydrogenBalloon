package com.dream.will.hydrogenballoon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.utils.SharedUtil;

/**
 */
public class WelcomeActivity extends AppCompatActivity {


    private ImageView iv_welcome;
   // private Boolean isplay = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        init();
    }

    private void init() {
        /**
         * 获取共享参数
         */
        //isplay = SharedUtil.isFirstRun(this);

        if (SharedUtil.isFirstRun(this)) {
            SharedUtil.saveFirstRun(this);
            iv_welcome.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, PlayVideoActivity.class));
                    overridePendingTransition(R.anim.anim_activity_in_right,R.anim.anim_activity_out_left);
                    finish();
                }
            }, 1000);
        } else {
            iv_welcome.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                    overridePendingTransition(R.anim.anim_activity_in_right,R.anim.anim_activity_out_left);
                    finish();
                }
            }, 1000);
        }
    }

    private void initView() {
        iv_welcome = (ImageView) findViewById(R.id.iv_welcome);
    }
}
