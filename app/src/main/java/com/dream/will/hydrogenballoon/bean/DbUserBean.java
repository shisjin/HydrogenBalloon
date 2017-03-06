package com.dream.will.hydrogenballoon.bean;


public class DbUserBean {
    private String userName;
    private String cityName;
    private  String cityid;
    private  String endTime;

    public DbUserBean(String userName, String cityName, String cityid, String endTime) {
        this.userName = userName;
        this.cityName = cityName;
        this.cityid = cityid;
        this.endTime = endTime;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setLocation(String cityName) {
        this.cityName = cityName;
    }

}
