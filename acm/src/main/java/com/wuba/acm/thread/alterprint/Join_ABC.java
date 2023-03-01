package com.wuba.acm.thread.alterprint;

/**
 * Created by dongSen on 2023/3/1
 */
class Join_ABC {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new PrintABC(null), "A");
            Thread t2 = new Thread(new PrintABC(t1), "B");
            Thread t3 = new Thread(new PrintABC(t2), "C");
            t1.start();
            t2.start();
            t3.start();
            Thread.sleep(10); //这里是要保证只有t1、t2、t3为一组，进行执行才能保证t1->t2->t3的执行顺序。
        }

    }

    static class PrintABC implements Runnable {
        private final Thread beforeThread;

        public PrintABC(Thread beforeThread) {
            this.beforeThread = beforeThread;
        }

        @Override
        public void run() {
            if (beforeThread != null) {
                try {
                    beforeThread.join();
                    System.out.print(Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.print(Thread.currentThread().getName());
            }

        }
    }
}