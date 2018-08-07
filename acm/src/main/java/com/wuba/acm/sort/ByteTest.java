package com.wuba.acm.sort;

/**
 * desc :
 * date : 2018/3/27
 *
 * @author : dongSen
 *         byte是一种primitive，byte就是byte正好占内存也是一个byte。但是在stack上分配给byte的
 *         仍然是4byte。而Byte是对象，她分配在stack上的是ref，但Byte对象当然就在heap上了。
 *         所以(Object)byte就显然不可以。
 */
public class ByteTest {

    public static void main(String[] args) {

        String a = "12";

        //将字符串转为ASCII表的值
        byte[] bytes = a.getBytes();

        for (byte aByte : bytes) {
            System.out.println(aByte);
        }

        System.out.println(new String(bytes));

        byte b = 12;

        System.out.println(Byte.valueOf(b));

    }


}
