package me.sam.practice.sync;

/**
 * desc :
 * date : 2020-02-19 18:30
 *
 * @author : dongSen
 */
public class Main1 {
    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread thread1 = new Thread(new MyThread1(demo));
        Thread thread2 = new Thread(new MyThread2(demo));
        thread1.start();
        thread2.start();
    }
}
