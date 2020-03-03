package com.test.devilsen.test.refrence;

import android.os.Looper;
import android.os.MessageQueue;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * desc :
 * date : 2020/2/28 4:02 PM
 *
 * @author : dongSen
 */
public class WeakReferenceTest {

    private ReferenceQueue<Object> mReferenceQueue;
    private WeakReference<Object> weakReference;

    public static void main(String[] args) {
        WeakReferenceTest weakReferenceTest = new WeakReferenceTest();
        weakReferenceTest.test();
    }

    private void test() {
        mReferenceQueue = new ReferenceQueue<>();
        // 定义一个对象
        Object o = new Object();
        // 定义一个弱引用对象引用 o,并指定引用队列为 mReferenceQueue
        weakReference = new WeakReference<>(o, mReferenceQueue);
        // 去掉强引用
        o = null;

        // 触发应用进行垃圾回收
        Runtime.getRuntime().gc();
        // hack: 延时100ms,等待gc完成
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Reference ref = null;
        // 遍历 mReferenceQueue，取出所有弱引用
        while ((ref = mReferenceQueue.poll()) != null) {
            System.out.println("============ \n ref in queue");
        }
    }

}
