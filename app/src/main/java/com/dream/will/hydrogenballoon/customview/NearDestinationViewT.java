package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.NearDestinationViewBean;
import com.dream.will.hydrogenballoon.content.Constant;
import com.dream.will.hydrogenballoon.content.IntentConstant;
import com.dream.will.hydrogenballoon.inter.IGongLv;
import com.dream.will.hydrogenballoon.ui.DestinationActivity;
import com.dream.will.hydrogenballoon.ui.RegionActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 */

public class NearDestinationViewT {

    private TextView hot_destination;
    private ImageView city_image1;
    private TextView city_name1;
    private TextView city_name_en1;
    private ImageView city_image2;
    private TextView city_name2;
    private TextView city_name_en2;
    private ImageView city_image3;
    private TextView city_name3;
    private TextView city_name_en3;
    private TextView more1;

    Context context;

    public NearDestinationViewT(Context context) {
        this.context = context;
    }

    public  View initData( ) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.gonglv_list_item2, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        inflate.setLayoutParams(layoutParams);
        inflate.requestLayout();
//        inflate(getContext(),R.layout.gonglv_list_item2,this);
//        inflate(context, R.layout.gonglv_list_item2, this);
        hot_destination = (TextView) inflate.findViewById(R.id.hot_destination);
        city_image1 = (ImageView) inflate.findViewById(R.id.city_image1);
        city_name1 = (TextView) inflate.findViewById(R.id.city_name1);
        city_name_en1 = (TextView) inflate.findViewById(R.id.city_name_en1);
        city_image2 = (ImageView) inflate.findViewById(R.id.city_image2);
        city_name2 = (TextView) inflate.findViewById(R.id.city_name2);
        city_name_en2 = (TextView) inflate.findViewById(R.id.city_name_en2);
        city_image3 = (ImageView) inflate.findViewById(R.id.city_image3);
        city_name3 = (TextView) inflate.findViewById(R.id.city_name3);
        city_name_en3 = (TextView) inflate.findViewById(R.id.city_name_en3);
        more1 = (TextView) inflate.findViewById(R.id.more1);
        return inflate ;
    }


    /**
     * 联网获取 listview  Hot数据  更新斯佩卿
     * "lat": 25.0454006195,
     "  lng": 102.7099990845,
     */
    public  void  getHostListData(double lat,double lng){

        //retrofit 联网加载数据
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获取接口实例
        IGongLv iStore = re.create(IGongLv.class);
        Call<NearDestinationViewBean> call = iStore.getNearDestination(lat,lng,"");
        //发请求
        call.enqueue(new Callback<NearDestinationViewBean>() {
            @Override
            public void onResponse(Call<NearDestinationViewBean> call, Response<NearDestinationViewBean> response) {
                NearDestinationViewBean body = response.body();
                setData(body.getData());
//                Log.i("TAG", "onResponse: -gonglv NearDestinationView--------" + response.body());
            }

            @Override
            public void onFailure(Call<NearDestinationViewBean> call, Throwable t) {
                Log.i("TAG", "onFailure: --gonglv NearDestinationView-----error--"+call.request());
            }
        });

    }

    private void setData(List<NearDestinationViewBean.DataBean> data) {
//        int widthPixels =  (getContext().getResources().getDisplayMetrics().widthPixels)/3;
        hot_destination.setText("附近目的地");
        final NearDestinationViewBean.DataBean dataBean1 = data.get(0);
        city_name1.setText(dataBean1.getName());
        city_name_en1.setText(dataBean1.getName_en());
        Glide.with(context).load(dataBean1.getPhoto_url()).asBitmap().dontAnimate().into(city_image1);
        final NearDestinationViewBean.DataBean dataBean2 = data.get(1);
        city_name2.setText(dataBean2.getName());
        city_name_en2.setText(dataBean2.getName_en());
        Glide.with(context).load(dataBean2.getPhoto_url()).asBitmap().dontAnimate().into(city_image2);
        final NearDestinationViewBean.DataBean dataBean3 = data.get(2);
        city_name3.setText(dataBean3.getName());
        city_name_en3.setText(dataBean3.getName_en());
        Glide.with(context).load(dataBean3.getPhoto_url()).asBitmap().dontAnimate().into(city_image3);

        more1.setText("更多附近目的地");
        more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转界面
                //跳转界面
                Intent intent = new Intent(context, RegionActivity.class);
                intent.putExtra(Constant.KEY_GONGLV_INTENT_NAME,"附近目的地");
                intent.putExtra(Constant.KEY_GONGLV_INTENT_TYPE, Constant.KEY_TYPE_NEAR_TRUE);
                context.startActivity(intent);

                Toast.makeText(context, ""+more1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        city_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转界面
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,dataBean1.getId());
                context.startActivity(intent);
                //显示在 最近想去 并且保存到数据库
//                Toast.makeText(context, dataBean1.getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        city_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,dataBean2.getId());
                context.startActivity(intent);
                //显示在 最近想去 并且保存到数据库
//                Toast.makeText(context, dataBean2.getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        city_image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,dataBean3.getId());
                context.startActivity(intent);
                //显示在 最近想去 并且保存到数据库
//                Toast.makeText(context, dataBean3.getUserName(), Toast.LENGTH_SHORT).show();
            }
        });

    }




}
