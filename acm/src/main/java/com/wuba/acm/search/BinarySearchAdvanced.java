package com.wuba.acm.search;

/**
 * desc : 二分查找的几个变体
 * date : 2018/12/17
 *
 * @author : dongSen
 * 变体一：查找第一个值等于给定值的元素
 * 变体二：查找最后一个值等于给定值的元素
 * 变体三：查找第一个大于等于给定值的元素
 * 变体四：查找最后一个小于等于给定值的元素
 */
public class BinarySearchAdvanced {

    public static void main(String[] args) {
        BinarySearchAdvanced searchAdvanced = new BinarySearchAdvanced();

        int[] num = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 9, 10, 11};
        searchAdvanced.bsearch1(num, num.length, 8);
    }

    /**
     * 查找第一个值等于给定值的元素
     */
    public int bsearch1(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     */
    public int bsearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((low + high) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == n - 1 || a[mid + 1] != value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     */
    public int bsearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((low + high) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] < value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     */
    public int bsearc4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((low + high) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == n - 1 || a[mid + 1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 如果有序数组是一个循环有序数组，比如 4，5，6，1，2，3。针对这种情况，如何实现一个求“值等于给定值”的二分查找算法呢？
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) return mid;

            if (nums[mid] == nums[low]) {
                low++;
            } else if (nums[mid] > nums[low]) {
                if (nums[mid] > target && nums[low] <= target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < target && nums[high] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
