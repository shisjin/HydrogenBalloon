package com.dream.will.hydrogenballoon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.PersonalHomePageHeadBean;
import com.dream.will.hydrogenballoon.fragment.personalhomepage_fragment.PersonalHomepage_DetailFragment;
import com.dream.will.hydrogenballoon.fragment.personalhomepage_fragment.PersonalHomepage_GroupedFragment;
import com.dream.will.hydrogenballoon.fragment.personalhomepage_fragment.PersonalHomepage_PhotoFragment;
import com.dream.will.hydrogenballoon.inter.IPersonalHomepage;
import com.dream.will.hydrogenballoon.other.UtilString;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PersonalHomepage extends AppCompatActivity {


    private ImageView homepage_header_photo_bg;
    private ImageView header_photo;
    private ImageView header_user_v;
    private  TabLayout personaltabLayout;
    private TextView username;
    private ImageView user_sex_iv;
    private TextView center_line;
    private TextView followings_count,guangzhutv;
    private TextView followers_count;
    private Toolbar toolbar;
    private RadioGroup rg;
    private  ViewPager  personal_viewpager;
    private PersonalHomePageHeadBean.DataBean dataBean;
    private Fragment [] phfragments= new Fragment[3]; ;
    private  int userID=10586;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_homepage);
        initView();

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String extra = intent.getStringExtra(UtilString.USERID);
        Log.d("print", " userID"+extra);
        userID=Integer.valueOf(extra).intValue();
        initFragmnet();
        initDatas(userID);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                showFragment(checkedId);


            }


        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });


    }


    private void initFragmnet() {

        phfragments[0]=new PersonalHomepage_DetailFragment();
        phfragments[1] =new PersonalHomepage_PhotoFragment();
        phfragments[2]=new PersonalHomepage_GroupedFragment();


        personal_viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(final int position) {

                return phfragments[position];
            }

            @Override
            public int getCount() {
                return phfragments.length;
            }
        });

        personal_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("TAGSHISJIN","onPageSelected:"+position);
                switch (position){
                    case 0:{
                        ((RadioButton)findViewById(R.id.rb_1)).setChecked(true);
                        ((RadioButton)findViewById(R.id.rb_2)).setChecked(false);
                        ((RadioButton)findViewById(R.id.rb_3)).setChecked(false);

                    }
                    break;
                    case 1:{
                        ((RadioButton)findViewById(R.id.rb_1)).setChecked(false);
                        ((RadioButton)findViewById(R.id.rb_2)).setChecked(true);
                        ((RadioButton)findViewById(R.id.rb_3)).setChecked(false);
                    }
                    break;
                    case 2:{
                        ((RadioButton)findViewById(R.id.rb_1)).setChecked(false);
                        ((RadioButton)findViewById(R.id.rb_2)).setChecked(false);
                        ((RadioButton)findViewById(R.id.rb_3)).setChecked(true);
                    }
                    break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void showFragment(int checkedId) {

        switch (checkedId){
            case R.id.rb_1:{
                personal_viewpager.setCurrentItem(0);
            }break;
            case R.id.rb_2:{
                personal_viewpager.setCurrentItem(1);
            }break;
            case R.id.rb_3:{
                personal_viewpager.setCurrentItem(2);
            }break;
        }

    }

    void initDatas(int userID) {

        Bundle bundle = new Bundle();
        bundle.putInt("userID",userID);
        phfragments[0].setArguments(bundle);
        phfragments[1].setArguments(bundle);
        phfragments[2].setArguments(bundle);
        dataBean= new PersonalHomePageHeadBean.DataBean();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create()).build();
        IPersonalHomepage iPersonalHomepage = retrofit.create(IPersonalHomepage.class);
        Call<PersonalHomePageHeadBean> call =
                iPersonalHomepage.getPerHomeHeadBean(userID);

        call.enqueue(new Callback<PersonalHomePageHeadBean>() {
            @Override
            public void onResponse(Call<PersonalHomePageHeadBean>
                                           call, Response<PersonalHomePageHeadBean> response) {
                PersonalHomePageHeadBean body = response.body();
                PersonalHomePageHeadBean.DataBean dataBean1 = body.getData();
                dataBean.setPhoto_url(dataBean1.getPhoto_url());
                dataBean.setGender(dataBean1.getGender());
                dataBean.setName(dataBean1.getName());
                dataBean.setLevel(dataBean1.getLevel());
                dataBean.setFollowings_count(dataBean1.getFollowings_count());
                dataBean.setFollowers_count(dataBean1.getFollowers_count());
                dataBean.setHeader_photo(dataBean1.getHeader_photo());

                //
                username.setText(dataBean.getName());
                if (dataBean.getGender()==0){
                    user_sex_iv.setImageResource(R.drawable.gender_w);
                    guangzhutv.setText("关注她");
                }else {
                    user_sex_iv.setImageResource(R.drawable.gender_m);
                    guangzhutv.setText("关注他");
                }
                if (dataBean.getLevel()>2){
                    header_user_v.setVisibility(View.VISIBLE);
                }else {
                    header_user_v.setVisibility(View.INVISIBLE);
                }
                followings_count.setText(""+dataBean.getFollowings_count());
                followers_count.setText(""+dataBean.getFollowers_count());
                Glide.with(PersonalHomepage.this).load(dataBean1.getPhoto_url())
                        .asBitmap()
                        .dontAnimate().into(header_photo);
                Glide.with(PersonalHomepage.this).load(dataBean1.getHeader_photo().getPhoto_url())
                        .asBitmap()
                        .dontAnimate()
                        .into(homepage_header_photo_bg);

            }

            @Override
            public void onFailure(Call<PersonalHomePageHeadBean> call, Throwable t) {

            }
        });



    }




    private void initView() {
        homepage_header_photo_bg = (ImageView) findViewById(R.id.homepage_header_photo_bg);
        header_photo = (ImageView) findViewById(R.id.header_photo);
        header_user_v = (ImageView) findViewById(R.id.header_user_v);
        rg = (RadioGroup) findViewById(R.id.rg);
        username = (TextView) findViewById(R.id.username);
        guangzhutv = (TextView) findViewById(R.id.guangzhutv);
        user_sex_iv = (ImageView) findViewById(R.id.user_sex_iv);
        center_line = (TextView) findViewById(R.id.center_line);
        followings_count = (TextView) findViewById(R.id.followings_count);
        followers_count = (TextView) findViewById(R.id.followers_count);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        personal_viewpager = (ViewPager) findViewById(R.id.personal_viewpager);
    }



}
