package com.dream.will.hydrogenballoon.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 */

public class DBhelper extends SQLiteOpenHelper {

    private static final String KEY_DBNAME = "dbname.bd";
    private static final int CURRENT = 1;
    private static final int START = 1;
    public static final String KEY_USER = "WanTo";

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, KEY_DBNAME, null, CURRENT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表字段说明
        // _id 自动增长
        //用户的名字
        //用户点击的城市
        //用户点击城市的id
        //用户点击城市时 的时间
        //用户第一次点击这个城市时的时间
        db.execSQL("CREATE TABLE IF NOT EXISTS " + KEY_USER + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME,LOCATION,CITYID,SELECTTIME," +
                "endTime,DATE timestamp not null default (datetime('now','localtime')))");
        //首次安装时候是高版本  比如3 就需要调用更新方法创建之前版本没有的表
        onUpgrade(db, START, CURRENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //只有更新的时候才会调用这个方法  所以 有可能用户直接从1版本跳到3版本
            //就要  考虑到在第二版本的时候有一个表
        //方法：：：判断老的版本号，老的版本号往后的版本创建的表都要创建一次
        switch (oldVersion) {
            case 1:
                db.execSQL("CREATE TABLE IF NOT EXISTS " + KEY_USER + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "USERNAME,LOCATION,CITYID,SELECTTIME," +
                        "endTime,DATE timestamp not null default (datetime('now','localtime')))");
                break;
        }
    }
}
