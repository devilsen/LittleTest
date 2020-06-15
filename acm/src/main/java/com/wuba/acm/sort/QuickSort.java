package com.wuba.acm.sort;

/**
 * desc : 快速排序
 * date : 2018/3/21
 * <p>
 * 不稳定
 * 时间:O(nlogn)
 * 空间:O(1)
 *
 * @author : dongSen
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {7, 10, 3, 5, 4, 6, 2, 8, 1, 9};
        quickSort(a, a.length);
        for (int anA : a)
            System.out.print(anA + " ");
    }

    // 快速排序，a是数组，n表示数组的大小
    public static void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n - 1);
    }

    // 快速排序递归函数，p,r为下标
    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r); // 获取分区点
        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q + 1, r);
    }

    private static int partition(int[] a, int p, int right) {
        int pivot = a[right];
        int mark = p;
        for (int i = p; i < right; ++i) {
            if (a[i] < pivot) {
                int tmp = a[mark];
                a[mark] = a[i];
                a[i] = tmp;
                ++mark;
            }
        }

        int tmp = a[mark];
        a[mark] = a[right];
        a[right] = tmp;

        System.out.println("i=" + mark);
        return mark;
    }

}
