package com.wuba.acm.sort;

/**
 * desc : 冒泡排序
 * date : 2018/12/13
 * https://time.geekbang.org/column/article/41802
 * <p>
 * n^2
 * 稳定排序
 * 原地排序
 *
 * @author : dongSen
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 6, 8, 1, 2, 9, 5};
        BubbleSort sort = new BubbleSort();
        sort.sort2(nums);

        for (int num : nums) {
            System.out.print(num + "  ");
        }
    }

    private void sort(int[] nums) {
        int length = nums.length - 1;
        for (int i = 0; i < length; i++) {
            boolean swap = false;
            for (int j = 0; j < length - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    swap = true;
                }
            }

            if (!swap) {
                break;
            }
        }
    }

    private void sort2(int[] nums) {
        if (nums == null) return;
        int length = nums.length - 1;
        int lastSortBorder = 0;
        int sortBorder = length;
        boolean isSort;
        for (int i = 0; i < length; i++) {
            isSort = true;
            for (int j = 0; j < sortBorder; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;

                    isSort = false;
                    lastSortBorder = j;
                }
            }
            sortBorder = lastSortBorder;
            if (isSort) {
                break;
            }
        }
    }

}
