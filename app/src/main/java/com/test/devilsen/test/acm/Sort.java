package com.test.devilsen.test.acm;

import java.util.Arrays;
import java.util.HashMap;

/**
 * desc :
 * date : 2018/4/18
 *
 * @author : dongSen
 */
public class Sort {

    private static HashMap<String, SortInterface> map = new HashMap<>();

    public static void main(String[] args) {

        map.put("CountingSort", new CountingSort());
        map.put("HeapSort", new HeapSort());

        int h = 30;
        int j = 15;
        int i = h & j;

        System.out.println(h + "   " + j + "   " + i);

        int[] a = {12, 4, 2, 55, 12, 89, 82};

        int[] result = map.get("HeapSort").sort(a);

        System.out.println(Arrays.toString(result));

        String eee = "RC:dd";
        switch (eee){
            case "RC":
                break;
        }
    }


}
