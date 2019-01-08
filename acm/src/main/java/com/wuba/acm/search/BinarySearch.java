package com.wuba.acm.search;

/**
 * desc : 简单二分查找
 * date : 2018/12/17
 *
 * @author : dongSen
 * <p>
 * 二分查找应用场景的局限性
 * 首先，二分查找依赖的是顺序表结构，简单点说就是数组。
 * 其次，二分查找针对的是有序数据。
 * 再次，数据量太小不适合二分查找。
 * 最后，数据量太大也不适合二分查找。因为它需要一个连续数组。
 */
public class BinarySearch {

    public static int search(int[] nums, int value) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {//注意：是 <= 而不是 <
            int middle = low + ((high - low) >> 1); //如果直接相加，可能会溢出

            if (nums[middle] == value) {
                return middle;
            } else if (nums[middle] < value) {
                low = middle + 1;//注意，如果直接 = middle 有可能会死循环
            } else {
                high = middle - 1;//注意
            }
        }
        return -1;
    }

    //递归实现
    public static int search2(int[] nums, int low, int high, int value) {
        if (low > high) return -1;

        int middle = low + ((high - low) >> 1);
        if (nums[middle] == value) {
            return middle;
        } else if (nums[middle] < value) {
            return search2(nums, middle + 1, high, value);
        } else {
            return search2(nums, low, middle - 1, value);
        }
    }

    public static int searchtest(int[] nums, int value) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (nums[middle] == value) {
                return middle;
            } else if (nums[middle] < value) {
                low = middle + 1;
            } else {
                high = middle -1 ;
            }
        }

        return -1;

    }

}
