package com.maben.druid_monitor.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程工具类
 */
public class ThreadUtil {

    /**
     * 线程计数
     */
    private static AtomicInteger count = new AtomicInteger(0);
    /**
     * 核心线程数
     */
    private static int corePoolSize = 5;
    /**
     * 最大线程数。可以计算，非核心线程数 = maximumPoolSize - corePoolSize = 2
     */
    private static int maximumPoolSize = 7;
    /**
     * 非核心空闲线程最长存活时间
     */
    private static long keepAliveTime = 10;
    /**
     * 非核心空闲线程最长存活时间的单位
     */
    private static TimeUnit timeUnit = TimeUnit.SECONDS;
    /**
     * 指定阻塞队列的类型和队列长度。可以计算，被核心线程数同时处理的任务个数 = corePoolSize + 队列容量
     */
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(17);
    /**
     * 无界阻塞队列，可以放任意数量的任务，也不用担心队列满，不会调用拒绝策略
     */
    private static LinkedBlockingDeque<Runnable> workQueuenbounded = new LinkedBlockingDeque<>();
    /**
     * 定义线程工厂
     */
    private static ThreadFactory myThreadFactory = (runnable) -> {
        String threadName = "线程" + count.addAndGet(1);
        return new Thread(runnable, threadName);
    };
    /**
     * 定义任务拒绝执行的策略
     */
    private static RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> {
        throw new RejectedExecutionException("当前线程池已满，请稍后操作！");
    };
    /**
     * 等待队列有限制，会触发拒绝机制
     */
    public static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            timeUnit,
            workQueue,
            myThreadFactory,
            rejectedExecutionHandler
    );
    /**
     * 等待队列无限制，不会触发拒绝策略
     */
    public static final ThreadPoolExecutor threadPoolUnbounded = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            timeUnit,
            workQueuenbounded
    );

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            //执行任务
            threadPool.execute(() -> {
                System.out.println("当前执行任务的线程名:" + Thread.currentThread().getName());
                long start = System.currentTimeMillis();
                while (true) {
                    long end = System.currentTimeMillis();
                    if ((end - start) > 10000) {
                        break;
                    }
                }
            });
        }
    }
}
