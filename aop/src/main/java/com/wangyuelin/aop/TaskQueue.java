package com.wangyuelin.aop;

import android.util.Log;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * author : yuelinwang
 * time   : 2020/3/19 6:17 PM
 * desc   : 任务队列：生产者和消费者模式
 */
public class TaskQueue {
    private static LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private static final int MAX = 100;//最多存100个
    private static volatile boolean isInit;//是否初始化了
    private static volatile boolean stop;//是否停止

    /**
     * 添加任务
     * @param task
     */
    public static void addTask(Runnable task) {
        if(task == null) {
            return;
        }
        if(!isInit) {
            isInit = true;
            init();
        }
        if(taskQueue.size() >= MAX) {
            //将任务丢弃
            Log.d("wyl", "丢弃");
            return;
        }
        //不阻塞
        Log.d("wyl", "添加任务");
        taskQueue.offer(task);
    }

    /**
     * 初始化，开始消费
     */
    private static void init() {
        Log.d("wyl", "init");
        ThreadHelper.INSTANCE().submit(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    try {
                        Runnable task = taskQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    /**
     * 停止线程
     */
    public static void stop(){
        stop = true;
    }
}
