package com.dream.will.hydrogenballoon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.GonglvListBean;
import com.dream.will.hydrogenballoon.content.Constant;
import com.dream.will.hydrogenballoon.content.IntentConstant;
import com.dream.will.hydrogenballoon.ui.DestinationActivity;
import com.dream.will.hydrogenballoon.ui.RegionActivity;

import java.util.List;


public class GonglueRecyclAdapter extends RecyclerView.Adapter {

    public static final int HEAD_ONE = 234;
    public static final int HEAD_TWO = 345;
    public static final int HEAD_THREE = 456;
    private Context context;
    private List<GonglvListBean.DataBean> data;
    private LayoutInflater inflater;
    private View mHeaderViewOne = null;
    private View mHeaderViewTwo = null;
    private View mHeaderViewThree = null;

    public GonglueRecyclAdapter(Context context, List<GonglvListBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public View getHeaderView() {
        return mHeaderViewOne;
    }

    public void setHeaderViewOne(View headerView) {
        mHeaderViewOne = headerView;
        notifyItemInserted(0);
    }
    public void setHeaderViewTwo(View headerView) {
        mHeaderViewTwo = headerView;
        notifyItemInserted(1);
    }
    public void setHeaderViewThree(View headerView) {
        mHeaderViewThree = headerView;
        notifyItemInserted(2);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderViewOne != null && position == 0) {
            return HEAD_ONE;
        }
        if (mHeaderViewTwo != null && position == 1) {
            return HEAD_TWO;
        }
        if (mHeaderViewThree != null && position == 2) {
            return HEAD_THREE;
        }
        //获取真正的 position位置
        position =  mHeaderViewOne == null ? position : position - 3;
        String region = data.get(position).getRegion();
        int layout = 0;
        switch (region) {
            case "hk": { //港澳台
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 1: {  //港澳台
                view = inflater.inflate(R.layout.gonglv_list_item2, parent, false);
                viewHolder = new ViewHolder1(view);
            }
            break;
            case 0: {
                view = inflater.inflate(R.layout.gonglv_list_item1, parent, false);
                viewHolder = new ViewHolder2(view);
            }
            break;
            case HEAD_ONE: {
                return new ViewHolderHeadOne(mHeaderViewOne);
            }
            case HEAD_TWO: {
                return new ViewHolderHeadTwo(mHeaderViewTwo);
            }
            case HEAD_THREE: {
                return new ViewHolderHeadThree(mHeaderViewThree);
            }
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int realPosition = getRealPosition(holder);
        switch (getItemViewType(position)) {
            case 1: {
                //港澳台单排
                setDataLayoutOne(holder, realPosition);
            }
            break;

            case 0: {
                //其他双排
                setDataLayoutTwo(holder, realPosition);
            }
            break;

            case HEAD_ONE: {
                ViewHolderHeadOne viewHolderH = (ViewHolderHeadOne) holder;
            }
            break;
            case HEAD_TWO: {
                ViewHolderHeadTwo viewHolderH = (ViewHolderHeadTwo) holder;
            }
            break;
            case HEAD_THREE: {
                ViewHolderHeadThree viewHolderH = (ViewHolderHeadThree) holder;
            }
            break;
        }
    }


    private void setDataLayoutTwo(RecyclerView.ViewHolder holder, int position) {
        final GonglvListBean.DataBean dataBean = data.get(position);
        ViewHolder2 holderTwo = (ViewHolder2) holder;
        final List<GonglvListBean.DataBean.DestinationsBean> destinations = dataBean.getDestinations();
        holderTwo.city_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,destinations.get(0).getId());
                context.startActivity(intent);
//                Toast.makeText(context, "" + destinations.get(0).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        holderTwo.city_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,destinations.get(1).getId());
                context.startActivity(intent);
//                Toast.makeText(context, "" + destinations.get(1).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        holderTwo.city_image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,destinations.get(2).getId());
                context.startActivity(intent);
//                Toast.makeText(context, "" + destinations.get(2).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        holderTwo.hot_destination.setText(dataBean.getName());
        GonglvListBean.DataBean.DestinationsBean destinationsBean = destinations.get(0);
        holderTwo.city_name1.setText(destinationsBean.getName());
        holderTwo.city_name_en1.setText(destinationsBean.getName_en());
        Glide.with(context).load(destinationsBean.getPhoto_url()).asBitmap().dontAnimate().into(holderTwo.city_image1);
        GonglvListBean.DataBean.DestinationsBean destinationsBean1 = destinations.get(1);
        holderTwo.city_name2.setText(destinationsBean1.getName());
        holderTwo.city_name_en2.setText(destinationsBean1.getName_en());
        Glide.with(context).load(destinationsBean1.getPhoto_url()).asBitmap().dontAnimate().into(holderTwo.city_image2);
        GonglvListBean.DataBean.DestinationsBean destinationsBean2 = destinations.get(2);
        holderTwo.city_name3.setText(destinationsBean2.getName());
        holderTwo.city_name_en3.setText(destinationsBean2.getName_en());
        Glide.with(context).load(destinationsBean2.getPhoto_url()).asBitmap().dontAnimate().into(holderTwo.city_image3);

        holderTwo.more.setText(dataBean.getButton_text());
        holderTwo.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转界面
                Intent intent = new Intent(context, RegionActivity.class);
                intent.putExtra(Constant.KEY_GONGLV_INTENT_NAME, dataBean.getName());
                intent.putExtra(Constant.KEY_GONGLC_INTENT_REGION, dataBean.getRegion());
                intent.putExtra(Constant.KEY_GONGLV_INTENT_TYPE, Constant.KEY_TYPE_NEAR_FALES);
                context.startActivity(intent);
//                    Toast.makeText(context, ""+dataBean.getRegion(), Toast.LENGTH_SHORT).show();
            }
        });

        holderTwo.city_image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,destinations.get(3).getId());
                context.startActivity(intent);
//                Toast.makeText(context, "" + destinations.get(3).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        holderTwo.city_image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,destinations.get(4).getId());
                context.startActivity(intent);
//                Toast.makeText(context, "" + destinations.get(4).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        holderTwo.city_image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,destinations.get(5).getId());
                context.startActivity(intent);
//                Toast.makeText(context, "" + destinations.get(5).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        GonglvListBean.DataBean.DestinationsBean destinationsBean3 = destinations.get(3);
        holderTwo.city_name4.setText(destinationsBean3.getName());
        holderTwo.city_name_en4.setText(destinationsBean3.getName_en());
        Glide.with(context).load(destinationsBean3.getPhoto_url()).asBitmap().dontAnimate().into(holderTwo.city_image4);
        GonglvListBean.DataBean.DestinationsBean destinationsBean4 = destinations.get(4);
        holderTwo.city_name5.setText(destinationsBean4.getName());
        holderTwo.city_name_en5.setText(destinationsBean4.getName_en());
        Glide.with(context).load(destinationsBean4.getPhoto_url()).asBitmap().dontAnimate().into(holderTwo.city_image5);
        GonglvListBean.DataBean.DestinationsBean destinationsBean5 = destinations.get(5);
        holderTwo.city_name6.setText(destinationsBean5.getName());
        holderTwo.city_name_en6.setText(destinationsBean5.getName_en());
        Glide.with(context).load(destinationsBean5.getPhoto_url()).asBitmap().dontAnimate().into(holderTwo.city_image6);

    }

    //港澳太 单排
    private void setDataLayoutOne(RecyclerView.ViewHolder holder, int position) {
        ViewHolder1 holderOne = (ViewHolder1) holder;
        final GonglvListBean.DataBean dataBean = data.get(position);
        final List<GonglvListBean.DataBean.DestinationsBean> destinations = dataBean.getDestinations();
        holderOne.city_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,destinations.get(0).getId());
                context.startActivity(intent);
//                Toast.makeText(context, "" + destinations.get(0).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        holderOne.city_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,destinations.get(1).getId());
                context.startActivity(intent);
//                Toast.makeText(context, "" + destinations.get(1).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        holderOne.city_image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY,destinations.get(2).getId());
                context.startActivity(intent);
//                Toast.makeText(context, "" + destinations.get(2).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
        holderOne.hot_destination.setText(dataBean.getName());
        GonglvListBean.DataBean.DestinationsBean destinationsBean = destinations.get(0);
        holderOne.city_name1.setText(destinationsBean.getName());
        holderOne.city_name_en1.setText(destinationsBean.getName_en());
        Glide.with(context).load(destinationsBean.getPhoto_url()).asBitmap().dontAnimate().into(holderOne.city_image1);
        GonglvListBean.DataBean.DestinationsBean destinationsBean1 = destinations.get(1);
        holderOne.city_name2.setText(destinationsBean1.getName());
        holderOne.city_name_en2.setText(destinationsBean1.getName_en());
        Glide.with(context).load(destinationsBean1.getPhoto_url()).asBitmap().dontAnimate().into(holderOne.city_image2);
        GonglvListBean.DataBean.DestinationsBean destinationsBean2 = destinations.get(2);
        holderOne.city_name3.setText(destinationsBean2.getName());
        holderOne.city_name_en3.setText(destinationsBean2.getName_en());
        Glide.with(context).load(destinationsBean2.getPhoto_url()).asBitmap().dontAnimate().into(holderOne.city_image3);

        //港澳台没有跳转监听事件
        holderOne.more1.setVisibility(View.GONE);
    }


    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderViewOne == null ? position : position - 3;
    }

    @Override
    public int getItemCount() {
        return mHeaderViewOne == null ? data.size() : data.size() + 3;
    }

    //单排港澳台
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView hot_destination;
        public TextView city_name1;
        public TextView city_name2;
        public TextView city_name3;
        public TextView city_name_en1;
        public TextView city_name_en2;
        public TextView city_name_en3;
        public ImageView city_image1;
        public ImageView city_image2;
        public ImageView city_image3;
        public TextView more1;

        public ViewHolder1(View itemView) {
            super(itemView);
            hot_destination = (TextView) itemView.findViewById(R.id.hot_destination);
            city_name1 = (TextView) itemView.findViewById(R.id.city_name1);
            city_name2 = (TextView) itemView.findViewById(R.id.city_name2);
            city_name3 = (TextView) itemView.findViewById(R.id.city_name3);
            city_name_en1 = (TextView) itemView.findViewById(R.id.city_name_en1);
            city_name_en2 = (TextView) itemView.findViewById(R.id.city_name_en2);
            city_name_en3 = (TextView) itemView.findViewById(R.id.city_name_en3);
            city_image1 = (ImageView) itemView.findViewById(R.id.city_image1);
            city_image2 = (ImageView) itemView.findViewById(R.id.city_image2);
            city_image3 = (ImageView) itemView.findViewById(R.id.city_image3);
            more1 = (TextView) itemView.findViewById(R.id.more1);
        }
    }

    //双排其他
    class ViewHolder2 extends RecyclerView.ViewHolder {

        private final TextView more;
        public TextView hot_destination;
        public TextView city_name1;
        public TextView city_name2;
        public TextView city_name3;
        public TextView city_name_en1;
        public TextView city_name_en2;
        public TextView city_name_en3;
        public ImageView city_image1;
        public ImageView city_image2;
        public ImageView city_image3;
        public TextView more1;
        public TextView city_name4;
        public TextView city_name5;
        public TextView city_name6;
        public TextView city_name_en4;
        public TextView city_name_en5;
        public TextView city_name_en6;
        public ImageView city_image4;
        public ImageView city_image5;
        public ImageView city_image6;

        public ViewHolder2(View itemView) {
            super(itemView);
            hot_destination = (TextView) itemView.findViewById(R.id.hot_destination);
            city_name1 = (TextView) itemView.findViewById(R.id.city_name1);
            city_name2 = (TextView) itemView.findViewById(R.id.city_name2);
            city_name3 = (TextView) itemView.findViewById(R.id.city_name3);
            city_name_en1 = (TextView) itemView.findViewById(R.id.city_name_en1);
            city_name_en2 = (TextView) itemView.findViewById(R.id.city_name_en2);
            city_name_en3 = (TextView) itemView.findViewById(R.id.city_name_en3);
            city_image1 = (ImageView) itemView.findViewById(R.id.city_image1);
            city_image2 = (ImageView) itemView.findViewById(R.id.city_image2);
            city_image3 = (ImageView) itemView.findViewById(R.id.city_image3);
            city_name4 = (TextView) itemView.findViewById(R.id.city_name4);
            city_name5 = (TextView) itemView.findViewById(R.id.city_name5);
            city_name6 = (TextView) itemView.findViewById(R.id.city_name6);
            city_name_en4 = (TextView) itemView.findViewById(R.id.city_name_en4);
            city_name_en5 = (TextView) itemView.findViewById(R.id.city_name_en5);
            city_name_en6 = (TextView) itemView.findViewById(R.id.city_name_en6);
            city_image4 = (ImageView) itemView.findViewById(R.id.city_image4);
            city_image5 = (ImageView) itemView.findViewById(R.id.city_image5);
            city_image6 = (ImageView) itemView.findViewById(R.id.city_image6);
            more = (TextView) itemView.findViewById(R.id.more);
        }
    }

    //三种头布局 viewholder
    class ViewHolderHeadOne extends RecyclerView.ViewHolder {

        public ViewHolderHeadOne(View itemView) {
            super(itemView);
        }
    }
    class ViewHolderHeadTwo extends RecyclerView.ViewHolder {

        public ViewHolderHeadTwo(View itemView) {
            super(itemView);
        }
    }
    class ViewHolderHeadThree extends RecyclerView.ViewHolder {

        public ViewHolderHeadThree(View itemView) {
            super(itemView);
        }
    }

}
