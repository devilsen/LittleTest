package com.test.devilsen.test.thread;

/**
 * desc :
 * date : 2018/5/15
 *
 * @author : dongSen
 *         <p>
 *         https://blog.csdn.net/u012050154/article/details/50903326
 *
 *         Sleep 让出CPU资源，但是不释放锁，会暂停此线程指定的时间，但监控依然保持，不会释放对象锁，到时间自动恢复
 *         wait 是Object的方法，调用会放弃对象锁，进入等待队列，待调用notify()/notifyAll()唤醒指定的线程或者所有线程，才会进入锁池，不再次获得对象锁才会进入运行状态
 */
public class WaitAndSleep {

    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            //由于 Thread1和下面Thread2内部run方法要用同一对象作为监视器，如果用this则Thread1和Threa2的this不是同一对象
            //所以用MultiThread.class这个字节码对象，当前虚拟机里引用这个变量时指向的都是同一个对象
            synchronized (WaitAndSleep.class) {
                System.out.println("enter thread1 ...");
                System.out.println("thread1 is waiting");

                try {
                    //释放锁有两种方式：(1)程序自然离开监视器的范围，即离开synchronized关键字管辖的代码范围
                    //(2)在synchronized关键字管辖的代码内部调用监视器对象的wait()方法。这里使用wait方法
                    WaitAndSleep.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread1 is going on ...");
                System.out.println("thread1 is being over!");
            }
        }

    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            //notify方法并不释放锁，即使thread2调用了下面的sleep方法休息10ms，但thread1仍然不会执行
            //因为thread2没有释放锁，所以Thread1得不到锁而无法执行
            synchronized (WaitAndSleep.class) {
                System.out.println("enter thread2 ...");
                System.out.println("thread2 notify other thread can release wait status ...");

                WaitAndSleep.class.notify();

                System.out.println("thread2 is sleeping ten millisecond ...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread2 is going on ...");
                System.out.println("thread2 is being over!");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Thread1());
        thread.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Thread2()).start();
    }
}
