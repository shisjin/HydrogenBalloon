package com.dream.will.hydrogenballoon.bean;

import android.os.Parcel;
import android.os.Parcelable;


public class UrlString implements Parcelable {

    public String url;
    public String title;

    public UrlString(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UrlString() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
    }

    public static final Creator<UrlString> CREATOR=new Creator<UrlString>() {
        @Override
        public UrlString createFromParcel(Parcel source) {
            //从Parcel容器中读取传递数据值，封装成Parcelable对象返回逻辑层。
            UrlString author=new UrlString();
            author.setTitle(source.readString());
            author.setUrl(source.readString());
            return author;
        }

        @Override
        public UrlString[] newArray(int size) {
            //创建一个类型为T，长度为size的数组，仅一句话（return new T[size])即可。方法是供外部类反序列化本类数组使用。
            return new UrlString[size];
        }
    };

    @Override
    public String toString() {
        return "UrlString{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
