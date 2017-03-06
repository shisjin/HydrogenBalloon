package com.dream.will.hydrogenballoon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.MyApp;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.apimanage.IDestinationApi;
import com.dream.will.hydrogenballoon.apimanage.RetrofitManager;
import com.dream.will.hydrogenballoon.bean.DbUserBean;
import com.dream.will.hydrogenballoon.bean.Destinations;
import com.dream.will.hydrogenballoon.bean.UrlString;
import com.dream.will.hydrogenballoon.content.DestinationConstent;
import com.dream.will.hydrogenballoon.content.IntentConstant;
import com.dream.will.hydrogenballoon.customview.CollapseTextView;
import com.dream.will.hydrogenballoon.customview.CollectionView;
import com.dream.will.hydrogenballoon.customview.DestinationView;
import com.dream.will.hydrogenballoon.customview.GoodsView;
import com.dream.will.hydrogenballoon.customview.MyMapView;
import com.dream.will.hydrogenballoon.customview.PlanView;
import com.dream.will.hydrogenballoon.fragment.GonglvFragment;
import com.dream.will.hydrogenballoon.inter.OnDestinationClickListener;
import com.dream.will.hydrogenballoon.other.UtilString;
import com.dream.will.hydrogenballoon.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 */

public class DestinationActivity extends BaseActivity implements OnDestinationClickListener, MyMapView.OnMyMapClickListener {
    private Toolbar mToolbar;
    private AppBarLayout mDestinationContentAppbar;
    private TextView mDestinationConDestTitle;
    private Button mDestinationConDestBtn;
    private LinearLayout mDestinationConDest;
    private TextView mDestinationConPlanTitle;
    private TextView mDestinationConPlanSubTitle;
    private MyMapView mDestinationConPlanMap;
    private LinearLayout mDestinationConPlan;
    private TextView mDestinationConCollectionTitle;
    private TextView mDestinationConCollectionSubTitle;
    private LinearLayout mDestinationConCollection;
    private TextView mDestinationConUseractivityTitle;
    private ImageView mDestinationConUseractivityImg;
    private TextView mDestinationConUseractivityUsername;
    private Button mDestinationConUseractivityBtn;
    private LinearLayout mDestinationConUseractivity;
    private TextView mDestinationErrorText;
    private ProgressBar mDestinationProgressBar;
    private CollectionView mDestinationContentHeadview;
    private GoodsView mDestinationConGoodsView;
    private DestinationView mDestinationConDestView;
    private CollapseTextView mDestinationConUseractivityDescri;
    private int main_id;
    private PlanView mDestinationConPlanView;
    private CoordinatorLayout mDestinationConCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        main_id = getIntent().getIntExtra(IntentConstant.INTNET_DESTINATION_KEY, 93);
        initViews();
        initData();
        initEvents();
    }

    public void initViews() {
        Log.i("google.karlo", "initViews: ");
        mDestinationConCoordinatorLayout = ((CoordinatorLayout) findViewById(R.id.destination_con_coordinatorLayout));
        mDestinationContentHeadview = (CollectionView) findViewById(R.id.destination_content_headview);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDestinationContentAppbar = (AppBarLayout) findViewById(R.id.destination_content_appbar);
        mDestinationConDestTitle = (TextView) findViewById(R.id.destination_con_dest_title);
        mDestinationConDestView = ((DestinationView) findViewById(R.id.destination_con_destView));
        mDestinationConDestBtn = (Button) findViewById(R.id.destination_con_dest_btn);
        mDestinationConDest = (LinearLayout) findViewById(R.id.destination_con_dest);
        mDestinationConPlanTitle = (TextView) findViewById(R.id.destination_con_plan_title);
        mDestinationConPlanSubTitle = (TextView) findViewById(R.id.destination_con_plan_subTitle);
        mDestinationConPlanMap = (MyMapView) findViewById(R.id.destination_con_plan_map);
        mDestinationConPlan = (LinearLayout) findViewById(R.id.destination_con_plan);
        mDestinationConPlanView = ((PlanView) findViewById(R.id.destination_con_planView));
        mDestinationConCollectionTitle = (TextView) findViewById(R.id.destination_con_collection_title);
        mDestinationConCollectionSubTitle = (TextView) findViewById(R.id.destination_con_collection_subTitle);
        mDestinationConCollection = (LinearLayout) findViewById(R.id.destination_con_collection);
        mDestinationConUseractivityTitle = (TextView) findViewById(R.id.destination_con_useractivity_title);
        mDestinationConUseractivityImg = (ImageView) findViewById(R.id.destination_con_useractivity_img);
        mDestinationConUseractivityUsername = (TextView) findViewById(R.id.destination_con_useractivity_username);
        mDestinationConUseractivityDescri = ((CollapseTextView) findViewById(R.id.destination_con_useractivity_descri));
        mDestinationConUseractivityBtn = (Button) findViewById(R.id.destination_con_useractivity_btn);
        mDestinationConUseractivity = (LinearLayout) findViewById(R.id.destination_con_useractivity);
        mDestinationErrorText = (TextView) findViewById(R.id.destination_errorText);
        mDestinationProgressBar = (ProgressBar) findViewById(R.id.destination_progressBar);
        mDestinationConGoodsView = (GoodsView) findViewById(R.id.destination_con_GoodsView);
        setSupportActionBar(mToolbar);
    }

    public void initData() {
        Retrofit retrofit = RetrofitManager.getInstance(ApiConstant.APPHOST_TYPE);
        IDestinationApi iDestinationApi = retrofit.create(IDestinationApi.class);
        Call<Destinations> call = iDestinationApi.requestDestionationById(main_id);

        call.enqueue(new Callback<Destinations>() {
            @Override
            public void onResponse(Call<Destinations> call, Response<Destinations> response) {
                mDestinationProgressBar.setVisibility(View.GONE);
                if (mDestinationErrorText.getVisibility() == View.VISIBLE) {
                    mDestinationErrorText.setVisibility(View.GONE);
                }
                if (response.isSuccessful()) {
                    mToolbar.setVisibility(View.VISIBLE);
                    mDestinationConCoordinatorLayout.setVisibility(View.VISIBLE);
                    Log.i("google.karlo", "onResponse: " + response.body());
                    Destinations destinations = response.body();
                    Log.i("google.karlo", "onResponse: Destinations" + destinations.toString());
                    Destinations.DataBean data = destinations.getData();
                    Destinations.DataBean.DestinationBean destination = data.getDestination();
                    List<Destinations.DataBean.GoodsBean> goods = data.getGoods();
                    initHeadView(destination);
                    initGoods(goods);
                    initLayout(data.getSections());
                } else {
                    Log.i("google.karlo", "onResponse: noSuccessful");
                    mDestinationErrorText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Destinations> call, Throwable t) {
                mDestinationProgressBar.setVisibility(View.GONE);
                mDestinationErrorText.setVisibility(View.VISIBLE);
                Log.i("google.karlo", "onFailure: " + t.getMessage());
            }
        });
    }

    private void initHeadView(@NonNull Destinations.DataBean.DestinationBean destination) {
        String name_en = destination.getName_en();
        String name = destination.getName();
        String photo_url = destination.getPhoto_url();
        /*这里存入数据库*/
        long endTime = System.currentTimeMillis();
        GonglvFragment.wantToView.setText(new DbUserBean(MyApp.getInstance().USERNAME,name,main_id+"",endTime+""));
        mToolbar.setTitle(name);
        mDestinationContentHeadview.setTv_title(name);
        mDestinationContentHeadview.setTv_titleSize(28);
        mDestinationContentHeadview.setTv_subtitle(name_en);
        mDestinationContentHeadview.setTv_subtitleSize(18);
        mDestinationContentHeadview.setImg(photo_url);
    }

    private void initGoods(@NonNull List<Destinations.DataBean.GoodsBean> goods) {
        mDestinationConGoodsView.setVisibility(View.VISIBLE);
        mDestinationConGoodsView.setData(goods);
        mDestinationConGoodsView.setListener(this);
    }

    private void initLayout(@NonNull List<Destinations.DataBean.SectionsBean> sections) {
        for (int i = 0; i < sections.size(); i++) {
            Destinations.DataBean.SectionsBean sectionsBean = sections.get(i);
            switch (sectionsBean.getType()) {
                case DestinationConstent.SECTIONS_COLLECTION: {
                    initCollections(sectionsBean);
                }
                break;
                case DestinationConstent.SECTIONS_DESTINATION: {
                    initDestination(sectionsBean);
                }
                break;
                case DestinationConstent.SECTIONS_PLAM: {
                    initPlan(sectionsBean);
                }
                break;
                case DestinationConstent.SECTIONS_USERACTIVITY: {
                    initUserActivity(sectionsBean);
                }
                break;
            }
        }
    }

    private void initDestination(@NonNull Destinations.DataBean.SectionsBean sectionsBean) {
        mDestinationConDest.setVisibility(View.VISIBLE);
        mDestinationConDestTitle.setText(sectionsBean.getTitle());
        mDestinationConDestBtn.setText(sectionsBean.getButton_text());
        /*当前目的地ID设为tag*/
        mDestinationConDestBtn.setTag(main_id);
        List<Destinations.DataBean.SectionsBean.ModelsBean> models = sectionsBean.getModels();
        mDestinationConDestView.setData(models);
        mDestinationConDestView.setListener(this);
    }

    private void initUserActivity(@NonNull Destinations.DataBean.SectionsBean sectionsBean) {
        mDestinationConUseractivity.setVisibility(View.VISIBLE);
        mDestinationConUseractivityBtn.setText(sectionsBean.getButton_text());
        mDestinationConUseractivityTitle.setText(sectionsBean.getTitle());
        List<Destinations.DataBean.SectionsBean.ModelsBean> models = sectionsBean.getModels();
        Destinations.DataBean.SectionsBean.ModelsBean modelsBean = models.get(0);
        /*设置distrit_id为tag*/
        mDestinationConUseractivityBtn.setTag(modelsBean.getDistrict_id());
        Log.i("google.karlo", "initUserActivity: 设置title");
        mDestinationConUseractivityDescri.setTitle(modelsBean.getTopic());
        mDestinationConUseractivityDescri.setContent(modelsBean.getDescription());
        Destinations.DataBean.SectionsBean.ModelsBean.UserBean user = modelsBean.getUser();
        mDestinationConUseractivityUsername.setText(user.getName());
        /*设置个人ID为tag*/
        mDestinationConUseractivityUsername.setTag(user.getId());
        /*图片集*/
        List<Destinations.DataBean.SectionsBean.ModelsBean.ContentsBean> contents = modelsBean.getContents();
        mDestinationConUseractivityImg.setTag(R.string.destination_user_photoSet, contents);
        Glide.with(this).load(contents.get(0).getPhoto_url()).asBitmap().dontAnimate().placeholder(R.color.picture_placeholder).into(mDestinationConUseractivityImg);
    }

    private void initCollections(@NonNull Destinations.DataBean.SectionsBean sectionsBean) {
        mDestinationConCollection.setVisibility(View.VISIBLE);
        mDestinationConCollectionTitle.setText(sectionsBean.getTitle());
        List<Destinations.DataBean.SectionsBean.ModelsBean> models = sectionsBean.getModels();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.collectionView_height));
        params.setMargins(0, 10, 0, 10);
        float titlePxSize = DisplayUtil.px2sp(this, getResources().getDimension(R.dimen.collectionView_Title_textSize));
        float subTitlePxSize = DisplayUtil.px2sp(this, getResources().getDimension(R.dimen.collectionView_subTitle_textSize));
        for (Destinations.DataBean.SectionsBean.ModelsBean modelsBean : models) {
            CollectionView view = new CollectionView(this);
            view.setTag(main_id);
            view.setTv_title(modelsBean.getTitle());
            view.setTv_titleSize(titlePxSize);
            view.setTv_subtitle(modelsBean.getSummary());
            view.setTv_subtitleSize(subTitlePxSize);
            view.setImg(modelsBean.getPhoto_url());
            view.setListener(this);
            mDestinationConCollection.addView(view, params);
        }
    }

    private void initPlan(@NonNull Destinations.DataBean.SectionsBean sectionsBean) {
        mDestinationConPlan.setVisibility(View.VISIBLE);
        mDestinationConPlanTitle.setText(sectionsBean.getTitle());
        mDestinationConPlanView.setListener(this);
        mDestinationConPlanView.setData(sectionsBean.getModels());
        mDestinationConPlanMap.setListener(this);
    }

    public void initEvents() {

        mDestinationErrorText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                mDestinationProgressBar.setVisibility(View.VISIBLE);
                initData();
            }
        });
        mDestinationContentAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();
//                Log.i("google.karlo", "onOffsetChanged--verticalOffset: " + verticalOffset);
//                Log.i("google.karlo", "onOffsetChanged--totalScrollRange: " + totalScrollRange);
                if (verticalOffset == 0) {
//                    mToolbar.setAlpha(0.0f);
                    mToolbar.setTitleTextColor(Color.alpha(0));
//                    mToolbarTV_Title.setAlpha(0.0f);
//                    mToolbar.setBackgroundColor(Color.alpha(0));//单纯设置透明度，背景色
                    mToolbar.setBackgroundColor(Color.argb(0, 55, 201, 202));
                } else {
                    float alpha = Math.abs(1.0f * verticalOffset / totalScrollRange);
                    Log.i("google.karlo", "onOffsetChanged---alpha: " + alpha);
//                    mToolbar.setAlpha(alpha);
//                    mToolbar.setBackgroundColor(Color.alpha((int) (alpha*255)));
                    mToolbar.setBackgroundColor(Color.argb((int) (alpha * 255), 55, 201, 202));
//                    mToolbarTV_Title.setAlpha(alpha);
                    mToolbar.setTitleTextColor(Color.argb((int) (alpha * 255), 255, 255, 255));
                }
            }
        });

        mDestinationConDestBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*跳转到当前目的地的概览与地图*/
                int id = (int) v.getTag();
                Toast.makeText(DestinationActivity.this, "概览与地图：" + id, Toast.LENGTH_SHORT).show();
            }
        });
        mDestinationConUseractivityBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*跳转到当前目的地的游记*/
                int id = (int) v.getTag();
                Toast.makeText(DestinationActivity.this, "游记：" + id, Toast.LENGTH_SHORT).show();
            }
        });

        mDestinationConUseractivityUsername.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) v.getTag();
                /*跳转到个人主页*/
                Toast.makeText(DestinationActivity.this, "个人主页：" + id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DestinationActivity.this, PersonalHomepage.class);
                intent.putExtra(UtilString.USERID, String.valueOf(id));
                startActivity(intent);
            }
        });
        mDestinationConUseractivityImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*相关氢游记，图片集*/
                Toast.makeText(DestinationActivity.this, "图片集", Toast.LENGTH_SHORT).show();
                List<Destinations.DataBean.SectionsBean.ModelsBean.ContentsBean> contents = (List<Destinations.DataBean.SectionsBean.ModelsBean.ContentsBean>) v.getTag(R.string.destination_user_photoSet);
                ArrayList<UrlString> url = new ArrayList<>();
                for (Destinations.DataBean.SectionsBean.ModelsBean.ContentsBean bean : contents) {
                    UrlString us = new UrlString();
                    us.setUrl(bean.getPhoto_url());
                    url.add(us);
                }
                Intent intent = new Intent(DestinationActivity.this, ShowPicActivity.class);
                intent.putExtra(IntentConstant.KEY_SHOW_PIC_CURRENT, 1);
                intent.putParcelableArrayListExtra(IntentConstant.KEY_SHOW_PIC_URL, url);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(String type, View view) {
        Object tag = view.getTag();
        switch (type) {
            case DestinationConstent.GOODS_WIKI: {
              /*跳转到攻略页面*/
                int id = (int) tag;
                Toast.makeText(this, id + "攻略", Toast.LENGTH_SHORT).show();
            }
            break;
            case DestinationConstent.GOODS_HOTEL: {
                Toast.makeText(this, "酒店", Toast.LENGTH_SHORT).show();
            }
            break;
            case DestinationConstent.GOODS_AIR_TICKET: {
                Toast.makeText(this, "机票", Toast.LENGTH_SHORT).show();
            }
            break;
            case DestinationConstent.GOODS_CTRIP_FREE_TRAVEL: {
                Toast.makeText(this, "自由行", Toast.LENGTH_SHORT).show();
            }
            break;
            case DestinationConstent.GOODS_CTRIP_GROUP_TRAVEL: {
                Toast.makeText(this, "跟团游", Toast.LENGTH_SHORT).show();
            }
            break;
            case DestinationConstent.GOODS_CTRIP_TICKET: {
                Toast.makeText(this, "门票", Toast.LENGTH_SHORT).show();
            }
            break;
            case DestinationConstent.SECTIONS_COLLECTION: {
                /*跳转到榜单列表页面*/
                int id = (int) tag;
                Toast.makeText(this, id + "榜单列表页面", Toast.LENGTH_SHORT).show();
            }
            break;
            case DestinationConstent.SECTIONS_DESTINATION: {
              /*跳转到新的目的地页面*/
                int id = (int) tag;
//                String name = (String) view.getTag(R.string.destination_name);
                Intent intent = new Intent(this, DestinationActivity.class);
                intent.putExtra(IntentConstant.INTNET_DESTINATION_KEY, id);
                startActivity(intent);
                Toast.makeText(this, id + "新的目的地页面", Toast.LENGTH_SHORT).show();
            }
            break;
            case DestinationConstent.SECTIONS_PLAM: {
                mDestinationConPlanMap.setData((Destinations.DataBean.SectionsBean.ModelsBean) tag);
            }
        }
    }

    @Override
    public void OnMapClick(int planId) {
        /*跳转到路线行程*/
        Toast.makeText(this, "路线行程" + planId, Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mDestinationConPlanMap) {
            mDestinationConPlanMap.onMapResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mDestinationConPlanMap) {
            mDestinationConPlanMap.onMapPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mDestinationConPlanMap) {
            mDestinationConPlanMap.onMapDestroy();
        }
    }

}
