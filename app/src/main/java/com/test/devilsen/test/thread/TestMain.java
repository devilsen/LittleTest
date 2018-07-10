package com.test.devilsen.test.thread;

import java.util.Date;

/**
 * desc :
 * date : 2018/5/14
 *
 * @author : dongSen
 */
public class TestMain {

    public static void main(String[] args) {
//        final byte a[] = {0};//以该对象为共享资源
//        new Thread(new WaitAndNotify((1), a), "1").start();
//        new Thread(new WaitAndNotify((2), a), "2").start();

//        int a = 1080;
//        int b = 199;
//        int c = 21;
//
//        int d = ((a - b) >> 1) - c;
//
////        System.out.println(d);
//
//        hashTest();

//        String a1 = "a";
//        String b1 = "a";
//
//        System.out.println(a1 == b1);

        System.out.println(new Date(1528693186312L));
        System.out.println(new Date(1528697399829L));
    }

    private static void hashTest() {

        String key = "aaaa";

        int hashCode = key.hashCode();

        int index = (hashCode & 0x7FFFFFFF) % 16;

        System.out.println("key: " + key + "  hashCode: " + hashCode + "  index: " + index);

        int a = 1;
        System.out.println(~a + "  " + a);

    }

}
