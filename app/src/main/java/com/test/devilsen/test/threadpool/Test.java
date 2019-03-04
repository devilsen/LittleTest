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


    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1, 1,
                1L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());

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

        for (int i = 2; i < 6; i++) {
            final int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start runnable " + finalI);
                }
            });
        }

        executorService.shutdown();

        System.out.println("main finish ------");
    }
}
