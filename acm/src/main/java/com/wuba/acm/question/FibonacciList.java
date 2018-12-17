package com.wuba.acm.question;

/**
 * desc :
 * date : 2018/7/3
 *
 * @author : dongSen
 */
public class FibonacciList {


    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            System.out.println(fib(i) + " ");
        }
    }

    private static int fib(int a) {
        if (a == 1 || a == 2) {
            return 1;
        }

        return fib(a - 1) + fib(a - 2);
    }

}
