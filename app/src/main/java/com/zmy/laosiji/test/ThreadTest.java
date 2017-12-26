package com.zmy.laosiji.test;

import com.zmy.laosiji.utils.ConstantUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 2017/12/22.
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┣┓
 * 　　　　┃　　　　 ┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 */

public class ThreadTest {
    // 创建线程池
//    private static ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(2,10,60, TimeUnit.SECONDS, new );
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);// 他妈的不回收 ，还无限创建
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();// 自动回收 一般使用这个
    ScheduledExecutorService scheduledThereadPool = Executors.newScheduledThreadPool(4);
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
                ConstantUtil.log_e("开启");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };
    public void setTest() {

//        fixedThreadPool.execute(runnable);
//        cachedThreadPool.execute(runnable);
//        scheduledThereadPool.schedule(runnable,2000, TimeUnit.MILLISECONDS);
//        singleThreadExecutor.execute(runnable);
        //使用，延迟1秒执行，每隔2秒执行一次Runnable r
        Executors.newScheduledThreadPool (2).scheduleAtFixedRate(runnable, 1000, 2000, TimeUnit.MILLISECONDS);
    }




}
