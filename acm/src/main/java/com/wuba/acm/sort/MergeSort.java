package com.wuba.acm.sort;

import com.wuba.acm.leetcode.ListNode;

/**
 * desc : 归并排序
 * date : 2018/12/14
 * 稳定 时间:O(nlogn) 空间:O(n) 其实计算是O(nlogn)但是一直使用临时变量计算，所以其实没那么多
 *
 * @author : dongSen
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] a = {7, 10, 3, 5, 4, 6, 2, 8, 1, 9};
        mergeSort(a);

        for (int anA : a) {
            System.out.print(anA + " ");
        }
    }

    // 归并排序算法, a是数组，n表示数组大小
    public static void mergeSort(int[] a, int n) {
        mergeSortInternally(a, 0, n - 1);
    }

    // 递归调用函数
    private static void mergeSortInternally(int[] a, int l, int r) {
        // 递归终止条件
        if (l >= r) return;

        // 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
        int m = l + (r - l) / 2;
        // 分治递归
        mergeSortInternally(a, l, m);
        mergeSortInternally(a, m + 1, r);

        // 将A[p...q]和A[q+1...r]合并为A[p...r]
        merge(a, l, m, r);
    }

    private static void merge(int[] a, int l, int m, int r) {
        int i = l;
        int j = m + 1;
        int k = 0; // 初始化变量i, j, k
        int[] tmp = new int[r - l + 1]; // 申请一个大小跟a[l...r]一样的临时数组
        while (i <= m && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++]; // i++等于i:=i+1
            } else {
                tmp[k++] = a[j++];
            }
        }

        // 判断哪个子数组中有剩余的数据
        int start = i;
        int end = m;
        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = a[start++];
        }

        // 将tmp中的数组拷贝回a[l...r]
        for (i = 0; i <= r - l; ++i) {
            a[l + i] = tmp[i];
        }
    }

    public static void mergeSort(int[] a) {
        if (a == null || a.length == 1) return;

        mergeSort(a, 0, a.length - 1);
    }

    public static void mergeSort(int[] a, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        mergeSort(a, l, m);
        mergeSort(a, m + 1, r);

        mergeTemp(a, l, m, r);
    }

    private static void mergeTemp(int[] a, int l, int m, int r) {
        int i = l;
        int j = m + 1;
        int k = 0;
        int[] temp = new int[r - l + 1];
        while (i <= m && j <= r) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        int start = i;
        int end = m;
        if (j <= r) {
            start = j;
            end = r;
        }

        while (start <= end) {
            temp[k++] = a[start++];
        }

        for (int n = 0; n <= r - l; n++) {
            a[l + n] = temp[n];
        }

    }


    // ----------------------链表排序------------------
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null; // 掐断链表，不然会死循环
        // 得到两个排序好的链表
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        // 合并两个链表
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left == null ? right : left;
        return res.next;
    }
}
