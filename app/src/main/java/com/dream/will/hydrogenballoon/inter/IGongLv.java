package com.dream.will.hydrogenballoon.inter;

import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.GongLvBannerBean;
import com.dream.will.hydrogenballoon.bean.GonglvListBean;
import com.dream.will.hydrogenballoon.bean.NearDestinationLinggan;
import com.dream.will.hydrogenballoon.bean.NearDestinationRegionListBean;
import com.dream.will.hydrogenballoon.bean.NearDestinationViewBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 */

public interface IGongLv {
    /**攻略   banner
     * @return
     */
    @GET(ApiConstant.BANNER)
    Call<GongLvBannerBean>  getBanner();

    /** 攻略  热门目的地
     * @return
     */
    @GET(ApiConstant.HOT_DESTINATION)
    Call<GonglvListBean>  getListData();

    /** 攻略  附近目的地
     * @return
     * "api/v2/destinations/nearby.json?lat={lat}&lng={lng}&recommend"
     */
    @GET(ApiConstant.NEARBY_DESTINATION)
    Call<NearDestinationViewBean>  getNearDestination(@Query("lat") double lat, @Query("lng") double lng, @Query("recommend") String recommend);

    /** 攻略  更多附近目的地
     * @return
     * //http://q.chanyouji.com/api/v2/destinations/nearby.json?lat=22.534911&lng=113.945074
     */
    @GET(ApiConstant.NEARBY_DESTINATION)
    Call<NearDestinationRegionListBean>  getNearDestinationMore(@Query("lat") double lat, @Query("lng") double lng);

    /** 攻略  更多附近目的地  灵感界面
     * @return
     * //api/v2/destinations/nearby_inspiration_activities.json?lat=22.534917&lng=113.945062
     */
    @GET(ApiConstant.NEAR_LINGFAN)
    Call<NearDestinationLinggan>  getNearDestinationMoreLinggan(@Query("lat") double lat, @Query("lng") double lng);


    /** 攻略   更多目的地 详情列表
     * @return
     * "api/v2/destinations/list.json?area={destination}"
     */
    @GET(ApiConstant.DESTINATIONS_LIST)
    Call<NearDestinationRegionListBean>  getNearDestinationList(@Query("area") String area);
}
