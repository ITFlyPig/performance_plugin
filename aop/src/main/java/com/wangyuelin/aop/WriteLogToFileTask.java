package com.wangyuelin.aop;

import com.wangyuelin.aop.log.BaseLogBean;

import java.util.List;

/**
 * author : yuelinwang
 * time   : 2020/3/30 10:40 AM
 * desc   : 将log写入到文件的task
 */
public class WriteLogToFileTask implements Runnable {
    private List<BaseLogBean> logs;

    public WriteLogToFileTask(List<BaseLogBean> logs) {
        this.logs = logs;
    }

    @Override
    public void run() {

    }
}
