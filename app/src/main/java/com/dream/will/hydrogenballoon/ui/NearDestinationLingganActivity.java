package com.dream.will.hydrogenballoon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.MyApp;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.adapter.AbsBaseAdapter2;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.NearDestinationLinggan;
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

public class NearDestinationLingganActivity extends SwipeBackActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private ListView listView_linggan;
    List<NearDestinationLinggan.DataBean> data;
    private AbsBaseAdapter2<NearDestinationLinggan.DataBean> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_destination_linggan);
        Intent intent = getIntent();
        initBack();
        initData();
        initView();
        toolbar.setTitle("附近旅行灵感");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.md_nav_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        getNetListData();//联网获取数据
    }

    private void initBack() {
        //获取  swipeBackLayout
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        //设置 互动的区域
        swipeBackLayout.setEdgeSize(200);
        // 设定滑动关闭的方向  下左右
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    private void initData() {
        data = new ArrayList<>();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView_linggan = (ListView) findViewById(R.id.listView_linggan);
        listView_linggan.setOnItemClickListener(this);
        adapter2 = new AbsBaseAdapter2<NearDestinationLinggan.DataBean>(this, data, R.layout.activity_near_destination_linggan_list_item) {
            private ImageView image;
            private ImageView big_image;
            private TextView topic;
            private TextView visit_tip;
            private TextView introduce;
            private TextView show_gonglue;
            private LinearLayout linearlayout;

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public void bindData(int position, ViewHolder viewHolder) {
                NearDestinationLinggan.DataBean dataBean = data.get(position);
                image = (ImageView) viewHolder.findViewBid(R.id.image);
                topic = (TextView) viewHolder.findViewBid(R.id.topic);
                visit_tip = (TextView) viewHolder.findViewBid(R.id.visit_tip);
                introduce = (TextView) viewHolder.findViewBid(R.id.introduce);
                linearlayout = (LinearLayout) viewHolder.findViewBid(R.id.linearlayout);
                big_image = (ImageView) viewHolder.findViewBid(R.id.big_image);
                show_gonglue = (TextView) viewHolder.findViewBid(R.id.show_gonglue);
                topic.setText(dataBean.getTopic());
                visit_tip.setText(dataBean.getVisit_tip());
                introduce.setText(dataBean.getIntroduce());
                switch (dataBean.getIcon_type()) {
                    case 0:
                        Glide.with(NearDestinationLingganActivity.this).load(R.drawable.icon_plan_food).asBitmap().dontAnimate().into(image);
                        break;
                    case 1:
                        Glide.with(NearDestinationLingganActivity.this).load(R.drawable.icon_plan_hotel).asBitmap().dontAnimate().into(image);
                        break;
                    case 2:
                        Glide.with(NearDestinationLingganActivity.this).load(R.drawable.icon_plan_scenery).asBitmap().dontAnimate().into(image);
                        break;
                }
                big_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //
                        Log.i("TAG", "onClick: -----tiaozhuan----" );
                    }
                });
                show_gonglue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("TAG", "onClick: -----tiaozhuan----" );
                    }
                });
            }
        };
        listView_linggan.setAdapter(adapter2);
    }

    //联网获取数据
    private void getNetListData() {
        //retrofit 联网加载数据
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获取接口实例
        IGongLv iStore = re.create(IGongLv.class);
        Call<NearDestinationLinggan> call = iStore.getNearDestinationMoreLinggan(MyApp.getInstance().lat, MyApp.getInstance().lng);

        //发请求
        call.enqueue(new Callback<NearDestinationLinggan>() {
            @Override
            public void onResponse(Call<NearDestinationLinggan> call, Response<NearDestinationLinggan> response) {
                NearDestinationLinggan body = response.body();
                data.addAll(body.getData());
                adapter2.notifyDataSetChanged();
//                setData(body.getData());
                Log.i("TAG", "onResponse: -gonglv NearDestinationLinggan--------" + response.body().getData().get(0).getTopic());
            }

            @Override
            public void onFailure(Call<NearDestinationLinggan> call, Throwable t) {
                Log.i("TAG", "onFailure: --gonglv NearDestinationLinggan-----error--" + call.request());
            }
        });
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

    Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int position = msg.arg1;
                listView_linggan.smoothScrollToPositionFromTop(position,0);
            }
        };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.linearlayout);
        if (ll.getVisibility() == View.VISIBLE) {  //如果是显示
            ll.setVisibility(View.GONE);
        }else {
            ll.setVisibility(View.VISIBLE);
            ImageView big_image = (ImageView) view.findViewById(R.id.big_image);
            Glide.with(this).load(data.get(position).getPhoto().getPhoto_url())
                    .asBitmap()
                    .dontAnimate()
                    .into(big_image);
        }
        //给一点时间给cpu处理
        handler.removeMessages(1);
        Message message = Message.obtain();
        message.what = 1;
        message.arg1 = position;
        handler.sendMessageDelayed(message,10);

    }
}
