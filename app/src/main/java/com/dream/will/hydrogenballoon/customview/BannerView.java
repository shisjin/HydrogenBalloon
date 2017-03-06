package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.GongLvBannerBean;
import com.dream.will.hydrogenballoon.inter.IGongLv;
import com.dream.will.hydrogenballoon.other.UtilString;
import com.dream.will.hydrogenballoon.ui.HomeBannerDetailActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 实现自动轮播 向外提供设置城市id，
 * 只要设置城市id，就能自动获取数据，得到图片进行轮播
 */

public class BannerView extends FrameLayout implements OnBannerClickListener {

    Banner banner;
    private List<GongLvBannerBean.DataBean> allData;

    public BannerView(Context context) {
        super(context);
        init();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        allData = new ArrayList<>();
        LayoutInflater.from(getContext()).inflate(R.layout.fragment_gonglv_banner, this, true);
        banner = (Banner) findViewById(R.id.gonglv_banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
//        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
//        //banner设置方法全部调用完毕时最后调用
        banner.setOnBannerClickListener(this);
//        banner.start();
        setCityId("city");
    }

    //提供城市id 设置方法
    public void setCityId(String cityId) {
        //retrofit 联网加载数据
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获取接口实例
        IGongLv iStore = re.create(IGongLv.class);
        Call<GongLvBannerBean> call = iStore.getBanner();
        //发请求
        call.enqueue(new Callback<GongLvBannerBean>() {
            @Override
            public void onResponse(Call<GongLvBannerBean> call, Response<GongLvBannerBean> response) {
                //得到BannerBean list
                //解析 list 得到url  标题
                List<String> imgUrls = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                GongLvBannerBean body = response.body();
                Log.i("TAG", "onResponse: -gonglv banner--------" + body);
                List<GongLvBannerBean.DataBean> data = body.getData();
                for (GongLvBannerBean.DataBean dataBean : data) {
                    imgUrls.add(dataBean.getPhoto().getPhoto_url());
                    titles.add(dataBean.getTopic());

                }
                allData.clear();
                allData.addAll(data);
                banner.setImages(imgUrls);
//                banner.setBannerTitles(titles);
                banner.start();
            }

            @Override
            public void onFailure(Call<GongLvBannerBean> call, Throwable t) {
                Log.i("TAG", "onFailure: --gonglv banner-----error--");
            }
        });

    }

    //设置banner监听
    @Override
    public void OnBannerClick(int position) {
        Log.i("TAG", "OnBannerClick: --------Banner Click:-" + position);
//        Toast.makeText(getContext(), "Banner Click::"+position, Toast.LENGTH_SHORT).show();
        /**
         *  "url":"7ee4cda5-293f-4b01-bb43-8ba9f0688283",
         "type":"1",
         */
        position-=1;
        Intent intent = new Intent(getContext(), HomeBannerDetailActivity.class);
        intent.putExtra(UtilString.BANNERID, allData.get(position).getTarget_id()+"");
        Log.d("print", " intent--id---》"+allData.get(position).getTarget_id());
        intent.putExtra(UtilString.BANNERTYPE, allData.get(position).getAdvert_type());
        Log.d("print", " intent--id---》"+allData.get(position).getAdvert_type());
//        intent.putExtra(Conten.KEY_STRORE_BANNER_TITLE,allData.get(position).getTitle());
        getContext().startActivity(intent);
    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(path).asBitmap().dontAnimate().into(imageView);
            Log.i("TAG", "displayImage: ---------" + path);
        }
    }
}
