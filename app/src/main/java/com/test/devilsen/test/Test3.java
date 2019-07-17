package com.test.devilsen.test;

/**
 * desc :
 * date : 2018/6/28
 *
 * @author : dongSen
 */
public class Test3 {
    /**
     * 父类中定义的静态语句块要优于子类的变量赋值操作
     * JVM保证一个类的clinit方法在多线程中被正确加锁、同步
     */
    static class Parent {
        public static int A = 1;

        static {
            A = 2;
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }

}
