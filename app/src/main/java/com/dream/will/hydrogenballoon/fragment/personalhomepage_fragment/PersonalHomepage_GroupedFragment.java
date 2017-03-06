package com.dream.will.hydrogenballoon.fragment.personalhomepage_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.adapter.PersonalHomepage_GroupedAdapter;
import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.PersonalHomePageGroupedBean;
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

public class PersonalHomepage_GroupedFragment  extends Fragment {


    private RecyclerView recyclerview;
    private List<PersonalHomePageGroupedBean.DataBean> data;
    private  PersonalHomepage_GroupedAdapter adapter;
    private int userID;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data= new ArrayList<>();
        Bundle bundle = getArguments();
        userID = bundle.getInt("userID");

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TAG", "PersonalHomepage_GroupedFragment--onCreateView~~~~");
        return inflater.inflate(R.layout.fragment_personalhomepage_grouped, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview = (RecyclerView) view.findViewById(R.id.grouped_recyclerview);
        initData(userID);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
        recyclerview.addItemDecoration(new PH_PhotoDivider(getActivity()));
        adapter= new PersonalHomepage_GroupedAdapter(data,getActivity());
        recyclerview.setAdapter(adapter);






    }

    private void initData(int userID) {
        Retrofit retorfit = new Retrofit.Builder().baseUrl(ApiConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IPersonalHomepage iPersonalHomepage = retorfit.create(IPersonalHomepage.class);
        Call<PersonalHomePageGroupedBean> call = iPersonalHomepage.getPerHomeGroupBean(userID);
        call.enqueue(new Callback<PersonalHomePageGroupedBean>() {
            @Override
            public void onResponse(Call<PersonalHomePageGroupedBean> call, Response<PersonalHomePageGroupedBean> response) {
                PersonalHomePageGroupedBean body = response.body();
                List<PersonalHomePageGroupedBean.DataBean> data1 = body.getData();
                data.clear();
                data.addAll(data1);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<PersonalHomePageGroupedBean> call, Throwable t) {

            }
        });

    }
}