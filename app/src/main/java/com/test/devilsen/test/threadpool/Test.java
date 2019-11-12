package com.test.devilsen.test.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * desc : 线程池的参数理解
 * https://www.jianshu.com/p/a118b04f04e9
 * https://blog.csdn.net/zero__007/article/details/78915354
 * date : 2019/3/4
 *
 * @author : dongSen
 */
public class Test {

    public static void main(String[] args) {
        int CPU_CORE_SIZE = Runtime.getRuntime().availableProcessors();
        System.out.println(CPU_CORE_SIZE);

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                2, 2,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolExecutor.DiscardPolicy());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runnable  1  " + Thread.currentThread());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("runnable 1 finish  " + Thread.currentThread());
            }
        });

        for (int i = 2; i < 6; i++) {
            final int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start runnable  " + finalI + "  " + Thread.currentThread());
                }
            });
        }

        executorService.shutdown();

        System.out.println("main finish ------ " + Thread.currentThread());
    }
}
