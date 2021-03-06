package com.test.devilsen.test.thread;

/**
 * desc : wait、notify、notifyAll是线程中通信可以使用的方法。线程中调用了wait方法，
 * 则进入阻塞状态，只有等另一个线程调用与wait同一个对象的notify方法。
 * 这里有个特殊的地方，调用wait或者notify，前提是需要获取锁，也就是说，
 * 需要在同步块中做以上操作。
 * <p>
 * 所以sleep()和wait()方法的最大区别是：
 * 　　　　sleep()睡眠时，保持对象锁，仍然占有该锁；
 * 　　　　而wait()睡眠时，释放对象锁。
 * <p>
 * date : 2018/5/14
 *
 * @author : dongSen
 */
public class WaitAndNotify implements Runnable {

    private int number;
    public final byte[] res;
    public static int count = 5;

    public WaitAndNotify(int number, byte[] a) {
        this.number = number;
        res = a;
    }

    @Override
    public void run() {
        synchronized (res) {
            while (count-- > 0) {
                System.out.println("count: " + count);
                res.notify();

                try {
                    res.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "获得锁，wait()后的代码继续运行：" + number);
            }
        }
    }

    private static void wait5ThenNotifyTest() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 1 running");
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                t2.notify();
                System.out.println("thread 1 over");
            }
        });
        try {
            // 加入这个，可以使t1先执行完，再执行t2
            t1.wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 2 running");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 2 over");
            }
        });
        t1.start();
        t2.start();
        try {
            // 等待线程2终止
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over");
    }

    public static void main(String[] args) {
//        final byte a[] = {0};//以该对象为共享资源
//        new Thread(new WaitAndNotify((1), a), "1").start();
//        new Thread(new WaitAndNotify((2), a), "2").start();

        wait5ThenNotifyTest();
    }
}
