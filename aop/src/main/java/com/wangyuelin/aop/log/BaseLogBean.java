package com.wangyuelin.aop.log;

import java.io.Serializable;

/**
 * author : yuelinwang
 * time   : 2020/3/23 11:06 AM
 * desc   : Log的base类
 */
public class BaseLogBean implements Serializable {
    public int logType;//日志的分类

    public BaseLogBean(int logType) {
        this.logType = logType;
    }
}
