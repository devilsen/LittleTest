package com.wuba.acm.thread.sequenceprint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dongSen on 2023/3/1
 * <p>
 * 依次打印
 * https://www.cnblogs.com/rever/p/14768553.html
 */
public class FIFOThreadExample {

    public static void foo(String name) {
        System.out.print(name);
    }

    /**
     * 1. join
     */
    public static void joinThread(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> foo("A"));
        Thread thread2 = new Thread(() -> foo("B"));
        Thread thread3 = new Thread(() -> foo("C"));
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
    }

    /**
     * 2. Executor
     */
    public static void executorThread(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> foo("A"));
        Thread thread2 = new Thread(() -> foo("B"));
        Thread thread3 = new Thread(() -> foo("C"));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(thread1);
        executor.submit(thread2);
        executor.submit(thread3);
        executor.shutdown();
    }


}