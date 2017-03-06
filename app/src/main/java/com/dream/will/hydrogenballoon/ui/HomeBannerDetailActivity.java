package com.dream.will.hydrogenballoon.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.adapter.AbsAdapter;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.BannerDetaliBean;
import com.dream.will.hydrogenballoon.bean.UrlString;
import com.dream.will.hydrogenballoon.content.IntentConstant;
import com.dream.will.hydrogenballoon.inter.BannerDetali_Interface;
import com.dream.will.hydrogenballoon.other.UtilString;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeBannerDetailActivity extends AppCompatActivity {


    private ListView bnner_ListView;
    private WebView web_banner_detail;
    private BannerDetaliBean data;


    private List<BannerDetaliBean.DataBean.ItemsBean> listData;

    private AbsAdapter<BannerDetaliBean.DataBean.ItemsBean> adapter;

    //多布局
    private int[] layoutId = {R.layout.item_text_content_home_banner,
            R.layout.item_title_home_banner,
            R.layout.item_banner_detail_person};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__banner__detail_);
        initView();
        //初始化数据源
        init();

    }

    private void init() {

        listData = new ArrayList<>();
        /**
         * 获取传值（id,advert_type）
         * 如果传过来的"advert_type": "url" ---》webView
         * "advert_type": "album",----》正常下载内容
         */
        Intent intent = getIntent();
         String type = intent.getStringExtra(UtilString.BANNERTYPE);


        if (type.equals("url") ) {
            bnner_ListView.setVisibility(View.GONE);
            web_banner_detail.setVisibility(View.VISIBLE);
            web_banner_detail.getSettings().setJavaScriptEnabled(true);
            web_banner_detail.setWebChromeClient(new WebChromeClient());
            web_banner_detail.setWebViewClient(new WebViewClient());
            web_banner_detail.loadUrl("http://m.ctrip.com/html5/you/operations/app.html?app=1&popup=close&autoawaken=close");

        } else {
            int id= Integer.parseInt(intent.getStringExtra(UtilString.BANNERID));
            web_banner_detail.setVisibility(View.GONE);
            bnner_ListView.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstant.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            BannerDetali_Interface interFacr = retrofit.create(BannerDetali_Interface.class);
            Call<BannerDetaliBean> call = interFacr.getBannerDetailBean(id);
            call.enqueue(new Callback<BannerDetaliBean>() {
                @Override
                public void onResponse(Call<BannerDetaliBean> call, Response<BannerDetaliBean> response) {
                    BannerDetaliBean body = response.body();
                    Log.d("print", " ==="+call.request());
                    data = body;

                    listData.clear();
                    listData.addAll(body.getData().getItems());

                    //设置值
                    setData();
                }

                @Override
                public void onFailure(Call<BannerDetaliBean> call, Throwable t) {
                    Log.d("print", " retrofit--->失敗");
                }
            });
        }
    }

    //设置值
    private void setData() {


        adapter = new AbsAdapter<BannerDetaliBean.DataBean.ItemsBean>(listData, this,
                layoutId[0], layoutId[1], layoutId[2]) {


            @Override
            public int getItemViewType(int position) {
                BannerDetaliBean.DataBean.ItemsBean bean = listData.get(position);
                if (bean.getUser_activity() != null) {
                    return 2;
                } else if (bean.getTitle() != null) {
                    return 1;
                } else {
                    return 0;
                }
            }

            @Override
            public void bindData(int position, ViewHolder holder) {
                final BannerDetaliBean.DataBean.ItemsBean bean = listData.get(position);
                //第三种布局
                if (bean.getUser_activity()!=null) {
                    //头像
                    final ImageView iv_head = (ImageView) holder.findViewById(R.id.iv_banner_person_head);
                    Glide.with(HomeBannerDetailActivity.this)
                            .load(bean.getUser_activity().getUser().getPhoto_url())
                            .asBitmap() //必须要加上这个才能使用BitmapImageViewTarget类 才能设置圆形图片
                            .error(R.drawable.img_app_introduce)
                            .placeholder(R.drawable.img_app_introduce)
                            .centerCrop().into(new BitmapImageViewTarget(iv_head) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(HomeBannerDetailActivity.this.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            iv_head.setImageDrawable(circularBitmapDrawable);
                        }
                    });
                   /* iv_head.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });*/


                    //名字
                    TextView tv_name = (TextView) holder.findViewById(R.id.item_banner_detail_person_name);
                    tv_name.setText(bean.getUser_activity().getUser().getName());

                    //关注（他、她）
                    TextView tv_gender = (TextView) holder.findViewById(R.id.tv_gender);
                    if(bean.getUser_activity().getUser().getGender()==0){
                        tv_gender.setText("关注她");
                    }else {
                        tv_gender.setText("关注他");
                    }
                   /* tv_gender.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });*/


                    //大图片
                    ImageView iv_big = (ImageView) holder.findViewById(R.id.iv_banner_detail_big);
                    Glide.with(HomeBannerDetailActivity.this)
                            .load(bean.getUser_activity().getContents().get(0).getPhoto_url())
                            .into(iv_big);

                    iv_big.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //跳转到showPicActivity界面显示图片细节
                            ArrayList<UrlString> url = new ArrayList<>();
                            List<BannerDetaliBean.DataBean.ItemsBean.UserActivityBean.ContentsBean> contents = bean.getUser_activity().getContents();
                            for (int i = 0; i < contents.size(); i++) {
                                UrlString e = new UrlString(contents.get(i).getPhoto_url(), (String) contents.get(i).getCaption());
                                url.add(e);
                            }
                            Intent intent = new Intent(HomeBannerDetailActivity.this,ShowPicActivity.class);
                            intent.putExtra(IntentConstant.KEY_SHOW_PIC_CURRENT,1);
                            intent.putParcelableArrayListExtra(IntentConstant.KEY_SHOW_PIC_URL,url);
                            startActivity(intent);
                        }
                    });


                    //大图下面的小图
                    LinearLayout layout = (LinearLayout) holder.findViewById(R.id.layout_banner_hscrollw);
                    List<BannerDetaliBean.DataBean.ItemsBean.UserActivityBean.ContentsBean> list = bean.getUser_activity().getContents();
                    for (int i = 1; i < list.size(); i++) {
                        ImageView imageView = new ImageView(HomeBannerDetailActivity.this);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((getResources().getDisplayMetrics().widthPixels) /2,getResources().getDisplayMetrics().heightPixels /6);
                        params.setMargins(0, 5, 5, 0);
                        Glide.with(HomeBannerDetailActivity.this)
                                .load(list.get(i).getPhoto_url())
                                .into(imageView);

                        final int finalI = i;
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //跳转到showPicActivity界面显示图片细节
                                ArrayList<UrlString> url = new ArrayList<>();
                                List<BannerDetaliBean.DataBean.ItemsBean.UserActivityBean.ContentsBean> contents = bean.getUser_activity().getContents();
                                for (int j = 0; j < contents.size(); j++) {
                                    UrlString e = new UrlString(contents.get(j).getPhoto_url(), (String) contents.get(j).getCaption());
                                    url.add(e);
                                }
                                Intent intent = new Intent(HomeBannerDetailActivity.this,ShowPicActivity.class);
                                intent.putExtra(IntentConstant.KEY_SHOW_PIC_CURRENT, finalI+1);
                                intent.putParcelableArrayListExtra(IntentConstant.KEY_SHOW_PIC_URL,url);
                                startActivity(intent);
                            }
                        });

                        layout.addView(imageView, params);
                    }

                    //内容标题
                    TextView tv_title = (TextView) holder.findViewById(R.id.item_banner_person_title);
                    tv_title.setText(bean.getUser_activity().getTopic());

                    //内容
                    final TextView tv_long_content = (TextView) holder.findViewById(R.id.long_banner_person_content);
                    tv_long_content.setText(bean.getUser_activity().getDescription());

                    final TextView tv_short_content = (TextView) holder.findViewById(R.id.short_banner_person_content);
                    tv_short_content.setText(bean.getUser_activity().getDescription());




                    //点击显示全文
                    final Button button = (Button) holder.findViewById(R.id.banner_person_quanwen);
                    //获取内容高度
                    int long_height = tv_long_content.getHeight();
                    int short_height = tv_short_content.getHeight();
                    if(long_height>short_height){
                        tv_long_content.setVisibility(View.GONE);
                        tv_short_content.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);

                    }else {
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
                    LinearLayout layout1 = (LinearLayout) holder.findViewById(R.id.linearLayout_banner_tab);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(5, 5, 5, 5);
                    List<BannerDetaliBean.DataBean.ItemsBean.UserActivityBean.DistrictsBean> tablist = bean.getUser_activity().getDistricts();
                    Log.d("print", " 标签"+tablist.size());
                    layout1.removeAllViews();

                    for (int i = 0; i < tablist.size(); i++) {
                        Log.d("print", " ---->"+i);
                        TextView textView = new TextView(HomeBannerDetailActivity.this);
                        textView.setBackgroundResource(R.drawable.banner_text_bg_shape);
                     //   textView.setTextColor(getResources().getColor(R.color.banner_wanto_gray));
                        textView.setPadding(15,4,15,4);
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
                    Button btn_zan = (Button) holder.findViewById(R.id.btn_zan_banner_datail);
                      btn_zan.setText(bean.getUser_activity().getLikes_count()+"");

                   /* zan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });*/

                    //评论
                    Button btn_pl = (Button) holder.findViewById(R.id.btn_pl_banner_datail);
                    btn_pl.setText(bean.getUser_activity().getComments_count()+"");
                  /*
                    btn_pl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });*/

                    //收藏
                    Button btn_sc = (Button) holder.findViewById(R.id.btn_shoucang_banner_datail);
                    btn_sc.setText(bean.getUser_activity().getFavorites_count()+"");
                    /*btn_sc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });*/

                    //分享
                    Button btn_fx = (Button) holder.findViewById(R.id.button);

                   /* btn_fx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });*/

                    //第二种布局
                } else if (bean.getTitle() != null) {
                    TextView title = (TextView) holder.findViewById(R.id.tv_item_home_banner_title);
                    title.setText(bean.getTitle().toString());
                    TextView tv_content = (TextView) holder.findViewById(R.id.tv_item_home_banner_content);
                    tv_content.setText(bean.getDescription().toString());


                    //第三种布局
                } else {
                    TextView textView = (TextView) holder.findViewById(R.id.author_name);
                    String str = (String) bean.getDescription();
                    // "#@王小君#:虽然公里数不长，但是汇聚浓缩了南太平很多美景。"
                    String substring = str.substring(1, str.lastIndexOf("#"));
                    String substring1 = str.substring(str.lastIndexOf("#")+1, str.length());
                    String text = substring + substring1;
                    SpannableString spannableString = new SpannableString(text);
                    spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                            0, str.indexOf(":")-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);

                }

            }

        };

        View view = LayoutInflater.from(this).inflate(R.layout.banner_detail_title, null);
        TextView tv_banner_title = (TextView) view.findViewById(R.id.title);
        tv_banner_title.setText(data.getData().getTitle());
        bnner_ListView.addHeaderView(view);

        bnner_ListView.setAdapter(adapter);

    }


    private void initView() {
        bnner_ListView = (ListView) findViewById(R.id.bnner_ListView);
        web_banner_detail = (WebView) findViewById(R.id.web_banner_detail);
    }
}
