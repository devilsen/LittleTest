package com.test.devilsen.test.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc :
 * date : 2020-02-21 10:34
 *
 * @author : dongSen
 */
public class AbsoluteSafeTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger integer = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    integer.incrementAndGet();
                }
            });
        }
        Thread.sleep(1000); //模拟等待执行结束
        System.out.println("------" + integer.toString() + "------");
        executorService.shutdown();
    }

    /**
     * 验证ConcurrentHashMap不是绝对线程安全
     */
    public static void test1() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("key", 0);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    int key = map.get("key") + 1; //step 1
                    map.put("key", key);//step 2
                }
            });
        }
        try {
            Thread.sleep(1000); //模拟等待执行结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------" + map.get("key") + "------");
        executorService.shutdown();
    }

}
