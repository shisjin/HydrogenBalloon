package com.dream.will.hydrogenballoon.apimanage;

import com.dream.will.hydrogenballoon.bean.Destinations;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IDestinationApi {
    /**
     * "api/v2/search.json?q={name}"
     *
     * @return
     */
    @GET(ApiConstant.SEARCH_BY_NAME)
    Call<Destinations> requestDestionation(@Query("q") String name);

    /**
     * api/v3/destinations/{id}.json
     *
     * @param id
     * @return
     */
    @Headers("Cache-Control: public ,max-age=3600")
    @GET(ApiConstant.SEARCH_BY_ID)
    Call<Destinations> requestDestionationById(@Path("id") int id);

}
