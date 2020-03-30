package com.wangyuelin.aop;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * author : yuelinwang
 * time   : 2020/3/23 11:52 AM
 * desc   : sp工具
 */
public class SPUtil {
    private static final String SP_NAME = "log_sp";

    /**
     * 保存string
     * @param key
     * @param value
     * @return
     */
    public static boolean setString(String key, String value) {
        Context context = EasyPerformance.getContext();
        if(context == null || TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.edit().putString(key, value).commit();
    }

    /**
     * 获取String
     * @param key
     * @return
     */
    public static String getString(String key) {
        Context context = EasyPerformance.getContext();
        if(context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }
}
