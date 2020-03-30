package com.wangyuelin.aop.log;

/**
 * author : yuelinwang
 * time   : 2020/3/23 11:10 AM
 * desc   : 日志的分类
 */
public interface LogType {
    int FPS = 1;//fps日志
    int METHOD_TIME = 2;//方法时间
    int RENDER = 3;//渲染类统计日志
    int CRASH = 4;//崩溃日志
}
