package com.test.devilsen.test.thread;

/**
 * desc :
 * date : 2018/5/14
 *
 * @author : dongSen
 */
public class ThreadJoin {


    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                System.err.println("线程" + Thread.currentThread().getId() + " 打印信息");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.err.println("主线程打印信息");

    }

}