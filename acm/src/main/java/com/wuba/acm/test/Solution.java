package com.wuba.acm.test;

/**
 * desc :
 * date : 2018/12/10
 *
 * @author : dongSen
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode listNode = new ListNode(1);
//        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);

        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int i = solution.search(nums, 1);

        System.out.println(i);
//        System.out.println(result);
//        for (int i = 0; i < strings.size(); i++) {
//            System.out.println(strings.get(i));
//        }
//        while (result != null) {
//            System.out.println(result.val);
//            result = result.next;
//        }
    }

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

    private void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}