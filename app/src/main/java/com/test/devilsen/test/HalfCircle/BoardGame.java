package com.test.devilsen.test.HalfCircle;

import java.util.LinkedList;

/**
 * author : dongSen
 * date : 2016-08-05 15:40
 * desc :
 */

public class BoardGame {

    static LinkedList<String> linkedList = new LinkedList<>();

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            linkedList.add(i + "");
        }

        for (int i = 0; i < 102; i++) {
            if (linkedList.size() > 0) {
                System.out.println(linkedList.getFirst());
                linkedList.removeFirst();
            }
        }


    }

}

