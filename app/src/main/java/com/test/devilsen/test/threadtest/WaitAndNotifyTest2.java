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
public class WaitAndNotifyTest2 {

    private int count;
    private final Object synObj = new Object();
    private int flag = 1;

    public static void main(String[] args) {

        WaitAndNotifyTest2 test = new WaitAndNotifyTest2();
        try {
            test.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void start() throws InterruptedException {
        final Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (synObj) {
                        while (flag != 1) {//必须为while，因为有2和3两种情况，不然会抢占另外一个线程
                            try {
                                synObj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("A " + count++);
                        flag = 2;//改变条件并唤醒等待线程
                        synObj.notifyAll();//必须为notifyAll
                    }
                }
            }
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (synObj) {
                        while (flag != 2) {
                            try {
                                synObj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("B " + count++);
                        flag = 3;
                        synObj.notifyAll();
                    }
                }
            }
        });

        Thread C = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (synObj) {
                        while (flag != 3) {
                            try {
                                synObj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("C " + count++);
                        flag = 1;
                        synObj.notifyAll();
                    }
                }
            }
        });

        A.start();
        B.start();
        C.start();
    }

}
