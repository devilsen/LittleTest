package com.wuba.acm.bit;

/**
 * desc : 经典分治
 * date : 2018/7/27
 *
 * @author : dongSen
 */
public class FindOdd {

    private static int[] num1 = {1, 1, 2, 2, 3, 5, 5};
    private static int[] num2 = {1, 1, 2, 2, 3, 4, 5, 5};

    public static void main(String[] args) {

        test();
        test2();

    }

    public static void test() {
        int a = 0;
        for (int n : num1) {
            a = a ^ n;
            System.out.println("---  " + a);
        }

        System.out.println(a);
    }

    public static void test2() {
        int a = 0;
        int b = 0;
        for (int n : num2) {
            if ((n & 1) == 1) {
                a = a ^ n;
            } else {
                b = b ^ n;
            }
        }

        System.out.println(a + "   " + b);
    }


}
