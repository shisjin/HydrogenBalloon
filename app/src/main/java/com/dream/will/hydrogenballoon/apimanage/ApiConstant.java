package com.dream.will.hydrogenballoon.apimanage;


public class ApiConstant {
    public static final int APPHOST_TYPE = 1;
    public static final int OTHER_HOST_TYPE = 2;
    /*氢气球旅游Host*/
    public static final String HOST = "http://q.chanyouji.com/";
    /*携程Host*/
    public static final String OTHER_HOST = "http://m.ctrip.com/webapp/";
    /*-------------------------攻略页-----------------------------------------*/
    /*广告轮播*/   //http://q.chanyouji.com/api/v1/adverts.json?market=huawei&first_launch=false
    public static final String BANNER = "api/v1/adverts.json?market=huawei&first_launch=false";
    /*点击广告轮播*/
    public static final String BANNERDETAIL="api/v1/albums/{id}.json ";
    /*热门目的地*/
    public static final String HOT_DESTINATION = "api/v2/destinations.json";
    /*--------点击-----------------------*/
    //http://q.chanyouji.com/api/v2/destinations/nearby.json?lat=22.534911&lng=113.945074
    /*附近目的地列表 lat，lng 定位的经纬度*/
    public static final String NEARBY_DESTINATION = "api/v2/destinations/nearby.json";
    /*地区攻略列表 参数为不同地区，分别为下面3个常量值*/
    public static final String DESTINATIONS_LIST = "api/v2/destinations/list.json";
    /*20省*/
    public static final String CHINA = "china";
    /*12国*/
    public static final String ASIA = "asia";
    /*8国*/
    public static final String EUROPE = "europe";

    /*附近旅行灵感*/
    //api/v2/destinations/nearby_inspiration_activities.json?lat=22.534917&lng=113.945062
    public static final String NEAR_LINGFAN = " api/v2/destinations/nearby_inspiration_activities.json";
    /*-----------游记页------------------------------------------------------------*/
    /*游记列表 page= 页数*/
    public static final String TIMELINES = "api/v1/timelines.json";


    /*-------------- 目的地详情-----------------------------------------------------------------*/
//    /*目的地查询 参数： destination */
//    public static final String SERACH = "api/v2/search.json?search_type=hint&q={destination}";
    /*目的地查询接口 参数：name*/
    public static final String SEARCH_BY_NAME = "api/v2/search.json";
    /*--------以下需要点击-------*/
    /* 攻略 参数 ID ,来源路径：data->goods->wiki_destination->id*/
    public static final String WIKI = "api/wiki/destinations/{id}.json";
    /*目的地接口 参数id*/
    public static final String SEARCH_BY_ID = "api/v3/destinations/{id}.json";

    /*概览与地图 id */
    public static final String GROUPINGS = "api/v2/destinations/{id}/groupings.json";

    /*经典路线  id*/
    public static final String PLANS = "api/v2/plans/{id}.json";

    /*旅行榜单列表*/
    public static final String COLLECTION_LIST = "api/v2/destinations/{id}/collection_destinations.json";

    /*单类榜单的数据列表 destination_id = */
    public static final String ONE_COLLECTIONS = "api/v1/activity_collections.json";
    /*单类榜单Item列表*/
    public static final String ONE_COLLECTIONS_ITEM = "api/v2/activity_collections/{id}.json";
    /*单类榜单Item列表中Item的攻略与游记*/
    public static final String ONE_COLLECTIONS_ITEM_INSPIRATION = "api/v1/inspiration_activities/{id}.json";


    /*----------相关氢游记中N篇游记BuTTon
    跳转到该目的地的游记页
    点击后需要请求2个接口
    ACTIVITY_CATEGORIES，
    USER_ACTIVITIES
    -------------*/
    /*
    *  api/v1/activity_categories/highlights.json?district_id=1
    *  Field :  district_id
    *  返回的是游记顶部栏数据
    *
    * */
    public static final String ACTIVITY_CATEGORIES = "api/v1/activity_categories/highlights.json";
    /**
     * api/v1/user_activities.json?district_id=1&page=1
     * Field: district_id  ,page
     * 返回的是游记列表数据
     */
    public static final String USER_ACTIVITIES = "api/v1/user_activities.json";


    public static String getHostByType(int type) {
        if (type == APPHOST_TYPE) {
            return HOST;
        } else {
            return OTHER_HOST;
        }
    }

    /*个人主页*/
    //头部信息: api/v1/users/10586/profiles.json
    public  static final String PERSONAL_HOMEPAGE_HEADINFO= "api/v1/users/{id}/profiles.json";
    //详细PersonalHomePageDeatilBean
    public static final String PERSONAL_HOMEPAGE_DEATIL= "api/v1/users/{id}/user_activities.json?";
    //详细PersonalHomePageGroupedBean
    public static final String PERSONAL_HOMEPAGE_GROUPED= "api/v1/users/{id}/user_activities.json?grouped=1";


}
