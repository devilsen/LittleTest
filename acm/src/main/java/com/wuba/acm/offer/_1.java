package com.wuba.acm.offer;

import java.util.Arrays;

/**
 * desc : 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * date : 2019-10-23 10:35
 *
 * @author : dongSen
 */
public class _1 {

    public boolean Find(int target, int[][] array) {
        for (int i = 0; i < array.length; i++) {
            int[] subArray = array[i];
            for (int j = 0; j < subArray.length; j++) {
                int num = subArray[j];
                if (num == target) {
                    return true;
                } else if (num > target) {
                    break;
                }
            }
        }

        return false;
    }

    public boolean Find1(int target, int[][] array) {
        int j = 0;
        int arrow = 1;
        int right = 1;
        int left = -1;
        for (int i = 0; i < array.length; i++) {
            int[] subArray = array[i];
            for (; j < subArray.length && j >= 0; j += arrow) {
                int num = subArray[j];
                if (num == target) {
                    return true;
                }

                if (arrow == right && target < num) {
                    arrow = left;
                    break;
                }

                if (arrow == left && target > num) {
                    arrow = right;
                    break;
                }

                if (j == subArray.length - 1) {
                    j = 0;
                    break;
                }
            }
        }

        return false;
    }

    public boolean Find2(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }

        for (int i = 0; i < array.length; i++) {
            int[] subArray = array[i];

            if (subArray[0] > target) {
                return false;
            }

            int subIndex = binarySearch(subArray, 0, subArray.length, target);

            if (subIndex >= 0) {
                return true;
            }
        }

        return false;
    }

    private static int binarySearch(int[] a, int fromIndex, int toIndex,
                                    int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * 1    2   3   4   5
     * 10   20  30  40  50
     * 100  200 300 400 500
     */
    public static void main(String[] args) {
        _1 test = new _1();
        int[][] arrays = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        test.Find2(5, arrays);
    }

}
