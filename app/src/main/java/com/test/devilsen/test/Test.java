package com.test.devilsen.test;

/**
 * desc :
 * date : 2018/6/25
 *
 * @author : dongSen
 */
public class Test {
//
//    public static void main(String[] args) {
//        String a = "123";
//        String b = new String("123");
//        String c = "123";
//        String d = new String("123");
//
//        System.out.println(a == b);
//        System.out.println(a == c);
//        System.out.println(b == c);
//        System.out.println(b == d);
//
//
//        Integer i1 = 321;
//        Integer i2 = 321;
//
//        System.out.println(i1 == i2);
//        System.out.println(i1 == i2);
//        System.out.println(i1.equals(i2));
//    }

    static {
        i = 0;  //  给变量复制可以正常编译通过
//        System.out.print(i);  // 这句编译器会提示“非法向前引用”
    }

    static int i = 1;

    static int j = 1;

    static {
        j = 2;
        System.out.println(j);
    }

    public static void main(String[] args) {
        System.out.println(Test.i);  //1
        System.out.println(Test.j); //2
    }

}

/**
 * 被动使用类字段演示三：
 * <p>
 * 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，
 * 因此不会触发定义常量的类的初始化。
 *
 * 有没有final，很重要
 **/
class ConstClass {

    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLOWORLD = "hello world";
//    public static String HELLOWORLD = "hello world";
}

class Test2 {
    public static void main(String[] args) {
//        System.out.println(ConstClass.HELLOWORLD);

        double atan = Math.atan(0);

        System.out.println("atan:" + atan);

    }
}

/**
 * 被动使用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 **/
class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    public static final int value = 123;
}

class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init!");
    }

    public static void main(String[] args) {
        System.out.println(SuperClass.value);
    }
}

