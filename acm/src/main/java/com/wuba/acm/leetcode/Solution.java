package com.wuba.acm.leetcode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * desc :
 * date : 2020/4/2 11:17 AM
 *
 * @author : dongSen
 */
public class Solution {

    static class MountainArray {
        int[] nums = {1, 2, 3, 4, 5, 2, 1};

        int get(int index) {
            return nums[index];
        }

        int length() {
            return nums.length;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int inMountainArray = solution.findInMountainArray(4, new MountainArray());
//        System.out.println(inMountainArray);

//        boolean happy = solution.isHappy(14);
//        System.out.println(happy);
//        ListNode listNode1 = ListNode.obtain(3);
//        ListNode listNode2 = ListNode.obtain(3);
//        ListNode listNode = solution.sortList(listNode1);
//        ListNode.print(listNode);

//        TreeNode root = TreeNode.obtainBST();
//        boolean validBST = solution.isValidBST(root);
//        System.out.println(validBST);

//        int[] days = {1, 4, 6, 7, 8, 20};
//        int[] costs = {2, 7, 15};
//        int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
//        int[] costs = {2, 7, 15};
//        int tickets = solution.mincostTickets(days, costs);
//        System.out.println(tickets);
//        for (int i = 0; i < days.length - 1; i++) {
//            int index = solution.findIndex(i, 1, days);
//            System.out.println(index);
//        }

//        TreeNode s = TreeNode.obtainBST();
//        TreeNode t = TreeNode.obtain(10, 7, 11);
//        boolean subtree = solution.isSubtree(s, t);
//        System.out.println(subtree);

//        char[][] obtain = Matrix.obtainChar();
//        int i = solution.maximalSquare(obtain);
//        System.out.println("square = " + i);
//        System.out.println(solution.mySqrt(9));

//        System.out.println(solution.myPow(2, 5));

        TreeNode root = TreeNode.obtain();
        List<List<Integer>> lists = solution.levelOrder2(root);
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> integers = lists.get(i);
            for (int j = 0; j < integers.size(); j++) {
                System.out.print(integers.get(j) + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> list = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentSize = queue.size();

            List<Integer> levelList = new ArrayList<>(currentSize);
            for (int i = 0; i < currentSize; i++) {
                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(levelList);
        }
        return list;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new LinkedList<>();
        dfs(list, root, 0);
        return list;
    }

    private void dfs(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) return;
        if (level >= list.size()) {
            list.add(new ArrayList<>());
        }
        list.get(level).add(root.val);
        dfs(list, root.left, level + 1);
        dfs(list, root.right, level + 1);
    }

    double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public int mySqrt(int x) {
        if (x == 0) return 0;
        long left = 0;
        long right = x;
        long mid;
        while (left < right) {
            mid = left + (right - left + 1) / 2;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return (int) left;
    }

    public int maximalSquare(char[][] matrix) {
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            char[] line = matrix[i];
            for (int j = 0; j < line.length; j++) {
                if (line[j] == '1') {
                    maxArea = Math.max(findMax(matrix, i, j), maxArea);
                }
            }
        }
        return maxArea;
    }

    private int findMax(char[][] matrix, int startI, int startJ) {
        int stage = 1;
        while (true) {
            boolean right = isRight(matrix[startI], startJ, startJ + stage);
            if (!right) {
                return stage * stage;
            }
            boolean down = isDown(matrix, startI, startI + stage, startJ + stage);
            if (!down) {
                return stage * stage;
            }
            boolean left = isLeft(matrix[startI + stage], startJ + stage, startJ);
            if (!left) {
                return stage * stage;
            }
            stage++;
        }
    }

    private boolean isLeft(char[] line, int startJ, int endJ) {
        if (endJ < 0) {
            return false;
        }
        for (int i = startJ; i >= endJ; i--) {
            if (line[i] == '0') {
                return false;
            }
        }
        return true;
    }

    private boolean isDown(char[][] matrix, int startI, int endI, int startJ) {
        if (endI > matrix.length - 1) {
            return false;
        }
        for (int i = startI; i <= endI; i++) {
            if (matrix[i][startJ] == '0') {
                return false;
            }
        }
        return true;
    }

    private boolean isRight(char[] line, int startJ, int endJ) {
        if (endJ > line.length - 1) {
            return false;
        }
        for (int i = startJ; i <= endJ; i++) {
            if (line[i] == '0') {
                return false;
            }
        }
        return true;
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        }
        if (s == null) {
            return false;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameTree(s, t);
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    int[] days, costs;
    Integer[] memo;
    int[] durations = new int[]{1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        memo = new Integer[days.length];
        return dp(0);
    }

    public int dp(int i) {
        if (i >= days.length) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        memo[i] = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < 3; ++k) {
            while (j < days.length && days[j] < days[i] + durations[k]) {
                j++;
            }
            memo[i] = Math.min(memo[i], dp(j) + costs[k]);
        }
        return memo[i];
    }

    public int mincostTickets1(int[] days, int[] costs) {
        if (days == null || days.length == 0) {
            return 0;
        }
        int index = 0;
        return Math.min(mincostTickets(index, days, costs),
                Math.min(costs[1] + mincostTickets(findIndex(index, 6, days), days, costs),
                        costs[2] + mincostTickets(findIndex(index, 29, days), days, costs)));

//        int a = mincostTickets(index, days, costs);
//        int b = costs[1] + mincostTickets(findIndex(index, 6, days), days, costs);
//        System.out.println("a : " + a + "  b: " + b);
//        int temp = Math.min(a, b);
//        System.out.println("aaa: " + temp);
//        return temp;
//        return costs[2] +mincostTickets(findIndex(index, 29, days), days, costs);
    }

    public int mincostTickets(int index, int[] days, int[] costs) {
        if (index > days.length - 1) {
            return 0;
        }

        return Math.min(costs[0] + mincostTickets(++index, days, costs),
                Math.min(costs[1] + mincostTickets(findIndex(index, 6, days), days, costs),
                        costs[2] + mincostTickets(findIndex(index, 29, days), days, costs)));

//        int i = costs[0] + mincostTickets(++index, days, costs);
//        int index1 = findIndex(index, 6, days);
//        int j = costs[1] + mincostTickets(index1, days, costs);
//        if (i == 11) {
//            System.out.println("--------- " + index1);
//        }
//        System.out.println("i : " + i + "  j: " + j);
//        int temp = Math.min(i, j);
//        System.out.println("bbb: " + temp);
//        return temp;
//        return costs[2] +mincostTickets(findIndex(index, 29, days), days, costs);
    }

    private int findIndex(int index, int day, int[] days) {
        if (index > days.length - 1) {
            return days.length;
        }

        int start = days[index];
        int end = start + day;
        for (int i = index; i < days.length; i++) {
            if (days[i] > end) {
                return i - 1;
            } else if (days[i] == end) {
                return i;
            }
        }
        return days.length;
    }

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
//            System.out.println(root.val);
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!helper(node.right, val, upper)) return false;
        if (!helper(node.left, lower, val)) return false;
        return true;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null; // 掐断链表，不然会死循环
        // 得到两个排序好的链表
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        // 合并两个链表
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left == null ? right : left;
        return res.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            if (l1 != null) {
                return l1;
            } else {
                return l2;
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;

        while (true) {
            if (l1 == null) {
                tail.next = l2;
                break;
            }
            if (l2 == null) {
                tail.next = l1;
                break;
            }
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        return head.next;
    }

    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        slow = addNum(slow);
        fast = addNum(fast);
        fast = addNum(fast);

        while (slow != fast) {
            slow = addNum(slow);
            fast = addNum(fast);
            fast = addNum(fast);
        }
        return slow == 1;
    }

    private int addNum(int n) {
        int result = 0;
        while (n != 0) {
            int temp = n % 10;
            result = result + temp * temp;
            n = n / 10;
        }
        return result;
    }

    private boolean isHappy(int n, Map<Integer, Boolean> integers) {
        int num = addNum(n);
        if (num == 1) {
            return true;
        } else if (integers.get(num) != null) {
            return false;
        } else {
            return isHappy(num);
        }
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int maxIndex = findMaxIndex(mountainArr);

        int leftIndex = findLeft(target, 0, maxIndex, mountainArr);
        if (leftIndex != -1) {
            return leftIndex;
        }
        return findRight(target, maxIndex + 1, mountainArr.length() - 1, mountainArr);
    }

    private int findRight(int target, int left, int right, MountainArray mountainArr) {
        while (left <= right) {
            int middleIndex = left + (right - left) / 2;
            int middleNum = mountainArr.get(middleIndex);
            if (middleNum == target) {
                return middleIndex;
            } else if (middleNum >= target) {
                left = middleIndex + 1;
            } else {
                right = middleIndex - 1;
            }
        }
        return -1;
    }

    private int findLeft(int target, int left, int right, MountainArray mountainArr) {
        while (left <= right) {
            int middleIndex = left + (right - left) / 2;
            int middleNum = mountainArr.get(middleIndex);
            if (middleNum == target) {
                return middleIndex;
            } else if (middleNum >= target) {
                right = middleIndex - 1;
            } else {
                left = middleIndex + 1;
            }
        }
        return -1;
    }

    private int findMaxIndex(MountainArray mountainArr) {
        int left = 0;
        int right = mountainArr.length() - 1;

        while (left <= right) {
            int middleIndex = left + (right - left) / 2;
            int middleNum = mountainArr.get(middleIndex);
            int middleAdd;
            if (middleIndex + 1 > right) {
                return middleIndex;
            } else {
                middleAdd = mountainArr.get(middleIndex + 1);
            }
            int middleSub;
            if (middleIndex - 1 < 0) {
                middleSub = middleNum;
            } else {
                middleSub = mountainArr.get(middleIndex - 1);
            }

            if (middleNum > middleAdd && middleNum > middleSub) {
                return middleIndex;
            } else if (middleNum > middleAdd) {
                right = middleIndex - 1;
            } else if (middleNum < middleAdd) {
                left = middleIndex + 1;
            }
        }
        return left + (right - left) / 2;
    }

}