package com.dream.will.hydrogenballoon.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.adapter.YouJiAdapter;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.YouJiBean;
import com.dream.will.hydrogenballoon.inter.IYouJI;
import com.dream.will.hydrogenballoon.other.DividerItemDecoration;
import com.dream.will.hydrogenballoon.other.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 */

public class YouJiFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<YouJiBean.DataBean> data;
    private YouJiAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                    refreshLayout.setRefreshing(false);
                    Log.d("print", " 刷新ok");
                }
                break;
                case 1: {
                    adapter.notifyDataSetChanged();
                    Log.d("print", " 上拉加载更多OK");
                    recyclerView.setAdapter(adapter);
                }
                break;
            }

        }
    };


    //页数
    private int page = 1;
    private boolean addmore = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        initdata(1);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initdata(1);
        recyclerView = (RecyclerView) view.findViewById(R.id.youji_recyclerView);

        //下拉刷新
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.reFreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initdata(1);
                Message message = Message.obtain();
                message.what = 0;
                handler.sendMessage(message);
                Log.d("print", " 刷新");
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                page++;
               // addMore(page);
                loadMore(page);
                Log.d("print", "page====> "+page);
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
                Log.d("print", " 上拉加载");

            }
        });
    }

    private void loadMore(int page) {
        Log.d("print", " addMore ----");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IYouJI iYouJI = retrofit.create(IYouJI.class);
        Call<YouJiBean> call = iYouJI.getYOUJiBean(page);
        call.enqueue(new Callback<YouJiBean>() {
            @Override
            public void onResponse(Call<YouJiBean> call, Response<YouJiBean> response) {
                YouJiBean body = response.body();
                List<YouJiBean.DataBean> dataBeen = body.getData();
                data.addAll(dataBeen);
                // adapter.notifyDataSetChanged();
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
                Log.d("print", " 上拉加载");


            }

            @Override
            public void onFailure(Call<YouJiBean> call, Throwable t) {

            }
        });

    }

    private void initdata(int i) {
        Log.d("print", " init ----");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IYouJI iYouJI = retrofit.create(IYouJI.class);
        Call<YouJiBean> call = iYouJI.getYOUJiBean(page);
        call.enqueue(new Callback<YouJiBean>() {
            @Override
            public void onResponse(Call<YouJiBean> call, Response<YouJiBean> response) {
                YouJiBean body = response.body();
                List<YouJiBean.DataBean> dataBeen = body.getData();
                data.clear();
                data.addAll(dataBeen);


               // initAdapter();
                newAdapter();

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<YouJiBean> call, Throwable t) {

            }
        });
    }

    private void newAdapter() {
        adapter = new YouJiAdapter(data, getActivity());
    }


    /*private void initAdapter() {
        adapter = new YouJiAdapter(data, getActivity());

    }*/

   /* private void init(int page) {
        Log.d("print", " init ----");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IYouJI iYouJI = retrofit.create(IYouJI.class);
        Call<YouJiBean> call = iYouJI.getYOUJiBean(page);
        call.enqueue(new Callback<YouJiBean>() {
            @Override
            public void onResponse(Call<YouJiBean> call, Response<YouJiBean> response) {
                YouJiBean body = response.body();
                List<YouJiBean.DataBean> dataBeen = body.getData();
                data.clear();
                data.addAll(dataBeen);


                initAdapter();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<YouJiBean> call, Throwable t) {

            }
        });
    }

    private void addMore(int page) {
        Log.d("print", " addMore ----");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IYouJI iYouJI = retrofit.create(IYouJI.class);
        Call<YouJiBean> call = iYouJI.getYOUJiBean(page);
        call.enqueue(new Callback<YouJiBean>() {
            @Override
            public void onResponse(Call<YouJiBean> call, Response<YouJiBean> response) {
                YouJiBean body = response.body();
                List<YouJiBean.DataBean> dataBeen = body.getData();
                data.addAll(dataBeen);
               // adapter.notifyDataSetChanged();
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
                Log.d("print", " 上拉加载");


            }

            @Override
            public void onFailure(Call<YouJiBean> call, Throwable t) {

            }
        });
    }*/





//1
    private void initAdapter() {
        adapter = new YouJiAdapter(data, getActivity());

    }

    private void init(int page) {
        Log.d("print", " init ----");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IYouJI iYouJI = retrofit.create(IYouJI.class);
        Call<YouJiBean> call = iYouJI.getYOUJiBean(page);
        call.enqueue(new Callback<YouJiBean>() {
            @Override
            public void onResponse(Call<YouJiBean> call, Response<YouJiBean> response) {
                YouJiBean body = response.body();
                List<YouJiBean.DataBean> dataBeen = body.getData();
                data.clear();
                data.addAll(dataBeen);


                initAdapter();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<YouJiBean> call, Throwable t) {

            }
        });

    }

    private void addMore(int page) {
        Log.d("print", " addMore ----");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IYouJI iYouJI = retrofit.create(IYouJI.class);
        Call<YouJiBean> call = iYouJI.getYOUJiBean(page);
        call.enqueue(new Callback<YouJiBean>() {
            @Override
            public void onResponse(Call<YouJiBean> call, Response<YouJiBean> response) {
                YouJiBean body = response.body();
                List<YouJiBean.DataBean> dataBeen = body.getData();
                data.addAll(dataBeen);
                // adapter.notifyDataSetChanged();
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
                Log.d("print", " 上拉加载");


            }

            @Override
            public void onFailure(Call<YouJiBean> call, Throwable t) {

            }
        });
    }



}
