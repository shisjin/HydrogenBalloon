package com.dream.will.hydrogenballoon.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.UrlString;
import com.dream.will.hydrogenballoon.bean.YouJiBean;
import com.dream.will.hydrogenballoon.content.IntentConstant;
import com.dream.will.hydrogenballoon.other.UtilString;
import com.dream.will.hydrogenballoon.ui.PersonalHomepage;
import com.dream.will.hydrogenballoon.ui.ShowPicActivity;

import java.util.ArrayList;
import java.util.List;

public class YouJiAdapter extends RecyclerView.Adapter<YouJiAdapter.MyViewHolder> {

    private List<YouJiBean.DataBean> dataBeen;
    private LayoutInflater inflater;
    private Context context;

    public YouJiAdapter(List<YouJiBean.DataBean> dataBeen, Context context) {
        this.dataBeen = dataBeen;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.item_youji_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(inflate);
        return viewHolder;
    }


    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final YouJiBean.DataBean bean = dataBeen.get(position);
        if (bean != null) {
            //头像
            final ImageView iv_head = (ImageView) holder.iv_banner_person_head;
            Glide.with(context)
                    .load(bean.getActivity().getUser().getPhoto_url())
                    .asBitmap() //必须要加上这个才能使用BitmapImageViewTarget类 才能设置圆形图片
                    .error(R.drawable.img_app_introduce)
                    .placeholder(R.drawable.img_app_introduce)
                    .centerCrop().into(new BitmapImageViewTarget(iv_head) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    iv_head.setImageDrawable(circularBitmapDrawable);
                }
            });

            //vip标志图
            ImageView iv_vip = (ImageView) holder.vip;
            if (bean.getActivity().getUser().getLevel() > 2) {
                iv_vip.setVisibility(View.VISIBLE);
            } else {
                iv_vip.setVisibility(View.INVISIBLE);
            }

            //头像跳转
            iv_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PersonalHomepage.class);
                    intent.putExtra(UtilString.USERID, bean.getActivity().getUser().getId() + "");
                    Log.d("print", " userid --intent-->" + bean.getActivity().getUser().getId());
                    context.startActivity(intent);
                }
            });


            //名字
            TextView tv_name = (TextView) holder.item_banner_detail_person_name;
            tv_name.setText(bean.getActivity().getUser().getName());

            //关注（他、她）
            TextView tv_gender = (TextView) holder.tv_gender;
            if (bean.getActivity().getUser().getGender() == 0) {
                tv_gender.setText("关注她");
            } else {
                tv_gender.setText("关注他");
            }
                   /* tv_gender.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });*/

            //来源
            TextView tv_come = (TextView) holder.tv_come;
            tv_come.setText(bean.getUser().getName());

            //大图片
            ImageView iv_big = (ImageView) holder.iv_banner_detail_big;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) iv_big.getLayoutParams();
            int width = bean.getActivity().getContents().get(0).getWidth();
            int widthPixels = context.getResources().getDisplayMetrics().widthPixels;

            float per = 0;
            if (width > widthPixels) {
                per = (float) widthPixels / (float) width;
                layoutParams.height = (int) ((bean.getActivity().getContents().get(0).getHeight()) * per);
                layoutParams.width = (int) ((bean.getActivity().getContents().get(0).getWidth()) * per);
            } else {
                per = (float) widthPixels / (float) width;
                layoutParams.height = (int) ((bean.getActivity().getContents().get(0).getHeight()) * per);
                layoutParams.width = (int) (widthPixels);
            }
            Log.d("print", " --per--->>" + per);

            iv_big.requestLayout();


            Glide.with(context)
                    .load(bean.getActivity().getContents().get(0).getPhoto_url())
                    .asBitmap()
                    .dontAnimate()
                    .into(iv_big);

            iv_big.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //跳转到showPicActivity界面显示图片细节
                    ArrayList<UrlString> url = new ArrayList<>();
                    List<YouJiBean.DataBean.ActivityBean.ContentsBean> contents = bean.getActivity().getContents();
                    for (int i = 0; i < contents.size(); i++) {
                        UrlString e = new UrlString(contents.get(i).getPhoto_url(), (String) contents.get(i).getCaption());
                        url.add(e);
                    }
                    Intent intent = new Intent(context, ShowPicActivity.class);
                    intent.putExtra(IntentConstant.KEY_SHOW_PIC_CURRENT, 1);
                    intent.putParcelableArrayListExtra(IntentConstant.KEY_SHOW_PIC_URL, url);
                    context.startActivity(intent);
                }
            });


            //大图下面的小图
            LinearLayout layout = (LinearLayout) holder.layout_banner_hscrollw;
            layout.removeAllViews();
            List<YouJiBean.DataBean.ActivityBean.ContentsBean> list = bean.getActivity().getContents();
            for (int i = 1; i < list.size(); i++) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((context.getResources().getDisplayMetrics().widthPixels) / 2, context.getResources().getDisplayMetrics().heightPixels / 6);
                params.setMargins(0, 5, 5, 0);
                Glide.with(context)
                        .load(list.get(i).getPhoto_url())
                        .asBitmap()
                        .dontAnimate()
                        .into(imageView);

                final int finalI = i;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转到showPicActivity界面显示图片细节
                        ArrayList<UrlString> url = new ArrayList<>();
                        List<YouJiBean.DataBean.ActivityBean.ContentsBean> contents = bean.getActivity().getContents();
                        for (int i = 0; i < contents.size(); i++) {
                            UrlString e = new UrlString(contents.get(i).getPhoto_url(), (String) contents.get(i).getCaption());
                            url.add(e);
                        }
                        Intent intent = new Intent(context, ShowPicActivity.class);
                        intent.putExtra(IntentConstant.KEY_SHOW_PIC_CURRENT, finalI+1);
                        intent.putParcelableArrayListExtra(IntentConstant.KEY_SHOW_PIC_URL, url);
                        context.startActivity(intent);
                    }
                });

                layout.addView(imageView, params);
            }

            //内容标题
            TextView tv_title = (TextView) holder.item_banner_person_title;
            tv_title.setText(bean.getActivity().getTopic());

            //内容
            final TextView tv_long_content = (TextView) holder.long_banner_person_content;
            tv_long_content.setVisibility(View.GONE);
            tv_long_content.setText(bean.getActivity().getDescription());

            final TextView tv_short_content = (TextView) holder.short_banner_person_content;
            tv_short_content.setVisibility(View.GONE);
            tv_short_content.setText(bean.getActivity().getDescription());


            //点击显示全文
            final Button button = (Button) holder.banner_person_quanwen;
            button.setVisibility(View.GONE);
            //获取内容高度
            int long_height = tv_long_content.getHeight();
            int short_height = tv_short_content.getHeight();
            if (long_height > short_height) {
                tv_long_content.setVisibility(View.GONE);
                tv_short_content.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);

            } else {
                tv_long_content.setVisibility(View.VISIBLE);
                tv_short_content.setVisibility(View.GONE);
                button.setVisibility(View.GONE);

            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_long_content.setVisibility(View.VISIBLE);
                    tv_short_content.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);

                }
            });

            //内容下的标签
            LinearLayout layout1 = (LinearLayout) holder.linearLayout_banner_tab;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 5, 5, 5);
            List<YouJiBean.DataBean.ActivityBean.DistrictsBean> tablist = bean.getActivity().getDistricts();
            Log.d("print", " 标签" + tablist.size());
            layout1.removeAllViews();

            for (int i = 0; i < tablist.size(); i++) {
                Log.d("print", " ---->" + i);
                TextView textView = new TextView(context);
                textView.setBackgroundResource(R.drawable.banner_text_bg_shape);
                //   textView.setTextColor(getResources().getColor(R.color.banner_wanto_gray));
                textView.setPadding(15, 4, 15, 4);
                textView.setText(tablist.get(i).getName());
                layout1.addView(textView, params);
                        /*textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });*/
            }

            //标签下的 点赞、评论、收藏、分享
            //点赞
            final Button btn_zan = (Button) holder.btn_zan_banner_datail;
            btn_zan.setText(bean.getActivity().getLikes_count() + "");

/*
                    btn_zan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("print", " 点赞 ooo");
                            setBtnListener(btn_zan, R.drawable.icon_like_highlight);
                            btn_zan.setText(bean.getUser_activity().getLikes_count() + 1 + "");
                        }
                    });
*/

            //评论
            Button btn_pl = (Button) holder.btn_pl_banner_datail;
            btn_pl.setText(bean.getActivity().getComments_count() + "");
                  /*
                    btn_pl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });*/

            //收藏
            Button btn_sc = (Button) holder.btn_shoucang_banner_datail;
            btn_sc.setText(bean.getActivity().getFavorites_count() + "");
                    /*btn_sc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });*/

            //分享
            Button btn_fx = (Button) holder.button;

                   /* btn_fx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });*/
        }


    }

    @Override
    public int getItemCount() {
        return dataBeen.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_banner_person_head;
        private ImageView vip;
        private TextView item_banner_detail_person_name;
        private TextView tv_come;
        private TextView tv_gender;
        private ImageView iv_banner_detail_big;
        private LinearLayout layout_banner_hscrollw;
        private TextView item_banner_person_title;
        private TextView long_banner_person_content;
        private TextView short_banner_person_content;
        private Button banner_person_quanwen;
        private LinearLayout linearLayout_banner_tab;
        private Button btn_zan_banner_datail;
        private Button btn_pl_banner_datail;
        private Button btn_shoucang_banner_datail;
        private Button button;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_banner_person_head = (ImageView) itemView.findViewById(R.id.iv_banner_person_head);
            vip = (ImageView) itemView.findViewById(R.id.vip);
            item_banner_detail_person_name = (TextView) itemView.findViewById(R.id.item_banner_detail_person_name);
            tv_come = (TextView) itemView.findViewById(R.id.tv_come);
            tv_gender = (TextView) itemView.findViewById(R.id.tv_gender);
            iv_banner_detail_big = (ImageView) itemView.findViewById(R.id.iv_banner_detail_big);
            layout_banner_hscrollw = (LinearLayout) itemView.findViewById(R.id.layout_banner_hscrollw);
            item_banner_person_title = (TextView) itemView.findViewById(R.id.item_banner_person_title);
            long_banner_person_content = (TextView) itemView.findViewById(R.id.long_banner_person_content);
            short_banner_person_content = (TextView) itemView.findViewById(R.id.short_banner_person_content);
            banner_person_quanwen = (Button) itemView.findViewById(R.id.banner_person_quanwen);
            linearLayout_banner_tab = (LinearLayout) itemView.findViewById(R.id.linearLayout_banner_tab);
            btn_zan_banner_datail = (Button) itemView.findViewById(R.id.btn_zan_banner_datail);
            btn_pl_banner_datail = (Button) itemView.findViewById(R.id.btn_pl_banner_datail);
            btn_shoucang_banner_datail = (Button) itemView.findViewById(R.id.btn_shoucang_banner_datail);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }

}
