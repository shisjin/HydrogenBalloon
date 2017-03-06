package com.dream.will.hydrogenballoon.inter;

import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.YouJiBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 */

public interface IYouJI {
    /**
     * 游记
     * @param page
     * @return
     */
    @GET(ApiConstant.TIMELINES)
    Call<YouJiBean> getYOUJiBean(@Query("page") int page);
}
