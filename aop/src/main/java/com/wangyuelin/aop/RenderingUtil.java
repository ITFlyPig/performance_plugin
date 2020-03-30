package com.wangyuelin.aop;

import android.text.TextUtils;

import com.wangyuelin.aop.log.RenderingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * author : yuelinwang
 * time   : 2020/3/19 11:50 AM
 * desc   : 渲染统计：统计activity创建好后到渲染完成时间；fragment创建到渲染的时间
 */
public class RenderingUtil {
    private static final String ONCREATE = "onCreate";
    private static final String ON_RENDERING_END = "onRenderingEnd";
    private static Map<String, Long> rendingMap = new HashMap<>();//记录渲染的时间

    /**
     * 开始创建
     * @param classPath
     */
    public static void onCreate(String classPath) {
        if(TextUtils.isEmpty(classPath)) {
            return;
        }
        rendingMap.put(classPath + ONCREATE, System.currentTimeMillis());
    }

    /**
     * 渲染完成
     * @param classPath
     */
    public static void onRenderingEnd(String classPath, int type) {
        if(TextUtils.isEmpty(classPath)) {
            return;
        }
        //获取创建时的时间
        Long createL = rendingMap.get(classPath + ONCREATE);
        if(createL == null) {
            return;
        }
        long createTime = createL;

        //渲染完成的时间
        long renderingEndTime = System.currentTimeMillis();
        //校验
        if(renderingEndTime < createTime) {
            return;
        }
        //构建记录bean
        RenderingBean bean = new RenderingBean(type, classPath, renderingEndTime - createTime);
        //添加到上传服务器队列


    }

}
