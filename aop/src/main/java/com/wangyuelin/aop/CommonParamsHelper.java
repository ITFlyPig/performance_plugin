package com.wangyuelin.aop;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * author : yuelinwang
 * time   : 2020/3/23 9:56 AM
 * desc   : 基础的参数
 */
public class CommonParamsHelper {
    private static CommonParamsHelper instance = Holder.commonParamsHelper;

    private CommonParamsHelper(){}

    private static class Holder{
        private static CommonParamsHelper commonParamsHelper = new CommonParamsHelper();
    }

    public CommonParamsHelper getInstance() {
        return instance;
    }

    /**
     * 获取静态的公用参数
     * @return
     */
    public HashMap<String, String> getCommonParams() {
        if(commonParamsStatic == null) {
            commonParamsStatic = new HashMap<>();
        }
        if(TextUtils.isEmpty(appName)) {
            //开始获取参数
            Context context = EasyPerformance.getContext();
            if(context != null) {
                initPackageInfo(context);
                commonParamsStatic.put("appName", appName);
                commonParamsStatic.put("packageName", packageName);
                commonParamsStatic.put("versionName", versionName);
                commonParamsStatic.put("versionCode", String.valueOf(versionCode));
                commonParamsStatic.put("brand", brand);
                commonParamsStatic.put("model", model);
                commonParamsStatic.put("sysVersion", sysVersion);
            }
        }
        return commonParamsStatic;
    }

    private HashMap<String, String> commonParamsStatic;//静态的基本参数
    private String appName;//app的名字
    private String packageName;//app的包名
    private String versionName;//版本名称
    private int versionCode;//版本号
    private String brand;//手机厂商
    private String model;//手机型号
    private String sysVersion;//系统版本号

    /**
     * 初始化包相关的信息
     */
    private void initPackageInfo(Context context) {
        if(context == null) {
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int nameRes = packageInfo.applicationInfo.labelRes;
            //app名称
            appName = context.getResources().getString(nameRes);
            //版本
            versionName = packageInfo.versionName;
            //版本号
            versionCode = packageInfo.versionCode;
            //包名
            packageName = context.getPackageName();
            //手机品牌
            brand = android.os.Build.BRAND;
            //手机型号
            model = android.os.Build.MODEL;
            //系统版本
            sysVersion = android.os.Build.VERSION.RELEASE;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
