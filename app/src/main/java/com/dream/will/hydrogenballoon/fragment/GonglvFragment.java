package com.dream.will.hydrogenballoon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.will.hydrogenballoon.MyApp;
import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.adapter.GonglueRecyclAdapter;
import com.dream.will.hydrogenballoon.adapter.GonglvListViewAdapter;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.DbUserBean;
import com.dream.will.hydrogenballoon.bean.GonglvListBean;
import com.dream.will.hydrogenballoon.customview.BannerView;
import com.dream.will.hydrogenballoon.customview.NearDestinationView;
import com.dream.will.hydrogenballoon.customview.NearDestinationViewT;
import com.dream.will.hydrogenballoon.customview.WantToView;
import com.dream.will.hydrogenballoon.db.DbDao;
import com.dream.will.hydrogenballoon.inter.IGongLv;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 */

public class GonglvFragment extends Fragment {

    public static WantToView wantToView;
    List<GonglvListBean.DataBean> data;
    List<String> data1;
    private RecyclerView recyclerView;
    private GonglvListViewAdapter adapter2;
    private GonglueRecyclAdapter recyclAdapter;
    private List<DbUserBean> loctionList;

    // "lat": 25.0454006195,
    //"  lng": 102.7099990845,
    // 深圳 lng 114.06667,lat 22.61667
//     double lng= 114.06667;
//     double lat= 22.61667;/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        data1 = new ArrayList<>();
        loctionList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            data1.add("hhhh");
        }
        loctionList.addAll(DbDao.getLoction(MyApp.getInstance().dBhelper));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gonglv, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.listView1);
        //添加banner
        BannerView bannerView = new BannerView(getActivity());
        //添加最近想去
        wantToView = new WantToView(getActivity());
        wantToView.setText(loctionList);
        //添加附近目的地
        NearDestinationView nearDestinationView = new NearDestinationView(getContext());
        NearDestinationViewT nearDestinationViewT = new NearDestinationViewT(getContext());
        View view1 = nearDestinationViewT.initData();
        nearDestinationViewT.getHostListData(MyApp.getInstance().lat, MyApp.getInstance().lng);
//        nearDestinationView.getHostListData(MyApp.getInstance().lat,MyApp.getInstance().lng);
        //listView数据
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclAdapter = new GonglueRecyclAdapter(getActivity(), data);
        recyclAdapter.setHeaderViewOne(bannerView);
        recyclAdapter.setHeaderViewTwo(wantToView);
        recyclAdapter.setHeaderViewThree(view1);
        recyclerView.setAdapter(recyclAdapter);
        getHostListData();
    }

    /**
     * 联网获取 listview  Hot数据  更新斯佩卿
     */
    public void getHostListData() {
        //retrofit 联网加载数据
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获取接口实例
        IGongLv iStore = re.create(IGongLv.class);
        Call<GonglvListBean> call = iStore.getListData();
        //发请求
        call.enqueue(new Callback<GonglvListBean>() {
            @Override
            public void onResponse(Call<GonglvListBean> call, Response<GonglvListBean> response) {
                GonglvListBean body = response.body();
                data.addAll(body.getData());
                Log.i("TAG", "onResponse: -gonglv listview--------" + data.size());
                recyclAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GonglvListBean> call, Throwable t) {
                Log.i("TAG", "onFailure: --gonglv listview-----error--");
            }
        });

    }

}
