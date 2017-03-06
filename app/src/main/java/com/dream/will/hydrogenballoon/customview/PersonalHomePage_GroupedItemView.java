package com.dream.will.hydrogenballoon.customview;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.PersonalHomePageGroupedBean;

import java.util.List;

public class PersonalHomePage_GroupedItemView extends LinearLayout {
    TextView month,day,personal_grouped_cityname,personal_grouped_title;
    ImageView grouped_img1,grouped_img2,grouped_img3,grouped_img4;

    public PersonalHomePage_GroupedItemView(Context context) {
        super(context);
        init();

    }

    public PersonalHomePage_GroupedItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();

    }

    private void init() {

        View view=inflate(getContext(),R.layout.fragment_personalhomepage_grouped_additem,this);
        month= (TextView) view.findViewById(R.id.month);
        day= (TextView) view.findViewById(R.id.day);
        personal_grouped_cityname= (TextView) view.findViewById(R.id.personal_grouped_cityname);
        personal_grouped_title= (TextView) view.findViewById(R.id.personal_grouped_title);
        grouped_img1= (ImageView) view.findViewById(R.id.grouped_img1);
        grouped_img2= (ImageView) view.findViewById(R.id.grouped_img2);
        grouped_img3= (ImageView) view.findViewById(R.id.grouped_img3);
        grouped_img4= (ImageView) view.findViewById(R.id.grouped_img4);
    }

    public   void  setGroupedItemView(String time , String cityname, String title,
                                      List<PersonalHomePageGroupedBean.DataBean.ActivitiesBean.ContentsBean> content){

            month.setText(time.substring(5, 7));
            day.setText(time.substring(8,10));
            personal_grouped_cityname.setText(cityname);
            personal_grouped_title.setText(title);
            if (content.size()>=4){
                Glide.with(getContext()).load(content.get(0).getPhoto_url()).asBitmap().dontAnimate().into(grouped_img1);
                Glide.with(getContext()).load(content.get(1).getPhoto_url()).asBitmap().dontAnimate().into(grouped_img2);
                Glide.with(getContext()).load(content.get(2).getPhoto_url()).asBitmap().dontAnimate().into(grouped_img3);
                Glide.with(getContext()).load(content.get(3).getPhoto_url()).asBitmap().dontAnimate().into(grouped_img4);
            }




    }


}
