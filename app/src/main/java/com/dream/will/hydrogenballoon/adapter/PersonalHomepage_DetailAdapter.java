package com.dream.will.hydrogenballoon.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.PersonalHomePageDeatilBean;

import java.util.List;


public class PersonalHomepage_DetailAdapter extends RecyclerView.Adapter<PersonalHomepage_DetailAdapter.MyHodleView> {

    private List<PersonalHomePageDeatilBean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;


    public PersonalHomepage_DetailAdapter(List<PersonalHomePageDeatilBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHodleView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_personalhomepage_deatil_item, parent, false);
        MyHodleView hodleview = new MyHodleView(view);

        return hodleview;
    }

    @Override
    public void onBindViewHolder( MyHodleView holder, int position) {
        PersonalHomePageDeatilBean.DataBean dataBean = list.get(position);
      if (dataBean!=null){
        List<PersonalHomePageDeatilBean.DataBean.ContentsBean> contents = dataBean.getContents();
       //第一张图片：大图
        ImageView bigimg= holder.personaldeatil_bigiv;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)holder.personaldeatil_bigiv.getLayoutParams();
        int width = contents.get(0).getWidth();
        int widthPixels =context.getResources().getDisplayMetrics().widthPixels;
          Log.d("widthPixels","widthPixels"+widthPixels);
        float min=Math.min(width,widthPixels);
        float max=Math.max(width,widthPixels);
        float per = min/max;


          if (width<widthPixels){
              layoutParams.width= widthPixels;
              layoutParams.height= (int) (contents.get(0).getHeight()/per);
          }else {
              layoutParams.height= (int) (contents.get(0).getHeight()*per);
              layoutParams.width = (int) (contents.get(0).getWidth() * per);
          }
          holder.personaldeatil_bigiv.requestLayout();
         holder.personaldeatil_bigiv.setScaleType(ImageView.ScaleType.FIT_XY);
         Glide.with(context).load(contents.get(0).getPhoto_url())
                .asBitmap()
                .dontAnimate().into(holder.personaldeatil_bigiv);

        //滑动的图片
        LinearLayout layout = holder.linearLayout_personal_deatil_iv;
          layout.removeAllViews();
        for (int i = 1; i < contents.size(); i++) {
          ImageView images= new ImageView(context);
            images.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthPixels*2/5,widthPixels*1/4);
            params.setMargins(0,5,5,0);
            Glide.with(context).load(contents.get(i).getPhoto_url())
                    .asBitmap()
                    .dontAnimate().into(images);

            layout.addView(images,params);
        }

       // 标题
        holder.personal_deatil_title.setText(list.get(position).getTopic());
        //内容
          final TextView content_short = holder.personal_deatil_description;
          content_short.setText(list.get(position).getDescription());
          content_short.setVisibility(View.GONE);

          final TextView content_long = holder.personal_deatil_description_more;

          content_long.setText(list.get(position).getDescription());
          content_long.setVisibility(View.GONE);
          final LinearLayout diquyouji1=holder.linearlayout_diquyouji;
          holder.personal_deatil_districts.setText(list.get(position).getDistricts().get(0).getName());
          diquyouji1.setVisibility(View.GONE);

          final Button buttshowall =  holder.personal_deatil_showfulltext_bt;



        int long_text=  content_long.getHeight();
        int short_text= content_short.getHeight();

          if (long_text>short_text){
              content_short.setVisibility(View.VISIBLE);
              buttshowall.setVisibility(View.VISIBLE);
          }else {
              buttshowall.setVisibility(View.GONE);
              content_short.setVisibility(View.GONE);
              diquyouji1.setVisibility(View.VISIBLE);
              content_long.setVisibility(View.VISIBLE);
          }

          int visibility =  buttshowall.getVisibility();
          if (visibility==View.VISIBLE){
              buttshowall.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      diquyouji1.setVisibility(View.VISIBLE);
                      buttshowall.setVisibility(View.GONE);
                      content_short.setVisibility(View.GONE);
                      content_long.setVisibility(View.VISIBLE);
                  }
              });

          }






          String time = list.get(position).getMade_at();
          String mothe = "一月";
          String date = time.substring(5, 7);
          switch (date){
              case "01":{
                  mothe="一月";}break;
              case "02":{
                  mothe="二月";}break;
              case "03":{
                  mothe="三月";}break;
              case "04":{
                  mothe="四月";}break;
              case "05":{
                  mothe="五月";}break;
              case "06":{
                  mothe="六月";}break;
              case "07":{
                  mothe="七月";}break;
              case "08":{
                  mothe="八月";}break;
              case "09":{
                  mothe="九月";}break;
              case "10":{
                  mothe="十月";}break;
              case "11":{
                  mothe="十一月";}break;
              case "12":{
                  mothe="十二月";}break;
          }


          //滑动标签
          LinearLayout shaptab = holder.linearLayout_personal_deatil_tab;

          LinearLayout.LayoutParams params_tab = new LinearLayout.LayoutParams
                  (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params_tab.setMargins(10,2,10,2);
            List<PersonalHomePageDeatilBean.DataBean.DistrictsBean > districtsbean=list.get(position).getDistricts();
               shaptab.removeAllViews();
            for (int i = 0; i < districtsbean.size(); i++) {
                TextView textView = new TextView(context);
                textView.setBackgroundResource(R.drawable.personalhomepager_text_bg_shape);
                textView.setPadding(15,5,15,5);
                textView.setGravity(Gravity.CENTER);
                textView.setText(districtsbean.get(i).getName());
                textView.setTextSize(12);
                textView.setTextColor(Color.parseColor("#A5A6A6"));
                shaptab.addView(textView,params_tab);
            }
              if (list.get(position)!=null) {
                  PersonalHomePageDeatilBean.DataBean.PoiBean poibean = list.get(position).getPoi();
                  if (poibean!=null){
                      TextView textView2= new TextView(context);
                      textView2.setBackgroundResource(R.drawable.personalhomepager_text_bg_shape);
                      textView2.setPadding(15,5,15,5);
                      textView2.setGravity(Gravity.CENTER);
                      textView2.setText(poibean.getName());
                      textView2.setTextSize(12);
                      textView2.setTextColor(Color.parseColor("#A5A6A6"));
                      shaptab.addView(textView2,params_tab);
                  }
              }

          TextView textView3= new TextView(context);
            textView3.setBackgroundResource(R.drawable.personalhomepager_text_bg_shape);
            textView3.setPadding(15,5,15,5);
            textView3.setGravity(Gravity.CENTER);
            textView3.setText(mothe);
            textView3.setTextSize(12);
            textView3.setTextColor(Color.parseColor("#A5A6A6"));
            shaptab.addView(textView3,params_tab);











          //标签下的 点赞、评论、收藏、分享
          //点赞
            holder.btn_zan_personal_deatil.setText(list.get(position).getLikes_count()+"");



          //评论
       holder.btn_pl_personal_deatil
          .setText(list.get(position).getComments_count() + "");


          //收藏
         holder.btn_shoucang_personal_deatil
         .setText(list.get(position).getFavorites_count() + "");


          //分享
          Button btn_fx = holder.personal_deatil_more;







      }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class MyHodleView extends RecyclerView.ViewHolder {

        ImageView personaldeatil_bigiv;
        TextView personal_deatil_title;
        LinearLayout linearLayout_personal_deatil_iv;
        TextView personal_deatil_description;
        TextView personal_deatil_description_more;
        Button personal_deatil_showfulltext_bt;
        LinearLayout linearlayout_diquyouji;
        TextView personal_deatil_districts;
        LinearLayout linearLayout_personal_deatil_tab;
        Button btn_zan_personal_deatil;
        Button btn_pl_personal_deatil;
        Button btn_shoucang_personal_deatil;
        Button personal_deatil_more;

        public MyHodleView(View itemView) {
            super(itemView);
            personaldeatil_bigiv= (ImageView) itemView.findViewById(R.id.personaldeatil_bigiv);
            personal_deatil_title= (TextView) itemView.findViewById(R.id.personal_deatil_title);
            personal_deatil_description_more= (TextView) itemView.findViewById(R.id.personal_deatil_description_more);
            linearLayout_personal_deatil_iv= (LinearLayout) itemView.findViewById(R.id.linearLayout_personal_deatil_iv);
            linearlayout_diquyouji= (LinearLayout) itemView.findViewById(R.id.linearlayout_diquyouji);
            linearLayout_personal_deatil_tab= (LinearLayout) itemView.findViewById(R.id.linearLayout_personal_deatil_tab);
            personal_deatil_description= ( TextView) itemView.findViewById(R.id.personal_deatil_description);
            personal_deatil_districts= ( TextView) itemView.findViewById(R.id.personal_deatil_districts);
            personal_deatil_showfulltext_bt= ( Button) itemView.findViewById(R.id.personal_deatil_showfulltext_bt);
            btn_zan_personal_deatil= ( Button) itemView.findViewById(R.id.btn_zan_personal_deatil);
            btn_pl_personal_deatil= ( Button) itemView.findViewById(R.id.btn_pl_personal_deatil);
            btn_shoucang_personal_deatil= ( Button) itemView.findViewById(R.id.btn_shoucang_personal_deatil);
            personal_deatil_more= ( Button) itemView.findViewById(R.id.personal_deatil_more);

        }
    }
}

