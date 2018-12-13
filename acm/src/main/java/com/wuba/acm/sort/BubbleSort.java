package com.wuba.acm.sort;

/**
 * desc : 冒泡排序
 * date : 2018/12/13
 * https://time.geekbang.org/column/article/41802
 *
 * @author : dongSen
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 6, 8, 1, 2, 9, 5};
        BubbleSort sort = new BubbleSort();
        sort.sort(nums);

        for (int num : nums) {
            System.out.print(num + "  ");
        }
    }

    private void sort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < nums.length - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < length - i - 1; j++) {
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

}
