package com.dream.will.hydrogenballoon.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dream.will.hydrogenballoon.other.UtilString;

/**
 * Created by Administrator on 2016/12/22.
 * 判断第一次启动
 */

public class SharedUtil {


    public static boolean isFirstRun(Context context){
        SharedPreferences preferences=context.getSharedPreferences(UtilString.SHARED_NAME,Context.MODE_PRIVATE);
        return preferences.getBoolean(UtilString.FIRST_RUN,true);
    }

    /**
     * 进入主界面
     */
    public static void saveFirstRun(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(UtilString.SHARED_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(UtilString.FIRST_RUN, false).commit();
    }
}
