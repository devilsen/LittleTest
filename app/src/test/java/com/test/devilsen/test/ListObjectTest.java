package com.test.devilsen.test;

import java.util.ArrayList;

/**
 * desc :
 * date : 2018/7/27
 *
 * @author : dongSen
 */
public class ListObjectTest {

    static class Num {
        int index;

        public Num(int index) {
            this.index = index;
        }
    }

    @org.junit.Test
    public void test() {

        ArrayList<Num> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Num(i));
        }

        for (int i = 0; i < list.size(); i++) {
            Num num = list.get(i);
            System.out.println(num.toString());
        }

        System.out.println();

        Num num;
        for (int i = 0; i < list.size(); i++) {
            num = list.get(i);
            System.out.println(num.toString());
        }

    }


}
