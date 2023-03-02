package com.wuba.acm.thread.atsametime;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dongSen on 2023/3/2
 *
 * 同时启动
 * https://blog.csdn.net/wang465745776/article/details/115496735
 */
public class CountdownLatchExample {

    public static void main(String[] args) {
        printAtSametime();
    }

    public static void printAtSametime() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        executorService.submit(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程A执行，执行时间：" + System.currentTimeMillis());
        });

        executorService.submit(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程B执行，执行时间：" + System.currentTimeMillis());
        });

        executorService.submit(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程C执行，执行时间：" + System.currentTimeMillis());
        });

        countDownLatch.countDown();
    }

}
