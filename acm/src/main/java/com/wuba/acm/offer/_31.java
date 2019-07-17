package com.wuba.acm.offer;

/**
 * desc : 连续子数组的最大值
 * date : 2019/3/13
 *
 * @author : dongSen
 * <p>
 * 输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为 O(n)。
 * 例如输入的数组为{1, -2, 3, 10, -4, 7, 2, -5}，和最大的子数组为｛3, 10, -4, 7, 2}。因此输出为该子数组的和 18 。
 */
public class _31 {

    public static void main(String[] args) {

        int[] a = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
        int[] b = new int[]{-2, -8, -1, -5, -9};
        int[] c = new int[]{2, 8, 1, 5, 9};
        System.out.println(findGreatestSumOfSubArray(a));
        System.out.println(findGreatestSumOfSubArray(b));
        System.out.println(findGreatestSumOfSubArray(c));

    }

    private static int findGreatestSumOfSubArray(int[] arr) {
        if (arr == null)
            return 0;

        int max = Integer.MIN_VALUE;
        int currentMax = 0;
        for (int num : arr) {
            if (currentMax <= 0) {
                currentMax = num;
            } else {
                currentMax += num;
            }

            if (currentMax > max) {
                max = currentMax;
            }
        }
        return max;
    }

}
