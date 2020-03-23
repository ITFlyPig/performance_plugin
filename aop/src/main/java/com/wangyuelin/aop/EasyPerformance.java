package com.wangyuelin.aop;

import android.content.Context;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * author : yuelinwang
 * time   : 2020/3/23 10:05 AM
 * desc   : 框架的入口
 */
public class EasyPerformance {
    private static boolean isLog;//是否输出log
    private static final String TAG = EasyPerformance.class.getSimpleName();
    private static WeakReference<Context> contextRef;
    private static volatile boolean isInit;//是否初始化

    /**
     * 初始化
     * @param context
     */
    public static void init(Context context) {
        if(context == null) {
            if(isLog) {
                Log.d(TAG, "init传入参数 context 为空");
            }
            return;
        }
        //开始初始化
        if(contextRef == null) {
            contextRef = new WeakReference<Context>(context);//保存，用于获取和app相关的数据
        }

        isInit = true;
    }

    /**
     * 获取Context
     * @return
     */
    public static Context getContext() {
        if(contextRef != null) {
            return contextRef.get();
        }
        return null;
    }
}
