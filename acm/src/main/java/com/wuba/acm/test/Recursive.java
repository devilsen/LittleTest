package com.wuba.acm.test;

/**
 * desc :
 * date : 2018/8/14
 *
 * @author : dongSen
 */
public class Recursive {

    public static void main(String[] args) {
        test(100);
    }

    private static void test(int num) {
        if (num == 1)
            return;

        System.out.println(num);
        test(--num);
        System.out.println(num + " ----- ");

    }


}
