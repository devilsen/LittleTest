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

        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = solution.permute(nums);
        for (int i = 0; i < permute.size(); i++) {
            System.out.println(permute.get(i));
        }
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