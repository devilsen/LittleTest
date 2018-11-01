package com.test.devilsen.test.thread;

/**
 * desc :
 * date : 2018/9/21
 *
 * @author : dongSen
 */
public class ThreadMessage {

    private static ReceiveRunnable receiveRunnable = new ReceiveRunnable();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            receiveRunnable.setData(i);

        }
        receiveRunnable.start();

    }

    static class ReceiveRunnable extends Thread {

        int left;

        ReceiveRunnable() {
            super("receive message");
        }

        public void setData(int left) {
            this.left = left;

            System.out.println(Thread.currentThread());
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread());

        }
    }
}
