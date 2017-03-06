package com.dream.will.hydrogenballoon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.UrlString;
import com.dream.will.hydrogenballoon.content.IntentConstant;
import com.dream.will.hydrogenballoon.fragment.ShowPicFragment;

import java.util.ArrayList;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ShowPicActivity extends SwipeBackActivity {

    Fragment[] fragments;
//    String[] url = {
//            "http://p.chanyouji.cn/1466864494/6cvdnzxkuzy3el2egv60yor30.jpg",
//            "http://p.chanyouji.cn/1469091857/3nbzv2ys69nu558doyhe3qlwq.jpg",
//            "http://p.chanyouji.cn/1469091093/5q00s09h823psbr8jgwowgllm.jpg",
//            "http://p.chanyouji.cn/1466864489/6i57b9fqx7dkowre2174bl92.jpg",
//            "http://p.chanyouji.cn/1469599732/31r1bz6kqplb78jots6b3qa73.jpg",
//            "http://p.chanyouji.cn/1469591136/5nnvub4iafohr7hrjdpcmhlou.jpg",
//            "http://p.chanyouji.cn/1469705781/64aducqvethhl24q69csgvdv6.jpg",
//            "http://p.chanyouji.cn/1469635156/6kqlhh35nh45ee56v4au4x9mt.jpg"};
//
//    String[] title = {
//            "没事很好", "没事很好", "没事很好", "http://p.chanyouji.cn/1469635156/6kqlhh35nh45ee56v4au4x9mt.jpg", "没事很好", "没事很好", "没事很好", ""
//    };
    private TextView text_pager;
    private ViewPager show_viewpager;
    private int current = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pic);
        initBack();
        iniData();
        initView();
    }

    private void initView() {
        text_pager = (TextView) findViewById(R.id.text_pager);
        show_viewpager = (ViewPager) findViewById(R.id.show_viewpager);
        show_viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }
            @Override
            public int getCount() {
                return fragments.length;
            }
        });
        show_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                text_pager.setText((position+1) +"/"+fragments.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        text_pager.setText(current+"/"+fragments.length);
        show_viewpager.setCurrentItem(current-1);
    }

    private void iniData() {

        Intent intent = getIntent();
        ArrayList<UrlString> beab = intent.getParcelableArrayListExtra(IntentConstant.KEY_SHOW_PIC_URL);
        current = intent.getIntExtra(IntentConstant.KEY_SHOW_PIC_CURRENT,3);
        Log.i("TAG", "iniData: ---------" + beab.toString());
        Log.i("TAG", "iniData: ---------" + current);

        fragments = new Fragment[beab.size()];
//        fragments = new Fragment[list_url.size()];
        for (int i = 0; i < fragments.length; i++) {
            ShowPicFragment showPicFragment = new ShowPicFragment();
            Bundle bundle = new Bundle();
            bundle.putString(IntentConstant.KEY_SHOW_PIC_URL, beab.get(i).getUrl());
            bundle.putString(IntentConstant.KEY_SHOW_PIC_TITLE, beab.get(i).getTitle());
            showPicFragment.setArguments(bundle);
            fragments[i] = showPicFragment;
        }
    }

    private void initBack() {
        //获取  swipeBackLayout
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        //设置 互动的区域
        swipeBackLayout.setEdgeSize(200);
        // 设定滑动关闭的方向  下左右
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
}
