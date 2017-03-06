package com.dream.will.hydrogenballoon.bean;


import com.google.gson.Gson;

import java.util.List;


public class Destinations {
    private String message;
    private int status;
    private DataBean data;

    public static Destinations objectFromData(String str) {

        return new Gson().fromJson(str, Destinations.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private DestinationBean destination;
        private ParentDestinationBean parent_destination;
        private WikiDestinationBean wiki_destination;
        private Object wiki_page;
        private String ctrip_airport_url;
        private List<SectionsBean> sections;
        private List<GoodsBean> goods;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public DestinationBean getDestination() {
            return destination;
        }

        public void setDestination(DestinationBean destination) {
            this.destination = destination;
        }

        public ParentDestinationBean getParent_destination() {
            return parent_destination;
        }

        public void setParent_destination(ParentDestinationBean parent_destination) {
            this.parent_destination = parent_destination;
        }

        public WikiDestinationBean getWiki_destination() {
            return wiki_destination;
        }

        public void setWiki_destination(WikiDestinationBean wiki_destination) {
            this.wiki_destination = wiki_destination;
        }

        public Object getWiki_page() {
            return wiki_page;
        }

        public void setWiki_page(Object wiki_page) {
            this.wiki_page = wiki_page;
        }

        public String getCtrip_airport_url() {
            return ctrip_airport_url;
        }

        public void setCtrip_airport_url(String ctrip_airport_url) {
            this.ctrip_airport_url = ctrip_airport_url;
        }

        public List<SectionsBean> getSections() {
            return sections;
        }

        public void setSections(List<SectionsBean> sections) {
            this.sections = sections;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class DestinationBean {
            private int id;
            private double lat;
            private double lng;
            private int district_id;
            private int parent_id;
            private String name;
            private String name_en;
            private String name_pinyin;
            private int score;
            private int level;
            private String path;
            private boolean published;
            private boolean is_in_china;
            private int inspiration_activities_count;
            private int activity_collections_count;
            private int wishes_count;
            private Object wiki_destination_id;
            private String photo_url;
            private String title;
            private String description;
            private String tip;
            private String time_cost;
            private Object wiki_page_id;
            private boolean has_airport;
            private String visit_tip;
            private boolean is_top_destination;
            private boolean is_in_grouping;
            private String alias_name;
            private String travel_tip;
            private PhotoBean photo;

            public static DestinationBean objectFromData(String str) {

                return new Gson().fromJson(str, DestinationBean.class);
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public int getDistrict_id() {
                return district_id;
            }

            public void setDistrict_id(int district_id) {
                this.district_id = district_id;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName_pinyin() {
                return name_pinyin;
            }

            public void setName_pinyin(String name_pinyin) {
                this.name_pinyin = name_pinyin;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public boolean isPublished() {
                return published;
            }

            public void setPublished(boolean published) {
                this.published = published;
            }

            public boolean isIs_in_china() {
                return is_in_china;
            }

            public void setIs_in_china(boolean is_in_china) {
                this.is_in_china = is_in_china;
            }

            public int getInspiration_activities_count() {
                return inspiration_activities_count;
            }

            public void setInspiration_activities_count(int inspiration_activities_count) {
                this.inspiration_activities_count = inspiration_activities_count;
            }

            public int getActivity_collections_count() {
                return activity_collections_count;
            }

            public void setActivity_collections_count(int activity_collections_count) {
                this.activity_collections_count = activity_collections_count;
            }

            public int getWishes_count() {
                return wishes_count;
            }

            public void setWishes_count(int wishes_count) {
                this.wishes_count = wishes_count;
            }

            public Object getWiki_destination_id() {
                return wiki_destination_id;
            }

            public void setWiki_destination_id(Object wiki_destination_id) {
                this.wiki_destination_id = wiki_destination_id;
            }

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }

            public String getTime_cost() {
                return time_cost;
            }

            public void setTime_cost(String time_cost) {
                this.time_cost = time_cost;
            }

            public Object getWiki_page_id() {
                return wiki_page_id;
            }

            public void setWiki_page_id(Object wiki_page_id) {
                this.wiki_page_id = wiki_page_id;
            }

            public boolean isHas_airport() {
                return has_airport;
            }

            public void setHas_airport(boolean has_airport) {
                this.has_airport = has_airport;
            }

            public String getVisit_tip() {
                return visit_tip;
            }

            public void setVisit_tip(String visit_tip) {
                this.visit_tip = visit_tip;
            }

            public boolean isIs_top_destination() {
                return is_top_destination;
            }

            public void setIs_top_destination(boolean is_top_destination) {
                this.is_top_destination = is_top_destination;
            }

            public boolean isIs_in_grouping() {
                return is_in_grouping;
            }

            public void setIs_in_grouping(boolean is_in_grouping) {
                this.is_in_grouping = is_in_grouping;
            }

            public String getAlias_name() {
                return alias_name;
            }

            public void setAlias_name(String alias_name) {
                this.alias_name = alias_name;
            }

            public String getTravel_tip() {
                return travel_tip;
            }

            public void setTravel_tip(String travel_tip) {
                this.travel_tip = travel_tip;
            }

            public PhotoBean getPhoto() {
                return photo;
            }

            public void setPhoto(PhotoBean photo) {
                this.photo = photo;
            }

            public static class PhotoBean {
                private int id;
                private int width;
                private int height;
                private String url;
                private int file_size;
                private String photo_url;

                public static PhotoBean objectFromData(String str) {

                    return new Gson().fromJson(str, PhotoBean.class);
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getFile_size() {
                    return file_size;
                }

                public void setFile_size(int file_size) {
                    this.file_size = file_size;
                }

                public String getPhoto_url() {
                    return photo_url;
                }

                public void setPhoto_url(String photo_url) {
                    this.photo_url = photo_url;
                }
            }
        }

        public static class ParentDestinationBean {
            private int id;
            private double lat;
            private double lng;
            private int district_id;
            private int parent_id;
            private String name;
            private String name_en;
            private String name_pinyin;
            private int score;
            private int level;
            private String path;
            private boolean published;
            private boolean is_in_china;
            private int inspiration_activities_count;
            private int activity_collections_count;
            private int wishes_count;
            private Object wiki_destination_id;
            private String photo_url;
            private String title;
            private String description;
            private String tip;
            private String time_cost;
            private Object wiki_page_id;
            private boolean has_airport;
            private String visit_tip;
            private boolean is_top_destination;
            private boolean is_in_grouping;
            private String alias_name;
            private String travel_tip;
            private PhotoBean photo;

            public static ParentDestinationBean objectFromData(String str) {

                return new Gson().fromJson(str, ParentDestinationBean.class);
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public int getDistrict_id() {
                return district_id;
            }

            public void setDistrict_id(int district_id) {
                this.district_id = district_id;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName_pinyin() {
                return name_pinyin;
            }

            public void setName_pinyin(String name_pinyin) {
                this.name_pinyin = name_pinyin;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public boolean isPublished() {
                return published;
            }

            public void setPublished(boolean published) {
                this.published = published;
            }

            public boolean isIs_in_china() {
                return is_in_china;
            }

            public void setIs_in_china(boolean is_in_china) {
                this.is_in_china = is_in_china;
            }

            public int getInspiration_activities_count() {
                return inspiration_activities_count;
            }

            public void setInspiration_activities_count(int inspiration_activities_count) {
                this.inspiration_activities_count = inspiration_activities_count;
            }

            public int getActivity_collections_count() {
                return activity_collections_count;
            }

            public void setActivity_collections_count(int activity_collections_count) {
                this.activity_collections_count = activity_collections_count;
            }

            public int getWishes_count() {
                return wishes_count;
            }

            public void setWishes_count(int wishes_count) {
                this.wishes_count = wishes_count;
            }

            public Object getWiki_destination_id() {
                return wiki_destination_id;
            }

            public void setWiki_destination_id(Object wiki_destination_id) {
                this.wiki_destination_id = wiki_destination_id;
            }

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }

            public String getTime_cost() {
                return time_cost;
            }

            public void setTime_cost(String time_cost) {
                this.time_cost = time_cost;
            }

            public Object getWiki_page_id() {
                return wiki_page_id;
            }

            public void setWiki_page_id(Object wiki_page_id) {
                this.wiki_page_id = wiki_page_id;
            }

            public boolean isHas_airport() {
                return has_airport;
            }

            public void setHas_airport(boolean has_airport) {
                this.has_airport = has_airport;
            }

            public String getVisit_tip() {
                return visit_tip;
            }

            public void setVisit_tip(String visit_tip) {
                this.visit_tip = visit_tip;
            }

            public boolean isIs_top_destination() {
                return is_top_destination;
            }

            public void setIs_top_destination(boolean is_top_destination) {
                this.is_top_destination = is_top_destination;
            }

            public boolean isIs_in_grouping() {
                return is_in_grouping;
            }

            public void setIs_in_grouping(boolean is_in_grouping) {
                this.is_in_grouping = is_in_grouping;
            }

            public String getAlias_name() {
                return alias_name;
            }

            public void setAlias_name(String alias_name) {
                this.alias_name = alias_name;
            }

            public String getTravel_tip() {
                return travel_tip;
            }

            public void setTravel_tip(String travel_tip) {
                this.travel_tip = travel_tip;
            }

            public PhotoBean getPhoto() {
                return photo;
            }

            public void setPhoto(PhotoBean photo) {
                this.photo = photo;
            }

            public static class PhotoBean {
                private int id;
                private int width;
                private int height;
                private String url;
                private int file_size;
                private String photo_url;

                public static PhotoBean objectFromData(String str) {

                    return new Gson().fromJson(str, PhotoBean.class);
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getFile_size() {
                    return file_size;
                }

                public void setFile_size(int file_size) {
                    this.file_size = file_size;
                }

                public String getPhoto_url() {
                    return photo_url;
                }

                public void setPhoto_url(String photo_url) {
                    this.photo_url = photo_url;
                }
            }
        }

        public static class WikiDestinationBean {
            private int id;
            private int children_count;
            private String name_zh_cn;
            private String name_en;
            private String image_url;

            public static WikiDestinationBean objectFromData(String str) {

                return new Gson().fromJson(str, WikiDestinationBean.class);
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getChildren_count() {
                return children_count;
            }

            public void setChildren_count(int children_count) {
                this.children_count = children_count;
            }

            public String getName_zh_cn() {
                return name_zh_cn;
            }

            public void setName_zh_cn(String name_zh_cn) {
                this.name_zh_cn = name_zh_cn;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }

        public static class SectionsBean {
            private int count;
            private String type;
            private String title;
            private String button_text;
            private List<ModelsBean> models;
            public static SectionsBean objectFromData(String str) {

                return new Gson().fromJson(str, SectionsBean.class);
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getButton_text() {
                return button_text;
            }

            public void setButton_text(String button_text) {
                this.button_text = button_text;
            }

            public List<ModelsBean> getModels() {
                return models;
            }

            public void setModels(List<ModelsBean> models) {
                this.models = models;
            }

            public static class ModelsBean {
                private int id;
                private double lat;
                private double lng;
                private int parent_id;
                private String name;
                private int destination_id;
                private String name_en;
                private String name_pinyin;
                private int score;
                private int level;
                private String path;
                private boolean published;
                private boolean is_in_china;
                private int inspiration_activities_count;
                private int activity_collections_count;
                private int inspiration_activity_id;
                private Object inspiration_activity;
                private int wishes_count;
                private Object wiki_destination_id;
                private String made_at;
                private int likes_count;
                private int comments_count;
                private String topic;
                private int contents_count;
                private int district_id;
                private String created_at;
                private int favorites_count;
                private int parent_district_id;
                private int parent_district_count;
                private String photo_url;
                private String title;
                private String description;
                private String tip;
                private String time_cost;
                private Object wiki_page_id;
                private boolean has_airport;
                private String visit_tip;
                private boolean is_top_destination;
                private boolean is_in_grouping;
                private Object alias_name;
                private int days_count;
                private Object travel_tip;
                private String summary;
                private PhotoBean photo;
                private UserBean user;
                private PoiBean poi;
                private List<ContentsBean> contents;
                private List<CategoriesBean> categories;
                private List<DaysBean> days;

                public static ModelsBean objectFromData(String str) {

                    return new Gson().fromJson(str, ModelsBean.class);
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }

                public int getParent_id() {
                    return parent_id;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getDestination_id() {
                    return destination_id;
                }

                public void setDestination_id(int destination_id) {
                    this.destination_id = destination_id;
                }

                public String getName_en() {
                    return name_en;
                }

                public void setName_en(String name_en) {
                    this.name_en = name_en;
                }

                public String getName_pinyin() {
                    return name_pinyin;
                }

                public void setName_pinyin(String name_pinyin) {
                    this.name_pinyin = name_pinyin;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public boolean isPublished() {
                    return published;
                }

                public void setPublished(boolean published) {
                    this.published = published;
                }

                public boolean isIs_in_china() {
                    return is_in_china;
                }

                public void setIs_in_china(boolean is_in_china) {
                    this.is_in_china = is_in_china;
                }

                public int getInspiration_activities_count() {
                    return inspiration_activities_count;
                }

                public void setInspiration_activities_count(int inspiration_activities_count) {
                    this.inspiration_activities_count = inspiration_activities_count;
                }

                public int getActivity_collections_count() {
                    return activity_collections_count;
                }

                public void setActivity_collections_count(int activity_collections_count) {
                    this.activity_collections_count = activity_collections_count;
                }

                public int getInspiration_activity_id() {
                    return inspiration_activity_id;
                }

                public void setInspiration_activity_id(int inspiration_activity_id) {
                    this.inspiration_activity_id = inspiration_activity_id;
                }

                public Object getInspiration_activity() {
                    return inspiration_activity;
                }

                public void setInspiration_activity(Object inspiration_activity) {
                    this.inspiration_activity = inspiration_activity;
                }

                public int getWishes_count() {
                    return wishes_count;
                }

                public void setWishes_count(int wishes_count) {
                    this.wishes_count = wishes_count;
                }

                public Object getWiki_destination_id() {
                    return wiki_destination_id;
                }

                public void setWiki_destination_id(Object wiki_destination_id) {
                    this.wiki_destination_id = wiki_destination_id;
                }

                public String getMade_at() {
                    return made_at;
                }

                public void setMade_at(String made_at) {
                    this.made_at = made_at;
                }

                public int getLikes_count() {
                    return likes_count;
                }

                public void setLikes_count(int likes_count) {
                    this.likes_count = likes_count;
                }

                public int getComments_count() {
                    return comments_count;
                }

                public void setComments_count(int comments_count) {
                    this.comments_count = comments_count;
                }

                public String getTopic() {
                    return topic;
                }

                public void setTopic(String topic) {
                    this.topic = topic;
                }

                public int getContents_count() {
                    return contents_count;
                }

                public void setContents_count(int contents_count) {
                    this.contents_count = contents_count;
                }

                public int getDistrict_id() {
                    return district_id;
                }

                public void setDistrict_id(int district_id) {
                    this.district_id = district_id;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public int getFavorites_count() {
                    return favorites_count;
                }

                public void setFavorites_count(int favorites_count) {
                    this.favorites_count = favorites_count;
                }

                public int getParent_district_id() {
                    return parent_district_id;
                }

                public void setParent_district_id(int parent_district_id) {
                    this.parent_district_id = parent_district_id;
                }

                public int getParent_district_count() {
                    return parent_district_count;
                }

                public void setParent_district_count(int parent_district_count) {
                    this.parent_district_count = parent_district_count;
                }

                public String getPhoto_url() {
                    return photo_url;
                }

                public void setPhoto_url(String photo_url) {
                    this.photo_url = photo_url;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getTip() {
                    return tip;
                }

                public void setTip(String tip) {
                    this.tip = tip;
                }

                public String getTime_cost() {
                    return time_cost;
                }

                public void setTime_cost(String time_cost) {
                    this.time_cost = time_cost;
                }

                public Object getWiki_page_id() {
                    return wiki_page_id;
                }

                public void setWiki_page_id(Object wiki_page_id) {
                    this.wiki_page_id = wiki_page_id;
                }

                public boolean isHas_airport() {
                    return has_airport;
                }

                public void setHas_airport(boolean has_airport) {
                    this.has_airport = has_airport;
                }

                public String getVisit_tip() {
                    return visit_tip;
                }

                public void setVisit_tip(String visit_tip) {
                    this.visit_tip = visit_tip;
                }

                public boolean isIs_top_destination() {
                    return is_top_destination;
                }

                public void setIs_top_destination(boolean is_top_destination) {
                    this.is_top_destination = is_top_destination;
                }

                public boolean isIs_in_grouping() {
                    return is_in_grouping;
                }

                public void setIs_in_grouping(boolean is_in_grouping) {
                    this.is_in_grouping = is_in_grouping;
                }

                public Object getAlias_name() {
                    return alias_name;
                }

                public void setAlias_name(Object alias_name) {
                    this.alias_name = alias_name;
                }

                public int getDays_count() {
                    return days_count;
                }

                public void setDays_count(int days_count) {
                    this.days_count = days_count;
                }

                public Object getTravel_tip() {
                    return travel_tip;
                }

                public void setTravel_tip(Object travel_tip) {
                    this.travel_tip = travel_tip;
                }

                public String getSummary() {
                    return summary;
                }

                public void setSummary(String summary) {
                    this.summary = summary;
                }

                public PhotoBean getPhoto() {
                    return photo;
                }

                public void setPhoto(PhotoBean photo) {
                    this.photo = photo;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public PoiBean getPoi() {
                    return poi;
                }

                public void setPoi(PoiBean poi) {
                    this.poi = poi;
                }

                public List<ContentsBean> getContents() {
                    return contents;
                }

                public void setContents(List<ContentsBean> contents) {
                    this.contents = contents;
                }

                public List<CategoriesBean> getCategories() {
                    return categories;
                }

                public void setCategories(List<CategoriesBean> categories) {
                    this.categories = categories;
                }

                public List<DaysBean> getDays() {
                    return days;
                }

                public void setDays(List<DaysBean> days) {
                    this.days = days;
                }

                public static class PhotoBean {
                    private int id;
                    private int width;
                    private int height;
                    private String url;
                    private int file_size;
                    private String photo_url;

                    public static PhotoBean objectFromData(String str) {

                        return new Gson().fromJson(str, PhotoBean.class);
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getFile_size() {
                        return file_size;
                    }

                    public void setFile_size(int file_size) {
                        this.file_size = file_size;
                    }

                    public String getPhoto_url() {
                        return photo_url;
                    }

                    public void setPhoto_url(String photo_url) {
                        this.photo_url = photo_url;
                    }
                }

                public static class UserBean {
                    private int id;
                    private String name;
                    private int gender;
                    private int level;
                    private String photo_url;

                    public static UserBean objectFromData(String str) {

                        return new Gson().fromJson(str, UserBean.class);
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getGender() {
                        return gender;
                    }

                    public void setGender(int gender) {
                        this.gender = gender;
                    }

                    public int getLevel() {
                        return level;
                    }

                    public void setLevel(int level) {
                        this.level = level;
                    }

                    public String getPhoto_url() {
                        return photo_url;
                    }

                    public void setPhoto_url(String photo_url) {
                        this.photo_url = photo_url;
                    }
                }

                public static class PoiBean {
                    private int id;
                    private String h5_url;
                    private String name;
                    private String name_en;
                    private String name_pinyin;
                    private int business_id;
                    private String poi_type;
                    private int district_id;
                    private double lat;
                    private double lng;
                    private String address;
                    private Object location_name;
                    private double blat;
                    private double blng;
                    private double youji_lat;
                    private double youji_lng;
                    private int youji_poi_id;
                    private String youji_poi_name;
                    private boolean is_in_china;
                    private Object local_name;
                    private Object local_address_name;

                    public static PoiBean objectFromData(String str) {

                        return new Gson().fromJson(str, PoiBean.class);
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getH5_url() {
                        return h5_url;
                    }

                    public void setH5_url(String h5_url) {
                        this.h5_url = h5_url;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getName_en() {
                        return name_en;
                    }

                    public void setName_en(String name_en) {
                        this.name_en = name_en;
                    }

                    public String getName_pinyin() {
                        return name_pinyin;
                    }

                    public void setName_pinyin(String name_pinyin) {
                        this.name_pinyin = name_pinyin;
                    }

                    public int getBusiness_id() {
                        return business_id;
                    }

                    public void setBusiness_id(int business_id) {
                        this.business_id = business_id;
                    }

                    public String getPoi_type() {
                        return poi_type;
                    }

                    public void setPoi_type(String poi_type) {
                        this.poi_type = poi_type;
                    }

                    public int getDistrict_id() {
                        return district_id;
                    }

                    public void setDistrict_id(int district_id) {
                        this.district_id = district_id;
                    }

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public Object getLocation_name() {
                        return location_name;
                    }

                    public void setLocation_name(Object location_name) {
                        this.location_name = location_name;
                    }

                    public double getBlat() {
                        return blat;
                    }

                    public void setBlat(double blat) {
                        this.blat = blat;
                    }

                    public double getBlng() {
                        return blng;
                    }

                    public void setBlng(double blng) {
                        this.blng = blng;
                    }

                    public double getYouji_lat() {
                        return youji_lat;
                    }

                    public void setYouji_lat(double youji_lat) {
                        this.youji_lat = youji_lat;
                    }

                    public double getYouji_lng() {
                        return youji_lng;
                    }

                    public void setYouji_lng(double youji_lng) {
                        this.youji_lng = youji_lng;
                    }

                    public int getYouji_poi_id() {
                        return youji_poi_id;
                    }

                    public void setYouji_poi_id(int youji_poi_id) {
                        this.youji_poi_id = youji_poi_id;
                    }

                    public String getYouji_poi_name() {
                        return youji_poi_name;
                    }

                    public void setYouji_poi_name(String youji_poi_name) {
                        this.youji_poi_name = youji_poi_name;
                    }

                    public boolean isIs_in_china() {
                        return is_in_china;
                    }

                    public void setIs_in_china(boolean is_in_china) {
                        this.is_in_china = is_in_china;
                    }

                    public Object getLocal_name() {
                        return local_name;
                    }

                    public void setLocal_name(Object local_name) {
                        this.local_name = local_name;
                    }

                    public Object getLocal_address_name() {
                        return local_address_name;
                    }

                    public void setLocal_address_name(Object local_address_name) {
                        this.local_address_name = local_address_name;
                    }
                }

                public static class ContentsBean {
                    private int id;
                    private Object caption;
                    private String photo_url;
                    private int position;
                    private int width;
                    private int height;

                    public static ContentsBean objectFromData(String str) {

                        return new Gson().fromJson(str, ContentsBean.class);
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public Object getCaption() {
                        return caption;
                    }

                    public void setCaption(Object caption) {
                        this.caption = caption;
                    }

                    public String getPhoto_url() {
                        return photo_url;
                    }

                    public void setPhoto_url(String photo_url) {
                        this.photo_url = photo_url;
                    }

                    public int getPosition() {
                        return position;
                    }

                    public void setPosition(int position) {
                        this.position = position;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }
                }

                public static class CategoriesBean {
                    private int id;
                    private String name;
                    private String category_type;

                    public static CategoriesBean objectFromData(String str) {

                        return new Gson().fromJson(str, CategoriesBean.class);
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getCategory_type() {
                        return category_type;
                    }

                    public void setCategory_type(String category_type) {
                        this.category_type = category_type;
                    }
                }

                public static class DaysBean {
                    private int id;
                    private int plan_id;
                    private int position;
                    private String description;
                    private List<PointsBean> points;

                    public static DaysBean objectFromData(String str) {

                        return new Gson().fromJson(str, DaysBean.class);
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getPlan_id() {
                        return plan_id;
                    }

                    public void setPlan_id(int plan_id) {
                        this.plan_id = plan_id;
                    }

                    public int getPosition() {
                        return position;
                    }

                    public void setPosition(int position) {
                        this.position = position;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public List<PointsBean> getPoints() {
                        return points;
                    }

                    public void setPoints(List<PointsBean> points) {
                        this.points = points;
                    }


                    public static class PointsBean {
                        private int id;
                        private int position;
                        private int time_cost;
                        private boolean is_custom;
                        private PoiBean poi;

                        public static PointsBean objectFromData(String str) {

                            return new Gson().fromJson(str, PointsBean.class);
                        }

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public int getPosition() {
                            return position;
                        }

                        public void setPosition(int position) {
                            this.position = position;
                        }

                        public int getTime_cost() {
                            return time_cost;
                        }

                        public void setTime_cost(int time_cost) {
                            this.time_cost = time_cost;
                        }

                        public boolean isIs_custom() {
                            return is_custom;
                        }

                        public void setIs_custom(boolean is_custom) {
                            this.is_custom = is_custom;
                        }

                        public PoiBean getPoi() {
                            return poi;
                        }

                        public void setPoi(PoiBean poi) {
                            this.poi = poi;
                        }

                        public static class PoiBean {
                            private int id;
                            private String name;
                            private String name_en;
                            private String name_pinyin;
                            private int business_id;
                            private String poi_type;
                            private int district_id;
                            private double lat;
                            private double lng;
                            private String location_name;
                            private double blat;
                            private double blng;
                            private double youji_lat;
                            private double youji_lng;
                            private int youji_poi_id;
                            private boolean is_in_china;
                            private String youji_poi_name;

                            public static PoiBean objectFromData(String str) {

                                return new Gson().fromJson(str, PoiBean.class);
                            }

                            public int getId() {
                                return id;
                            }

                            public void setId(int id) {
                                this.id = id;
                            }

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getName_en() {
                                return name_en;
                            }

                            public void setName_en(String name_en) {
                                this.name_en = name_en;
                            }

                            public String getName_pinyin() {
                                return name_pinyin;
                            }

                            public void setName_pinyin(String name_pinyin) {
                                this.name_pinyin = name_pinyin;
                            }

                            public int getBusiness_id() {
                                return business_id;
                            }

                            public void setBusiness_id(int business_id) {
                                this.business_id = business_id;
                            }

                            public String getPoi_type() {
                                return poi_type;
                            }

                            public void setPoi_type(String poi_type) {
                                this.poi_type = poi_type;
                            }

                            public int getDistrict_id() {
                                return district_id;
                            }

                            public void setDistrict_id(int district_id) {
                                this.district_id = district_id;
                            }

                            public double getLat() {
                                return lat;
                            }

                            public void setLat(double lat) {
                                this.lat = lat;
                            }

                            public double getLng() {
                                return lng;
                            }

                            public void setLng(double lng) {
                                this.lng = lng;
                            }

                            public String getLocation_name() {
                                return location_name;
                            }

                            public void setLocation_name(String location_name) {
                                this.location_name = location_name;
                            }

                            public double getBlat() {
                                return blat;
                            }

                            public void setBlat(double blat) {
                                this.blat = blat;
                            }

                            public double getBlng() {
                                return blng;
                            }

                            public void setBlng(double blng) {
                                this.blng = blng;
                            }

                            public double getYouji_lat() {
                                return youji_lat;
                            }

                            public void setYouji_lat(double youji_lat) {
                                this.youji_lat = youji_lat;
                            }

                            public double getYouji_lng() {
                                return youji_lng;
                            }

                            public void setYouji_lng(double youji_lng) {
                                this.youji_lng = youji_lng;
                            }

                            public int getYouji_poi_id() {
                                return youji_poi_id;
                            }

                            public void setYouji_poi_id(int youji_poi_id) {
                                this.youji_poi_id = youji_poi_id;
                            }

                            public boolean isIs_in_china() {
                                return is_in_china;
                            }

                            public void setIs_in_china(boolean is_in_china) {
                                this.is_in_china = is_in_china;
                            }

                            public String getYouji_poi_name() {
                                return youji_poi_name;
                            }

                            public void setYouji_poi_name(String youji_poi_name) {
                                this.youji_poi_name = youji_poi_name;
                            }
                        }
                    }
                }
            }
        }

        public static class GoodsBean {
            private String type;
            private String title;
            private Object url;
            private String photo_url;
            private WikiDestinationBean wiki_destination;

            public static GoodsBean objectFromData(String str) {

                return new Gson().fromJson(str, GoodsBean.class);
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }

            public WikiDestinationBean getWiki_destination() {
                return wiki_destination;
            }

            public void setWiki_destination(WikiDestinationBean wiki_destination) {
                this.wiki_destination = wiki_destination;
            }

            public static class WikiDestinationBean {
                private int id;
                private String name_zh_cn;
                private int children_count;
                private String created_at;
                private String updated_at;
                private String name_en;
                private String image_url;

                public static WikiDestinationBean objectFromData(String str) {

                    return new Gson().fromJson(str, WikiDestinationBean.class);
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName_zh_cn() {
                    return name_zh_cn;
                }

                public void setName_zh_cn(String name_zh_cn) {
                    this.name_zh_cn = name_zh_cn;
                }

                public int getChildren_count() {
                    return children_count;
                }

                public void setChildren_count(int children_count) {
                    this.children_count = children_count;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public String getName_en() {
                    return name_en;
                }

                public void setName_en(String name_en) {
                    this.name_en = name_en;
                }

                public String getImage_url() {
                    return image_url;
                }

                public void setImage_url(String image_url) {
                    this.image_url = image_url;
                }
            }
        }
    }
}
