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

        int[] nums = new int[]{1, 3, 2};
        solution.nextPermutation(nums);

//        System.out.println(result);
//        for (int i = 0; i < strings.size(); i++) {
//            System.out.println(strings.get(i));
//        }
//        while (result != null) {
//            System.out.println(result.val);
//            result = result.next;
//        }
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;

        for (; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) { //find first number which is smaller than it's after number
                break;
            }
        }

        if (i != 0) {
            swap(nums, i - 1); //if the number exist,which means that the nums not like{5,4,3,2,1}
        }
        reverse(nums, i);
    }

    private void reverse(int[] a, int i) {//reverse the number after the number we have found
        int first = i;
        int last = a.length - 1;
        while (first < last) {
            int t = a[first];
            a[first] = a[last];
            a[last] = t;
            first++;
            last--;
        }
    }

    private void swap(int[] a, int i) {
        for (int j = a.length - 1; j > i; j--) {
            if (a[j] > a[i]) {
                int t = a[j];
                a[j] = a[i];
                a[i] = t;
                break;
            }
        }
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