package com.wangyuelin.aop;

import android.text.TextUtils;

import com.wangyuelin.aop.log.BaseLogBean;
import com.wangyuelin.aop.util.Constans;
import com.wangyuelin.aop.util.FileIOUtils;
import com.wangyuelin.aop.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author : yuelinwang
 * time   : 2020/3/23 10:57 AM
 * desc   : 管理日志
 */
public class LogManager {
    private static final int MAX_SIZE = 1024 * 1024;//一个文件最大值，超过这个大小，下次新开文件存。
    private static final int MAX_ITEMS = 20;//最大内存缓存item数
    private String curFilePath;
    private ExecutorService executorService = Executors.newFixedThreadPool(2);//两个线程，一个写入日志，一个上传文件
    private List<BaseLogBean> logCache;


    private static LogManager instance = Holder.logManager;
    public static LogManager getInstance() {
        return instance;
    }
    private LogManager(){}
    private static class Holder {
        private static LogManager logManager = new LogManager();
    }


    /**
     * 日志进来,将日志保存到文件
     * 这个方法只限主线程调用
     * @param log
     */
    public static void onLog(BaseLogBean log) {
        if(log == null) {
            return;
        }
        LogManager.getInstance().addLog(log);
    }

    /**
     * 添加log到内存缓存，检查是否应该写入到磁盘文件
     * @param log
     */
    private synchronized void addLog(BaseLogBean log) {
        if(logCache == null) {
            logCache = new ArrayList<>(MAX_ITEMS);
        }
        logCache.add(log);
        if(logCache.size() >= MAX_ITEMS) {//开始写入到log文件
            List<BaseLogBean> shouldWriteLogs = logCache;
            logCache = new ArrayList<>(MAX_ITEMS);
            executorService.submit(new WriteLogToFileTask(shouldWriteLogs));
        }
    }

    /**
     * 写到文件
     * @param logs
     */
    private void writeToFile(List<BaseLogBean> logs) {
        String curFile = getCurFile();
        if(TextUtils.isEmpty(curFile)) {
            return;
        }
        FileIOUtils.writeFileFromList(new File(curFile), logs);
    }

    /**
     * 是否应该新建log文件
     * @return
     */
    private boolean shouldNewLogFile(String curLogFile) {
        if(TextUtils.isEmpty(curLogFile)) {
            return true;
        }
        File curFile = new File(curLogFile);
        return !curFile.exists() || FileUtils.getFileLength(curLogFile) >= MAX_SIZE;
    }

    /**
     * 获取当前log文件
     * @return
     */
    private synchronized String getCurFile() {
        if(TextUtils.isEmpty(curFilePath)) {
            //从sp获取
            curFilePath = SPUtil.getString(Constans.SP.CUR_FILE_PATH);
        }
        if(shouldNewLogFile(curFilePath)) {
            File newLogFile = FileUtils.newLogFile();
            if(newLogFile == null) {
                return null;
            }
            curFilePath = newLogFile.getAbsolutePath();
            SPUtil.setString(Constans.SP.CUR_FILE_PATH, curFilePath);//记录目前的log文件的地址
        }
        return curFilePath;
    }

}
