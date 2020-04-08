package com.wuba.acm.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * desc : 全排列
 * date : 2020/4/8
 *
 * @author : dongSen
 * <p>
 * 全排列是经典的回溯问题，可以将这个问题作为回溯思想的切入点
 * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-xiang-jie-by-labuladong-2/
 * <p>
 * 各种搜索问题其实都是树的遍历问题
 * <p>
 * 回溯的3个步骤（条件）
 * 1. 路径，已经做出的选择
 * 2. 选择列表，选择面临的选择
 * 3. 结束条件，终止条件
 */
public class PermuteAll {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = new PermuteAll().permute(nums);
        for (int i = 0; i < permute.size(); i++) {
            System.out.println(permute.get(i));
        }
    }

    private List<List<Integer>> result = new LinkedList<>();

    private List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return result;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        // 回溯终止条件
        if (nums.length == track.size()) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 前序遍历操作
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            // 回溯
            backtrack(nums, track);
            // 后序遍历操作
            track.removeLast();
        }
    }
}
