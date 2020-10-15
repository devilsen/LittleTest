package com.test.devilsen.test.thread;

/**
 * desc : join test
 * date : 2018/5/14
 *
 * @author : dongSen
 *
 * 核心逻辑
 * 其实从源码可以看出，join() 也是利用的等待通知机制：
 * while (isAlive()) {
 *     wait(0);
 * }
 *
 * 在 join 线程完成后会调用 notifyAll() 方法，是在 JVM 实现中调用，所以这里看不出来。
 */
public class ThreadJoin {


    public static void main(String[] args) {
        joinTest1();
//        joinTest2();
    }

    private static void joinTest1() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 1 running");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 1 over");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 加入这个，可以使t1先执行完，再执行t2
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            // 等待线程1终止
            t1.join();
            // 等待线程2终止
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over");
    }

    private static void joinTest2() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                System.err.println("线程 " + Thread.currentThread().getId() + " 打印信息");
            }
        });
        thread.start();

        try {
            // 主线程等待子线程运行结束
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.err.println("主线程打印信息");
    }

}