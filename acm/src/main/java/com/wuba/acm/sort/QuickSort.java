package com.wuba.acm.sort;

import java.util.ArrayList;

/**
 * desc :
 * date : 2018/3/21
 *
 * @author : dongSen
 */
public class QuickSort {

    public static void main(String[] args) {
//        int[] a = {7, 10, 3, 5, 4, 6, 2, 8, 1, 9};
//        quickSort(a, 1, 10);
//        for (int anA : a)
//            System.out.print(anA + " ");

//        ArrayList<String> list = new ArrayList<>(1000);
//        for (int i = 0; i < 1000000; i++) {
//            list.add(String.valueOf(i));
//        }
//
//        forTest(list);
//        forTest2(list);

//        double aaa = 2.11;
//        double test = 2.01 * aaa;
//        System.out.println(test);
    }

    private static void forTest(ArrayList<String> list) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {

        }

        long end = System.currentTimeMillis();

        System.out.println("fori  " + (end - begin) + "ms");
    }

    private static void forTest2(ArrayList<String> list) {
        long begin = System.currentTimeMillis();
        for (String text : list) {

        }

        long end = System.currentTimeMillis();

        System.out.println("foreach  " + (end - begin) + "ms");
    }

    private static int Partition(int[] a, int p, int r) {
        int x = a[r - 1];
        int i = p - 1;
        int temp;
        for (int j = p; j <= r - 1; j++) {
            if (a[j - 1] <= x) {
                // 交换(a[j-1],a[i]);
                temp = a[j - 1];
                a[j - 1] = a[i];
                a[i] = temp;
                i++;
            }
        }
        //交换(a[r-1],a[i]);
        temp = a[r - 1];
        a[r - 1] = a[i];
        a[i] = temp;
        return i + 1;
    }

    private static void quickSort(int[] a, int p, int r) {
        if (p < r) {
            int q = Partition(a, p, r);
            quickSort(a, p, q - 1);
            quickSort(a, q + 1, r);
        }
    }

}
