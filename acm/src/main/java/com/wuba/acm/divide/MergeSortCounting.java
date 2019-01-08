package com.wuba.acm.divide;

/**
 * desc : 求逆序度
 * date : 2018/12/26
 *
 * @author : dongSen
 */
public class MergeSortCounting {

    public static void main(String[] args) {
        MergeSortCounting counting = new MergeSortCounting();

        int[] array = new int[]{1, 5, 6, 2, 3, 4};
        int count = counting.count(array, array.length);
        System.out.println("逆序度为： " + count);
    }

    private int num = 0; // 全局变量或者成员变量

    public int count(int[] a, int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }

    private void mergeSortCounting(int[] a, int p, int r) {
        if (p >= r) return;
        int q = (p + r) / 2;
        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        merge(a, p, q, r);
    }

    private void merge(int[] a, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                num += (q - i + 1); // 统计 p-q 之间，比 a[j] 大的元素个数
                tmp[k++] = a[j++];
            }
        }
        while (i <= q) { // 处理剩下的
            tmp[k++] = a[i++];
        }
        while (j <= r) { // 处理剩下的
            tmp[k++] = a[j++];
        }
        for (i = 0; i <= r - p; ++i) { // 从 tmp 拷贝回 a
            a[p + i] = tmp[i];
        }
    }

}
