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
                high = middle - 1;
            }
        }

        return -1;

    }

    /**
     * https://leetcode-cn.com/problems/sqrtx/solution/er-fen-cha-zhao-niu-dun-fa-python-dai-ma-by-liweiw/
     * 二分法的变形
     * 实现开方
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        long left = 0;
        long right = x;
        long mid;
        while (left < right) {
            // 这里取右中值
            mid = left + (right - left + 1) / 2;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return (int) left;
    }

}
