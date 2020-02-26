package me.sam.practice.sync;

/**
 * desc :
 * date : 2020-02-19 18:29
 *
 * @author : dongSen
 */
public class MyThread1 implements Runnable {

    private Demo demo;

    public MyThread1(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.demo();
    }
}
