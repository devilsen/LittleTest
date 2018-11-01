package com.test.devilsen.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * desc :
 * date : 2018/9/27
 *
 * @author : dongSen
 */
public class RemoveItem {


    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 3) {
                iterator.remove();
            }
        }

        System.out.println(list.toString());

    }

}
