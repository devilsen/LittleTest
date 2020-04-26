package com.wuba.acm.divide;

/**
 * desc : 求逆序度
 * date : 2018/12/26
 *
 * @author : dongSen
 *
 * 题目：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * 解答：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/shu-zu-zhong-de-ni-xu-dui-by-leetcode-solution/
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

    // -------------------------------代码2------------------------------

    public int reversePairs(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return 0;
        }

        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = nums[i];
        }

        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    /**
     * nums[left..right] 计算逆序对个数并排序
     */
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        // 拆分数组
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        // 排序优化，如果已经有序，直接累加即可
        if (nums[mid] < nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        // 排序并计算
        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    /**
     * [left..mid] 有序 [mid+1,right]有序
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @param temp
     * @return
     */
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) { // 注意=，否则会变为不稳定排序
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                // 只有这里做了累加
                count += (mid - i + 1);
            }
        }
        return count;
    }

}
