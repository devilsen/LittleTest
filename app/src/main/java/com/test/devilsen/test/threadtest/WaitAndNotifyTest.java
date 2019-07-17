package com.test.devilsen.test.threadtest;

/**
 * desc : 两个线程交替打印
 * date : 2019/3/13
 *
 * @author : dongSen
 * <p>
 * 1.首先创建一个 A 和 B 共享的对象锁 lock = new Object();
 * 2.A打印完之后，唤醒锁，之后调用wait方法，交出锁的控制权，进入到wait状态
 * 3.同理，B也是同样的逻辑。
 */
public class WaitAndNotifyTest {

    private int count;
    private final Object lock = new Object();

    public static void main(String[] args) {

        WaitAndNotifyTest test = new WaitAndNotifyTest();
        test.start();

    }

    private void start() {
        final Thread A = new Thread(new Runnable() {

            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        System.out.println("偶数： " + count++);
                        lock.notify();//同时开始竞争
                        if (count < 100) {
                            try {
                                lock.wait();//让出锁
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        System.out.println("奇数： " + count++);
                        lock.notify();
                        if (count < 100) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        A.start();
        B.start();
    }

}
