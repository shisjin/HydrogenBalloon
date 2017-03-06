package com.dream.will.hydrogenballoon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.MyApp;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.adapter.AbsBaseAdapter2;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.NearDestinationRegionListBean;
import com.dream.will.hydrogenballoon.content.Constant;
import com.dream.will.hydrogenballoon.content.IntentConstant;
import com.dream.will.hydrogenballoon.customview.linggan;
import com.dream.will.hydrogenballoon.inter.IGongLv;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegionActivity extends SwipeBackActivity implements AdapterView.OnItemClickListener {

    List<NearDestinationRegionListBean.DataBean> data;
    private Toolbar toolbar;
    private ListView listView;
    private String name;
    private String region;
    private AbsBaseAdapter2<NearDestinationRegionListBean.DataBean> adapter2;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        initBack();
        Intent intent = getIntent();
        name = intent.getStringExtra(Constant.KEY_GONGLV_INTENT_NAME);
        type = intent.getStringExtra(Constant.KEY_GONGLV_INTENT_TYPE);
        initData();
        initViews();
        //判断类型如果是 true  过多附近目的地 ;  否则为推荐
        if (Constant.KEY_TYPE_NEAR_TRUE.equals(type)) {
            linggan linggan = new linggan(this);
            listView.addHeaderView(linggan);
        } else if (Constant.KEY_TYPE_NEAR_FALES.equals(type)) {
            region = intent.getStringExtra(Constant.KEY_GONGLC_INTENT_REGION);
        }
        toolbar.setTitle(name);
        toolbar.setNavigationIcon(R.drawable.md_nav_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        getNetListData();

    }

    private void initBack() {
        //获取  swipeBackLayout
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        //设置 互动的区域
        swipeBackLayout.setEdgeSize(200);
        // 设定滑动关闭的方向  下左右
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    private void getNetListData() {
        //retrofit 联网加载数据
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获取接口实例
        IGongLv iStore = re.create(IGongLv.class);
        Call<NearDestinationRegionListBean> call = null;
        //判断类型如果是 true  过多附近目的地 ;  否则为推荐
        if (Constant.KEY_TYPE_NEAR_TRUE.equals(type)) {
            call = iStore.getNearDestinationMore(MyApp.getInstance().lat, MyApp.getInstance().lng);
        } else if (Constant.KEY_TYPE_NEAR_FALES.equals(type)) {
            call = iStore.getNearDestinationList(region);
        }

        //发请求
        call.enqueue(new Callback<NearDestinationRegionListBean>() {
            @Override
            public void onResponse(Call<NearDestinationRegionListBean> call, Response<NearDestinationRegionListBean> response) {
                NearDestinationRegionListBean body = response.body();
                setData(body.getData());
                Log.i("TAG", "onResponse: -gonglv NearDestinationRegionListBean--------" + response.body().getData().get(0).getName());
            }

            @Override
            public void onFailure(Call<NearDestinationRegionListBean> call, Throwable t) {
                Log.i("TAG", "onFailure: --gonglv NearDestinationRegionListBean-----error--" + call.request());
            }
        });
    }

    private void setData(List<NearDestinationRegionListBean.DataBean> data) {
        this.data.addAll(data);
        listView.setAdapter(adapter2);
    }


    void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listView);
        adapter2 = new AbsBaseAdapter2<NearDestinationRegionListBean.DataBean>(this, data, R.layout.gonglv_detail_list_item) {
            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public void bindData(int position, ViewHolder viewHolder) {

                ImageView city_image = (ImageView) viewHolder.findViewBid(R.id.city_image1);
                TextView city_name = (TextView) viewHolder.findViewBid(R.id.city_name1);
                TextView city_name_en = (TextView) viewHolder.findViewBid(R.id.city_name_en1);
                NearDestinationRegionListBean.DataBean dataBean = data.get(position);
                Glide.with(RegionActivity.this).load(dataBean.getPhoto_url()).into(city_image);
                city_name.setText(dataBean.getName());
                //判断类型如果是 true  过多附近目的地 ;  否则为推荐
                if (Constant.KEY_TYPE_NEAR_TRUE.equals(type)) {
                    city_name_en.setText(dataBean.getVisit_tip());
                } else if (Constant.KEY_TYPE_NEAR_FALES.equals(type)) {
                    city_name_en.setText(dataBean.getName_en());
                }
            }
        };
        listView.setOnItemClickListener(this);
    }

    void initData() {
        data = new ArrayList();
    }

    //设置toolbar 导航栏 logo  侦听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ///////////////////////////////////////////////////////////////////////////
    // 点击事件
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onItemClick(AdapterView<?> paent, View view, int position, long id) {
        //判断类型如果是 true  过多附近目的地 ;  否则为推荐
        if (Constant.KEY_TYPE_NEAR_TRUE.equals(type)) {
            position = position - 1;
        } else if (Constant.KEY_TYPE_NEAR_FALES.equals(type)) {

        }
        Intent intent = new Intent(this, DestinationActivity.class);
        intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,data.get(position).getId());
        startActivity(intent);
        //跳转到新界面
//        Toast.makeText(this, "" + data.get(position).getUserName(), Toast.LENGTH_SHORT).show();
    }
}
