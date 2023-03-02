package com.wuba.acm.thread.alterprint;

import java.util.concurrent.Semaphore;

/**
 * <a href="https://blog.csdn.net/a772304419/article/details/106002301">交替打印 A1B2C3</a>
 *
 * 使用Semaphore实现线程的交替执行打印 A1B2...
 * Semaphore 基于AQS（内部维护了一个队列）可以用于限流 最多允许多少线程同时运行
 * 可以有很多个线程  但同时允许运行的线程有限制2个 new Semaphore(2)
 */
public class T11_TestSemaphore_A1B2 {
    public static void main(String[] args) {
        //允许一个线程同时执行
        // Semaphore s = new Semaphore(1);
        // 允许两个纤程同时执行
        Semaphore s = new Semaphore(2);
        //2 同时允许两个线程运行  true 公平与否 默认非公平
        // Semaphore s = new Semaphore(2, true);

        new Thread(() -> {
            try {
                s.acquire();//获得许可 一共就2个许可

                char[] chs = {'A', 'B', 'C', 'D', 'E'};
                for (int i = 0; i < chs.length; i++) {
                    System.out.println(chs[i]);
                    Thread.sleep(200);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(() -> {
            try {
                s.acquire();

                int[] ints = {1, 2, 3, 4, 5};
                for (int i = 0; i < ints.length; i++) {
                    System.out.println(ints[i]);
                    Thread.sleep(200);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();
    }
}