package com.dream.will.hydrogenballoon.fragment.personalhomepage_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.adapter.PersonalHomepage_DetailAdapter;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.PersonalHomePageDeatilBean;
import com.dream.will.hydrogenballoon.inter.IPersonalHomepage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shisjin on 2016/12/20.
 */

public class PersonalHomepage_DetailFragment extends Fragment {
    private RecyclerView mrecyclerView;
    private  int userID ;
    private int page=1;
    private List<PersonalHomePageDeatilBean.DataBean> data;
    private PersonalHomepage_DetailAdapter adapter;

   private  SwipeRefreshLayout  swipeRefreshLayout;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:{
                    InData(userID,page);
                   swipeRefreshLayout.setRefreshing(false);
                };

            }


        }
    };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        userID = bundle.getInt("userID");
        data = new ArrayList<>();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personalhomepage_deatil,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        InData(userID,page);
        adapter= new PersonalHomepage_DetailAdapter(data,getActivity());
        mrecyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Message message = Message.obtain();
                message.what=0;
                handler.sendMessage(message);

            }
        });

        mrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });







    }

    private void init(View view) {
        mrecyclerView= (RecyclerView) view.findViewById(R.id.personaldeatil_recyclerview);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.homepage_detail_reFreshLayout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        mrecyclerView.addItemDecoration(new PH_DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));








    }

    private void InData(int userID,int page) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IPersonalHomepage personalHomepage = retrofit.create(IPersonalHomepage.class);
        Call<PersonalHomePageDeatilBean> call = personalHomepage.getPerHomeDeatBean(userID, page);
        call.enqueue(new Callback<PersonalHomePageDeatilBean>() {
            @Override
            public void onResponse(Call<PersonalHomePageDeatilBean> call, Response<PersonalHomePageDeatilBean> response) {
                PersonalHomePageDeatilBean body = response.body();
                List<PersonalHomePageDeatilBean.DataBean> data1 = body.getData();
                data.clear();
                data.addAll(data1);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PersonalHomePageDeatilBean> call, Throwable t) {

            }
        });



    }
}
