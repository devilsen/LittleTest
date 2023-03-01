package com.wuba.acm.thread.alterprint;

/**
 * Created by dongSen on 2023/3/1
 */
public class WaitNotify_ABC {

    private int num;
    private static final Object LOCK = new Object();

    private void printABC(int targetNum) {
        synchronized (LOCK) {
            while (num % 3 != targetNum) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
            System.out.print(Thread.currentThread().getName());
            LOCK.notifyAll();
        }
    }

    public static void main(String[] args) {
        WaitNotify_ABC wait_notify_acb = new WaitNotify_ABC();
        new Thread(() -> wait_notify_acb.printABC(0), "A").start();
        new Thread(() -> wait_notify_acb.printABC(1), "B").start();
        new Thread(() -> wait_notify_acb.printABC(2), "C").start();
    }

}
