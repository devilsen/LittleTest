package com.wuba.acm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

//        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
//        solution.solveNQueens(4);

//        System.out.println(i);
//        System.out.println(result);
//        for (int i = 0; i < strings.size(); i++) {
//            System.out.println(strings.get(i));
//        }
//        while (result != null) {
//            System.out.println(result.val);
//            result = result.next;
//        }

//        int[] nums = new int[]{10, 1, 2, 7, 6, 1, 5};
//        List<List<Integer>> lists = solution.combinationSum(nums, 8);
//
//        for (int i = 0; i < lists.size(); i++) {
//            List<Integer> integers = lists.get(i);
//            for (int j = 0; j < integers.size(); j++) {
//                Integer integer = integers.get(j);
//                System.out.print(integer + "  ");
//            }
//            System.out.println();
//        }

        String multiply = solution.multiply("99", "9");

        System.out.println(multiply);

    }

    public String multiply(String num1, String num2) {
        int[] numArray1 = new int[num1.length()];
        int[] numArray2 = new int[num2.length()];

        for (int i = 0; i < num1.length(); i++) {
            int c = num1.charAt(i) - 48;
            numArray1[i] = c;
        }
        for (int i = 0; i < num2.length(); i++) {
            int c = num2.charAt(i) - 48;
            numArray2[i] = c;
        }

        int length = numArray1.length + numArray2.length;

        int[][] bList = new int[numArray2.length][length];
        int aIndex = 0;
        for (int i = numArray1.length - 1; i >= 0; i--) {
            int a = numArray1[i];
            int[] aList = new int[length];
            int index = length - 1 - aIndex;
            int last = 0;
            for (int j = numArray2.length - 1; j >= 0; j--) {
                int b = numArray2[j];

                int muti = a * b + last;

                if (muti > 9) {
                    last = muti / 10;
                }
                aList[index] = muti % 10;

                index--;
            }
            if (last > 0)
                aList[index] = last;

            bList[aIndex] = aList;
            aIndex++;
        }

        int[] cList = new int[length];
        for (int i = 0; i < bList.length; i++) {
            int[] integers = bList[i];
            int last = 0;
            for (int j = integers.length - 1; j >= 0; j--) {
                int add = integers[j] + cList[j] + last;
                if (add > 9) {
                    last = last / 10;
                }
                cList[j] = add % 10;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int aCList : cList) {
            stringBuilder.append(aCList);
        }

        return stringBuilder.toString();
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<Integer>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, ArrayList<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                if (i > 0 && i != start && nums[i] == nums[i - 1])
                    continue;

                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private List<List<String>> lists = new ArrayList<>();
    private int[] result;

    public List<List<String>> solveNQueens(int n) {
        result = new int[n];
        nQueen(0, n);
        return lists;
    }

    private void nQueen(int row, int n) {
        if (row == n) {
            printList(n);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isOk(row, i, n)) {
                result[row] = i;
                nQueen(row + 1, n);
            }
        }
    }

    private void printList(int n) {
        ArrayList<String> temp2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (result[i] == j) {
                    stringBuilder.append("Q");
                } else {
                    stringBuilder.append(".");
                }
            }
            temp2.add(stringBuilder.toString());
        }
        lists.add(temp2);
    }

    private boolean isOk(int row, int column, int n) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int check = row - 1; check >= 0; check--) {
            if (result[check] == column) {
                return false;
            }

            if (leftUp >= 0 && result[check] == leftUp) {
                return false;
            }

            if (rightUp < n && result[check] == rightUp) {
                return false;
            }

            leftUp--;
            rightUp++;
        }
        return true;
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