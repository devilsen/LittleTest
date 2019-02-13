package com.wuba.acm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc :
 * date : 2018/12/10
 *
 * @author : dongSen
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

//        ListNode listNode = new ListNode(1);
//        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);

//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
//        int[][] matrix = {
//                {1, 2, 3, 4, 5, 6},
//                {7, 8, 9, 10, 11, 12},
//                {13, 14, 15, 16, 17, 18},
//                {19, 20, 21, 22, 23, 24},
//                {25, 26, 27, 28, 29, 30},
//                {31, 32, 33, 34, 35, 36}
//        };

//        solution.rotate(matrix);

//        String[] a = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
//        List<List<String>> lists = solution.groupAnagrams(a);
//
//        for (List<String> b : lists) {
//            for (String c : b) {
//                System.out.print(c + "  ");
//            }
//            System.out.println();
//        }
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int v = solution.maxSubArray(a);
        System.out.println(v);

    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num: nums) {
            sum += num;
            max = Math.max(max, sum);
            if (sum < 0) sum = 0;
        }
        return max;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String code = getCode(str);
            List<String> strings = map.get(code);
            if (strings == null) {
                strings = new ArrayList<>();
                strings.add(str);
                map.put(code, strings);
            } else {
                strings.add(str);
            }
        }

        return new ArrayList<>(map.values());
    }

    private String getCode(String s) {
        char[] ca = s.toCharArray();
        Arrays.sort(ca);
        return String.valueOf(ca);
    }

    public void rotate(int[][] matrix) {
        int x = 0;
        int y = 0;
        int n = matrix[0].length - 1;
        int time = n;
        for (int j = 0; j < matrix[0].length / 2; j++) {
            for (int i = 0; i < time; i++) {
                int temp1 = matrix[x][y + i];
                int temp2 = matrix[x + i][n];
                int temp3 = matrix[n][n - i];
                int temp4 = matrix[n - i][y];

                matrix[x][y + i] = temp4;
                matrix[x + i][n] = temp1;
                matrix[n][n - i] = temp2;
                matrix[n - i][y] = temp3;

            }
            x++;
            y++;
            n--;
            time = time - 2;
        }

    }

    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> lists = new ArrayList<>();

        boolean[] used = new boolean[nums.length];

        dfs(nums, used, new ArrayList<Integer>(), lists);
        return lists;
    }


    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
//            if (used[i]) continue;
//            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
            used[i] = true;
            list.add(nums[i]);
            dfs(nums, used, list, res);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    private void printList(String name, List<Integer> cur) {
        System.out.println(name);

        for (Integer i : cur) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2))
            return "0";

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

        int[][] bList = new int[numArray1.length][length];
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
                } else {
                    last = 0;
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
                    last = add / 10;
                } else {
                    last = 0;
                }
                cList[j] = add % 10;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int aCList : cList) {
            stringBuilder.append(aCList);
        }

        String result = stringBuilder.toString();
        if (result.startsWith("0")) {
            result = result.substring(1);
        }

        return result;
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