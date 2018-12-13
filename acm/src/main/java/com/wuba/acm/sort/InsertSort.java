package com.wuba.acm.sort;

/**
 * desc : 插入排序
 * date : 2018/3/28
 * 插入排序要比冒泡稍好，主要是因为交换的时候，插入只需要一次交换，而冒泡需要三次
 * @author : dongSen
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] a = new int[]{3, 9, 1, 4, 5, 2};
//        sort(a);

//        binaryInsertSort(a, 0, a.length - 1);

        sort(a);
        for (int n : a) {
            System.out.print(n + " ");
        }
    }

    private static void sort(int[] arr) {
        if (arr.length < 2)
            return;

        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] <= value) {
                    break;
                } else {
                    arr[j + 1] = arr[j];
                }
            }
            arr[j + 1] = value;
        }
    }

    private static void binaryInsertSort(int[] a, int left, int right) {
        int low, middle, high;
        int temp;
        for (int i = left + 1; i <= right; i++) {
            temp = a[i];
            low = left;
            high = i - 1;
            while (low <= high) {
                middle = (low + high) / 2;
                if (a[i] < a[middle])
                    high = middle - 1;
                else
                    low = middle + 1;
            }

            for (int j = i - 1; j >= low; j--)
                a[j + 1] = a[j];

            a[low] = temp;
        }
    }


}
