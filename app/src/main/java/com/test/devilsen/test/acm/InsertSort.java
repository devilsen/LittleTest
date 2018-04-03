package com.test.devilsen.test.acm;

/**
 * desc :
 * date : 2018/3/28
 *
 * @author : dongSen
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] a = new int[]{3, 9, 1, 4, 5, 2};
//        sort(a);

        binaryInsertSort(a, 0, a.length - 1);

        for (int n : a) {
            System.out.println(n);
        }
    }

    private static void sort(int[] arr) {
        int temp, j;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            j = i;
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
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
