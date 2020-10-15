package com.test.devilsen.test;

import org.junit.Test;

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

    @Test
    public void newTemplateBuilder() {
        int width = 720;
        int height = 1280;

        int vShorter = Math.min(width, height);
        int vLonger = Math.max(width, height);

        int tShorter = Math.min(720, vShorter);
        float v = tShorter * (vLonger / (float) vShorter);
        System.out.println("number: " + v);
        int tLonger = (int) Math.ceil(v);

        int tWidth;
        int tHeight;
        tWidth = tShorter;
        tHeight = tLonger;
        System.out.println("width: " + tWidth + " height: " + tHeight);
    }


}
