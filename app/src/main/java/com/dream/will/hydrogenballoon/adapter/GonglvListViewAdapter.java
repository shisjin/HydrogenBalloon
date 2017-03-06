package com.dream.will.hydrogenballoon.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.GonglvListBean;
import com.dream.will.hydrogenballoon.content.Constant;
import com.dream.will.hydrogenballoon.ui.RegionActivity;

import java.util.List;



public class GonglvListViewAdapter extends AbsBaseAdapter2<GonglvListBean.DataBean> {
    private Context context;
    private List<GonglvListBean.DataBean> data;
    private int position;

    public GonglvListViewAdapter(Context context, List<GonglvListBean.DataBean> data, int... layoutId) {
        super(context, data, layoutId);
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        this.position = position;
        String region = data.get(position).getRegion();
        int layout = 0;
        switch (region) {
            case "hk": {
                layout = 1;
            }
            break;
            default: {
                layout = 0;
            }
            break;
        }

        return layout;
    }

    @Override
    public void bindData(int position, ViewHolder viewHolder) {
        final GonglvListBean.DataBean dataBean = data.get(position);
        TextView hot_destination = (TextView) viewHolder.findViewBid(R.id.hot_destination);

        TextView city_name1 = (TextView) viewHolder.findViewBid(R.id.city_name1);
        TextView city_name2 = (TextView) viewHolder.findViewBid(R.id.city_name2);
        TextView city_name3 = (TextView) viewHolder.findViewBid(R.id.city_name3);
        TextView city_name_en1 = (TextView) viewHolder.findViewBid(R.id.city_name_en1);
        TextView city_name_en2 = (TextView) viewHolder.findViewBid(R.id.city_name_en2);
        TextView city_name_en3 = (TextView) viewHolder.findViewBid(R.id.city_name_en3);
        ImageView city_image1 = (ImageView) viewHolder.findViewBid(R.id.city_image1);
        ImageView city_image2 = (ImageView) viewHolder.findViewBid(R.id.city_image2);
        ImageView city_image3 = (ImageView) viewHolder.findViewBid(R.id.city_image3);
        LinearLayout position1 = (LinearLayout) viewHolder.findViewBid(R.id.position1);
        LinearLayout position2 = (LinearLayout) viewHolder.findViewBid(R.id.position2);
        LinearLayout position3 = (LinearLayout) viewHolder.findViewBid(R.id.position3 );
        final List<GonglvListBean.DataBean.DestinationsBean> destinations = dataBean.getDestinations();
        city_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+ destinations.get(0).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        city_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+ destinations.get(1).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        city_image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+ destinations.get(2).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        hot_destination.setText(dataBean.getName());

        GonglvListBean.DataBean.DestinationsBean destinationsBean = destinations.get(0);
        city_name1.setText(destinationsBean.getName());
        city_name_en1.setText(destinationsBean.getName_en());
        Glide.with(context).load(destinationsBean.getPhoto_url()).into(city_image1);
        GonglvListBean.DataBean.DestinationsBean destinationsBean1 = destinations.get(1);
        city_name2.setText(destinationsBean1.getName());
        city_name_en2.setText(destinationsBean1.getName_en());
        Glide.with(context).load(destinationsBean1.getPhoto_url()).into(city_image2);
        GonglvListBean.DataBean.DestinationsBean destinationsBean2 = destinations.get(2);
        city_name3.setText(destinationsBean2.getName());
        city_name_en3.setText(destinationsBean2.getName_en());
        Glide.with(context).load(destinationsBean2.getPhoto_url()).into(city_image3);

        if (dataBean.getRegion().equals("hk")){
            //港澳台没有跳转监听事件
            TextView more1 = (TextView) viewHolder.findViewBid(R.id.more1);
            more1.setVisibility(View.GONE);
        }else {
            TextView more = (TextView) viewHolder.findViewBid(R.id.more);
            more.setText(dataBean.getButton_text());
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转界面
                    Intent intent = new Intent(context, RegionActivity.class);
                    intent.putExtra(Constant.KEY_GONGLV_INTENT_NAME,dataBean.getName());
                    intent.putExtra(Constant.KEY_GONGLC_INTENT_REGION,dataBean.getRegion());
                    intent.putExtra(Constant.KEY_GONGLV_INTENT_TYPE,Constant.KEY_TYPE_NEAR_FALES);
                    context.startActivity(intent);
//                    Toast.makeText(context, ""+dataBean.getRegion(), Toast.LENGTH_SHORT).show();
                }
            });
            TextView city_name4 = (TextView) viewHolder.findViewBid(R.id.city_name4);
            TextView city_name5 = (TextView) viewHolder.findViewBid(R.id.city_name5);
            TextView city_name6 = (TextView) viewHolder.findViewBid(R.id.city_name6);
            TextView city_name_en4 = (TextView) viewHolder.findViewBid(R.id.city_name_en4);
            TextView city_name_en5 = (TextView) viewHolder.findViewBid(R.id.city_name_en5);
            TextView city_name_en6 = (TextView) viewHolder.findViewBid(R.id.city_name_en6);
            ImageView city_image4 = (ImageView) viewHolder.findViewBid(R.id.city_image4);
            ImageView city_image5 = (ImageView) viewHolder.findViewBid(R.id.city_image5);
            ImageView city_image6 = (ImageView) viewHolder.findViewBid(R.id.city_image6);
            LinearLayout position4 = (LinearLayout) viewHolder.findViewBid(R.id.position4);
            LinearLayout position5 = (LinearLayout) viewHolder.findViewBid(R.id.position5);
            LinearLayout position6 = (LinearLayout) viewHolder.findViewBid(R.id.position6 );
            city_image4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+ destinations.get(3).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            city_image5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+ destinations.get(4).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            city_image6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+ destinations.get(5).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            GonglvListBean.DataBean.DestinationsBean destinationsBean3 = destinations.get(3);
            city_name4.setText(destinationsBean3.getName());
            city_name_en4.setText(destinationsBean3.getName_en());
            Glide.with(context).load(destinationsBean3.getPhoto_url()).into(city_image4);
            GonglvListBean.DataBean.DestinationsBean destinationsBean4 = destinations.get(4);
            city_name5.setText(destinationsBean4.getName());
            city_name_en5.setText(destinationsBean4.getName_en());
            Glide.with(context).load(destinationsBean4.getPhoto_url()).into(city_image5);
            GonglvListBean.DataBean.DestinationsBean destinationsBean5 = destinations.get(5);
            city_name6.setText(destinationsBean5.getName());
            city_name_en6.setText(destinationsBean5.getName_en());
            Glide.with(context).load(destinationsBean5.getPhoto_url()).into(city_image6);
        }

    }

}
