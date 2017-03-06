package com.dream.will.hydrogenballoon.inter;

import com.dream.will.hydrogenballoon.apimanage.ApiConstant;
import com.dream.will.hydrogenballoon.bean.PersonalHomePageDeatilBean;
import com.dream.will.hydrogenballoon.bean.PersonalHomePageGroupedBean;
import com.dream.will.hydrogenballoon.bean.PersonalHomePageHeadBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 */

public interface IPersonalHomepage {

@GET(ApiConstant.PERSONAL_HOMEPAGE_HEADINFO)
Call<PersonalHomePageHeadBean> getPerHomeHeadBean(@Path("id") int id);
@GET(ApiConstant.PERSONAL_HOMEPAGE_DEATIL)
Call<PersonalHomePageDeatilBean> getPerHomeDeatBean (@Path("id") int id,@Query("page") int page);

@GET(ApiConstant.PERSONAL_HOMEPAGE_GROUPED)
Call<PersonalHomePageGroupedBean> getPerHomeGroupBean(@Path("id") int id);




}
