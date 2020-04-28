package com.wuba.acm.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * desc :
 * date : 2020/4/2 11:17 AM
 *
 * @author : dongSen
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

//        int[][] nums = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        solution.merge(nums);
//        System.out.println(nums);

//        String word = solution.getPermutation(3, 3);
//        System.out.println(word);

//        int[] nums = {1, 2, 3};
//        List<List<Integer>> permute = solution.permute(nums);
//        for (int i = 0; i < permute.size(); i++) {
//            System.out.println(permute.get(i));
//        }

//        int[] nums = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int[] nums = {1, 1, 2, 1, 1};
        int number = solution.numberOfSubarrays(nums, 3);
        System.out.println(number);
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int left = 0, right = 0, oddCnt = 0, res = 0;
        while (right < nums.length) {
            // 右指针先走，每遇到一个奇数则 oddCnt++。
            if ((nums[right++] & 1) == 1) {
                oddCnt++;
            }

            //  若当前滑动窗口 [left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
            if (oddCnt == k) {
                // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
                // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right++;
                }
                int rightEvenCnt = right - tmp;
                // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    leftEvenCnt++;
                    left++;
                }
                // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
                res += (leftEvenCnt + 1) * (rightEvenCnt + 1);

                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                left++;
                oddCnt--;
            }

        }

        return res;
    }

    private int findLeftEven(int[] nums, int left) {
        if (left == 0) {
            return 0;
        }
        int count = 0;
        for (int i = left - 1; i >= 0; i--) {
            if (!isOdd(nums[i])) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int findRightEven(int[] nums, int right) {
        if (right > nums.length) {
            return 0;
        }
        int count = 0;
        for (int i = right + 1; i < nums.length; i++) {
            if (!isOdd(nums[i])) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private boolean isOdd(int num) {
        return (num & 1) == 1;
    }

    List<List<Integer>> result = new LinkedList<>();

    List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return result;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track);
            track.removeLast();
        }
    }

}