package com.dream.will.hydrogenballoon.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dream.will.hydrogenballoon.MyApp;
import com.dream.will.hydrogenballoon.bean.DbUserBean;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class DbDao {

    /** 添加 一条 新城市 数据到数据库
     * @param db
     * @param user
     */
    public  static  void addLocation(DBhelper db, DbUserBean user){
        SQLiteDatabase writableDatabase = db.getWritableDatabase();
        boolean b = find(db, user); //存在返回true
        if (b){ //城市存在  就添加点击次数
            String loctionTime = getLoctionTime(db, user.getCityName());
            upData(db,user,loctionTime);
//            Log.i("TAG", "addLocation: -------添加城市点击次数--");
        }else { //存世不存在  存储数据
//            Log.i("TAG", "addLocation: -------添加城市到数据库中--");
            ContentValues values = new ContentValues();
            values.put("USERNAME",user.getUserName());
            values.put("LOCATION",user.getCityName());
            values.put("CITYID",user.getCityid());
            values.put("SELECTTIME",1); //默认存储 点击次数为 1
            values.put("endTime",user.getEndTime());
            writableDatabase.insert(DBhelper.KEY_USER, null, values);
        }
    }

    /**
     * @param db
     * @return  获取全部数据库城市
     */
    public  static List<DbUserBean> getLoction(DBhelper db){
        List<DbUserBean> data = new ArrayList<>();
        SQLiteDatabase writableDatabase = db.getWritableDatabase();
        Cursor query = writableDatabase.query(DBhelper.KEY_USER, null, null, null, null, null, "0+endTime DESC");
        while (query.moveToNext()){
            String location = query.getString(query.getColumnIndex("LOCATION"));
            String cityid = query.getString(query.getColumnIndex("CITYID"));
            String date = query.getString(query.getColumnIndex("endTime"));
            String selecttime = query.getString(query.getColumnIndex("SELECTTIME"));
            Log.i("TAG", "getLoction: ----------"+location+"-时间-" +date+"-次数-"+selecttime);
            data.add(new DbUserBean(MyApp.getInstance().USERNAME,location,cityid,""));
        }
        if (query != null){
            query.close();
        }
        return  data;
    }

    /**  获取城市点击 次数
     * @param db
     * @param findCityName
     * @return
     */
    private   static String getLoctionTime(DBhelper db, String findCityName){

        String tiems = null;
        SQLiteDatabase writableDatabase = db.getWritableDatabase();
        Cursor query = writableDatabase.query(DBhelper.KEY_USER, null, "  LOCATION = ? ", new String[]{findCityName}, null, null, null);
        if (query.getCount() != 0) {
            query.moveToFirst();
            tiems = query.getString(query.getColumnIndex("SELECTTIME"));
//            Log.i("TAG", "getLoctionTime: --------点击次数-"+tiems);
        }
        if (query != null){
            query.close();
        }
//        Log.i("TAG", "getLoctionTime: --------城市点击次数-" + tiems);
        return  tiems;
    }

    /** 更新城市 点击次数
     * @param db 1
     * @param user 1
     * @param upadatTimes 1
     */
    public  static void upData(DBhelper db,DbUserBean user,String upadatTimes){
        ContentValues values = new ContentValues();
        SQLiteDatabase writableDatabase = db.getWritableDatabase();
        values.put("SELECTTIME",Integer.parseInt(upadatTimes)+1);
        values.put("endTime",user.getEndTime());
        Log.i("TAG", "getLoction: --------更新-" +user.getCityName()+"-时间-" +user.getEndTime() );
        writableDatabase.update(DBhelper.KEY_USER, values, " LOCATION = ? ", new String[]{user.getCityName()});
    }

    /**  查找当前城市是否 在数据库中
     * @param  db
     * @param userBean
     * @return
     */
    public  static  boolean  find(DBhelper db, DbUserBean userBean){
        String cityName = userBean.getCityName();
        SQLiteDatabase writableDatabase = db.getWritableDatabase();
//        Log.i("TAG", "find: -------查看数据库中是否有--" + cityName);
        Cursor query = writableDatabase.query(DBhelper.KEY_USER, null, " LOCATION = ?", new String[]{cityName}, null, null, null);
        if (query.getCount() == 0) {
//            Log.i("TAG", "find: --------数据库中 没有 null-");
            return false;
        }else {
//            Log.i("TAG", "find: --------数据库中 不为null-");
            query.close();
            return true;
        }
    }

}
