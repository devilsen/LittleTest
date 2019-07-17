package com.test.devilsen.test.aidl.trick;

/**
 * desc : 位运算巧用
 * date : 2019/4/3
 *
 * @author : dongSen
 */
public class BitOperation {

    static final int LONG_CLICKABLE = 0x00200000;
    static final int CLICKABLE = 0x00004000;

    public static void main(String[] args) {
        int test = 0x00200000;

        //为什么十六进制可以直接这么用？
        int longClickable = LONG_CLICKABLE & test;
        System.out.print(Integer.parseInt(longClickable + "", 16) + "   " + Integer.parseInt(LONG_CLICKABLE + "", 16)  + "   ");
        System.out.println(longClickable == LONG_CLICKABLE);
        System.out.println((CLICKABLE & test) == CLICKABLE);
    }
}
