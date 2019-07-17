package com.test.devilsen.test.threadtest;

/**
 * desc : 先打印完A，再打印B
 * date : 2019/3/13
 *
 * @author : dongSen
 */
public class JoinTest {

    public static void main(String[] args) {

        final Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    A.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printNumber("B");
            }
        });

        A.start();
        B.start();

    }

    private static void printNumber(String threadName) {
        int i=0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " print: " + i);
        }
    }

}
