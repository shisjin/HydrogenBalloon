package com.dream.will.hydrogenballoon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.PersonalHomePageDeatilBean;

import java.util.List;


public class PersonalHomepage_PhotoAdapter extends RecyclerView.Adapter<PersonalHomepage_PhotoAdapter.HolderView> {

    private List<PersonalHomePageDeatilBean.DataBean> list;
    private  Context context ;
    private  LayoutInflater inflater;

    public PersonalHomepage_PhotoAdapter(List<PersonalHomePageDeatilBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= inflater.inflate(R.layout.fragment_personalhomepage_photo_item,parent,false);
        HolderView holder = new HolderView(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(HolderView holder, int position) {

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.imageView.getLayoutParams();
        int widthPixels =  context.getResources().getDisplayMetrics().widthPixels;
        layoutParams.width= widthPixels*1/3-6;
        layoutParams.height= widthPixels*1/3-6;
        holder.imageView.requestLayout();
        for (int i = 0; i < list.size(); i++) {


        }
        Glide.with(context).load(list.get(position)
                .getContents().get(0).getPhoto_url()).asBitmap().dontAnimate().into(holder.imageView);





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  HolderView extends RecyclerView.ViewHolder {
        ImageView imageView;

        public HolderView(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.photo_imgs);

        }
    }

}
