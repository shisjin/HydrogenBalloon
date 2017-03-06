package com.dream.will.hydrogenballoon.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.fragment.GonglvFragment;
import com.dream.will.hydrogenballoon.fragment.WoDeFragment;
import com.dream.will.hydrogenballoon.fragment.XingChengDanFragment;
import com.dream.will.hydrogenballoon.fragment.YouJiFragment;

public class HomeActivity extends BaseActivity implements View.OnClickListener{
    String[] titles = {"攻略", "游记", "行程单", "我的"};
    private Fragment[] fragments;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
        initView();
    }


    void initData() {
        fragments = new Fragment[titles.length];
        GonglvFragment mGonglvFragment = new GonglvFragment();
        fragments[0] = mGonglvFragment;
        YouJiFragment mYouJiFragment = new YouJiFragment();
        fragments[1] = mYouJiFragment;
        XingChengDanFragment mXingChengDanFragment = new XingChengDanFragment();
        fragments[2] = mXingChengDanFragment;
        WoDeFragment mWoDeFragment = new WoDeFragment();
        fragments[3] = mWoDeFragment;

    }
    private void initView() {
        TextView search = (TextView) findViewById(R.id.search_text);
        search.setOnClickListener(this);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        //2 设置适配器
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            // 4. 设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        //3 联动
        tabLayout.setupWithViewPager(viewpager);
    }


    /** 按两次返回键退出
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次返回", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_text:{
                //跳转搜索页面
//                startActivity(new Intent(HomeActivity.this,ShowPicActivity.class));

            }break;
        }
    }
}
