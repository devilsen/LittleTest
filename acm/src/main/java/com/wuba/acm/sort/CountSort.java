package com.wuba.acm.sort;

import java.util.Arrays;

/**
 * desc : 计数排序
 * date : 2018/4/12
 *
 * @author : dongSen
 * 时间：O(N+M)
 * 空间：O(M)
 * 稳定
 */
public class CountSort {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 5, 9, 1, 6, 3, 0, 3};
        int[] result = countSort(nums);
        System.out.println(Arrays.toString(result));
    }

    public static int[] countSort(int[] arrays) {
        if (arrays == null || arrays.length < 2) return arrays;
        // 1. 计算出数组内的最大值和最小值，求出差值d
        int min = arrays[0];
        int max = arrays[0];
        for (int i = 0; i < arrays.length; i++) {
            if (min > arrays[i]) {
                min = arrays[i];
            }
            if (max < arrays[i]) {
                max = arrays[i];
            }
        }
        int d = max - min;

        // 2. 创建统计元素
        int[] countArray = new int[d + 1];
        for (int i = 0; i < arrays.length; i++) {
            countArray[arrays[i] - min]++;
        }

        // 3. 统计数组做变形，后面的元素等于前面的元素只和
        int sum = 0;
        for (int i = 0; i < countArray.length; i++) {
            sum += countArray[i];
            countArray[i] = sum;
        }

        // 4. 倒序遍历原始数列，从统计数组找到正确的位置，输出到结果数组
        int[] sortedArray = new int[arrays.length];
        for (int i = arrays.length - 1; i >= 0; i--) {
            sortedArray[countArray[arrays[i] - min] - 1] = arrays[i];
            countArray[arrays[i] - min]--;
        }
        return sortedArray;
    }

}
