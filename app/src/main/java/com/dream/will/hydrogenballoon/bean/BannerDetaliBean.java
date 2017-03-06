package com.dream.will.hydrogenballoon.bean;

import java.util.List;


public class BannerDetaliBean {


    private String message;
    private int status;
    private DataBean data;

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
        private int id;
        private String title;
        private String summary;
        private List<ItemsBean> items;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            private int id;
            private Object title;
            private Object description;
            private String url;
            private Object target_id;
            private String target_type;
            private Object entry_text;
            private Object target;
            private UserActivityBean user_activity;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getTarget_id() {
                return target_id;
            }

            public void setTarget_id(Object target_id) {
                this.target_id = target_id;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public Object getEntry_text() {
                return entry_text;
            }

            public void setEntry_text(Object entry_text) {
                this.entry_text = entry_text;
            }

            public Object getTarget() {
                return target;
            }

            public void setTarget(Object target) {
                this.target = target;
            }

            public UserActivityBean getUser_activity() {
                return user_activity;
            }

            public void setUser_activity(UserActivityBean user_activity) {
                this.user_activity = user_activity;
            }

            public static class UserActivityBean {
                private int id;
                private String made_at;
                private int likes_count;
                private int comments_count;
                private String topic;
                private int contents_count;
                private int district_id;
                private String created_at;
                private int favorites_count;
                private String description;
                private UserBean user;
                private Object poi;
                private int inspiration_activity_id;
                private Object inspiration_activity;
                private boolean current_user_liked;
                private boolean current_user_commented;
                private boolean current_user_favorited;
                private List<ContentsBean> contents;
                private List<DistrictsBean> districts;
                private List<CategoriesBean> categories;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
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

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public Object getPoi() {
                    return poi;
                }

                public void setPoi(Object poi) {
                    this.poi = poi;
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

                public boolean isCurrent_user_liked() {
                    return current_user_liked;
                }

                public void setCurrent_user_liked(boolean current_user_liked) {
                    this.current_user_liked = current_user_liked;
                }

                public boolean isCurrent_user_commented() {
                    return current_user_commented;
                }

                public void setCurrent_user_commented(boolean current_user_commented) {
                    this.current_user_commented = current_user_commented;
                }

                public boolean isCurrent_user_favorited() {
                    return current_user_favorited;
                }

                public void setCurrent_user_favorited(boolean current_user_favorited) {
                    this.current_user_favorited = current_user_favorited;
                }

                public List<ContentsBean> getContents() {
                    return contents;
                }

                public void setContents(List<ContentsBean> contents) {
                    this.contents = contents;
                }

                public List<DistrictsBean> getDistricts() {
                    return districts;
                }

                public void setDistricts(List<DistrictsBean> districts) {
                    this.districts = districts;
                }

                public List<CategoriesBean> getCategories() {
                    return categories;
                }

                public void setCategories(List<CategoriesBean> categories) {
                    this.categories = categories;
                }

                public static class UserBean {
                    private int id;
                    private String name;
                    private int gender;
                    private int level;
                    private String photo_url;
                    private boolean is_follow;

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

                    public boolean isIs_follow() {
                        return is_follow;
                    }

                    public void setIs_follow(boolean is_follow) {
                        this.is_follow = is_follow;
                    }
                }

                public static class ContentsBean {
                    private int id;
                    private Object caption;
                    private String photo_url;
                    private int width;
                    private int height;
                    private int position;

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

                    public int getPosition() {
                        return position;
                    }

                    public void setPosition(int position) {
                        this.position = position;
                    }
                }

                public static class DistrictsBean {
                    private int id;
                    private String name;
                    private String name_en;
                    private String name_pinyin;
                    private Object score;
                    private int level;
                    private String path;
                    private boolean published;
                    private boolean is_in_china;
                    private int user_activities_count;
                    private double lat;
                    private double lng;
                    private boolean is_valid_destination;
                    private int destination_id;

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

                    public Object getScore() {
                        return score;
                    }

                    public void setScore(Object score) {
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

                    public int getUser_activities_count() {
                        return user_activities_count;
                    }

                    public void setUser_activities_count(int user_activities_count) {
                        this.user_activities_count = user_activities_count;
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

                    public boolean isIs_valid_destination() {
                        return is_valid_destination;
                    }

                    public void setIs_valid_destination(boolean is_valid_destination) {
                        this.is_valid_destination = is_valid_destination;
                    }

                    public int getDestination_id() {
                        return destination_id;
                    }

                    public void setDestination_id(int destination_id) {
                        this.destination_id = destination_id;
                    }
                }

                public static class CategoriesBean {
                    private int id;
                    private String name;
                    private String category_type;

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
            }
        }
    }
}
