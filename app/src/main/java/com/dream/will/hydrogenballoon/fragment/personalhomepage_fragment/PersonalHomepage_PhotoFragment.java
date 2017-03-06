package com.dream.will.hydrogenballoon.fragment.personalhomepage_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.adapter.PersonalHomepage_PhotoAdapter;
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

public class PersonalHomepage_PhotoFragment   extends Fragment {

   private RecyclerView photo_recyclerview;
    private  int userID ;
    private PersonalHomepage_PhotoAdapter adapter;

    private int page=1;
    private List<PersonalHomePageDeatilBean.DataBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        userID = bundle.getInt("userID");
        data = new ArrayList<>();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personalhomepage_photo,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photo_recyclerview= (RecyclerView) view.findViewById(R.id.photo_recyclerview);
        InData(userID,page);
      // LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        photo_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),3));
        photo_recyclerview.addItemDecoration(new PH_PhotoDivider(getActivity()));
        adapter = new PersonalHomepage_PhotoAdapter(data,getActivity());
        photo_recyclerview.setAdapter(adapter);







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
