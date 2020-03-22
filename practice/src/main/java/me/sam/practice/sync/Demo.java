package me.sam.practice.sync;

/**
 * desc :
 * date : 2020-02-19 18:28
 *
 * @author : dongSen
 */
public class Demo {

    public synchronized void demo() {
        while (true) {   //synchronized方法内部是一个死循环，一旦一个线程持有过后就不会释放这个锁
            System.out.println(Thread.currentThread());
        }
    }

    public void demo2() {
        System.out.println("---------------");
    }
}
