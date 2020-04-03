package com.wuba.acm.leetcode;

/**
 * desc :
 * date : 2020/4/2 11:17 AM
 *
 * @author : dongSen
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = {2, 3, 1, 1, 4};
        boolean result = solution.canJump(nums);

        System.out.println(result);
    }

    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (lastPos <= nums[i] + i) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public boolean canJump1(int[] nums) {
        if (nums.length < 1) {
            return true;
        }

        if (nums[0] >= nums.length - 1) {
            return true;
        }

        return jump(nums, 0, nums.length);
    }

    private boolean jump(int[] nums, int index, int length) {
        if (length < 1) {
            return true;
        }

        if (nums[index] >= length - 1) {
            return true;
        }

        if (nums[index] == 0 && index != nums.length - 1) {
            return false;
        }

        int maxStep = nums[index];
        for (int i = maxStep; i >= 1; i--) {
            return jump(nums, index + i, length - i);
        }
        return false;
    }
}