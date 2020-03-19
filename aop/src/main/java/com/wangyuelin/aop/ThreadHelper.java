package com.wangyuelin.aop;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author : yuelinwang
 * time   : 2020/3/19 6:03 PM
 * desc   : 单线程的线程池
 */
public class ThreadHelper {
    private static ThreadHelper INSTANCE = Holder.helper;
    private ExecutorService executor;

    private ThreadHelper(){
        executor = Executors.newCachedThreadPool();
    }

    public static ThreadHelper INSTANCE(){
        return INSTANCE;
    }

    private static class Holder{
        private static ThreadHelper helper = new ThreadHelper();
    }

    /**
     * 提交任务
     * @param task
     */
    public void submit(Runnable task) {
        if(task == null) {
            return;
        }
        executor.submit(task);
    }
}
