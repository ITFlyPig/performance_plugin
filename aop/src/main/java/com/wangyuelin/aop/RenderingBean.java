package com.wangyuelin.aop;

/**
 * author : yuelinwang
 * time   : 2020/3/19 12:14 PM
 * desc   : 渲染的bean
 */
public class RenderingBean {
    private int renderType;//渲染的类型
    private String className;//页面类
    private long totalTime;//渲染的总时间

    public RenderingBean(int renderType, String className, long totalTime) {
        this.renderType = renderType;
        this.className = className;
        this.totalTime = totalTime;
    }
}
