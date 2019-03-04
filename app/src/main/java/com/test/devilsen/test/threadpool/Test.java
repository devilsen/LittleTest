package com.test.devilsen.test.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * desc : 线程池的参数理解
 * https://blog.csdn.net/zero__007/article/details/78915354
 * date : 2019/3/4
 *
 * @author : dongSen
 */
public class Test {


    public static void main(String[] args) throws Exception {

        //(1)线程池单个线程，线程池队列元素个数为1
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                2, 1,
                1L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        //(2)添加任务one
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runnable 1");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("runnable 1 finish");
            }
        });

        //(3)添加任务two
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runnable 2");
            }
        });

        //(4)添加任务three
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runnable 3");
            }
        });

        executorService.shutdown();//(8)关闭线程池，阻塞直到所有任务执行完毕

        System.out.println("task finish ------");//(5)等待任务one执行完毕
    }
}
