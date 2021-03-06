package com.wuba.acm.leetcode;

import android.view.View;

import com.wuba.acm.tree.TreeMaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

//        ListNode listNode = ListNode.obtainCycleList(2);
//        solution.detectCycle(listNode);
//        System.out.println(listNode.val);

//        int[] nums = {4, 5, 0, -2, -3, 1};
//        int i = solution.subarraysDivByK(nums, 5);
//        System.out.println(i);
//        String decodeString = solution.decodeString("3[a]2[bc]");
//        System.out.println(decodeString);

//        int[] candies = {2, 3, 5, 1, 3};
//        List<Boolean> booleans = solution.kidsWithCandies(candies, 3);
//        System.out.println(booleans.toString());

//        int i = solution.sumNums(9);
//        System.out.println(i);

//        int[] nums = {1, 2, 3, 4};
//        int[] ints = solution.productExceptSelf(nums);
//        System.out.println(Arrays.toString(ints));
//        int[][] nums = {
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12},
//                {13, 14, 15, 16},
//        };
//        int[][] nums = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9},
//        };
//        int[] ints = solution.spiralOrder(nums);
//        System.out.println(Arrays.toString(ints));

//        int num = solution.translateNum(506);
//        System.out.println(num);
//        System.out.println(solution.isPalindrome(0110));
//        int[] t = {73, 74, 75, 71, 69, 72, 76, 73};
//        int[] ints = solution.dailyTemperatures(t);
//        System.out.println(Arrays.toString(ints));
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        List<List<Integer>> lists = solution.threeSum(nums);
//        System.out.println(lists.toString());

//        String[] s = {"flower","f","f2"};
//        System.out.println(solution.longestCommonPrefix(s));

//        int[] nums = {8, 1, 5, 2, 6};
//        System.out.println(solution.maxScoreSightseeingPair(nums));

        System.out.println(solution.printFromTopToBottom(TreeNode.obtain()));
    }

    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.peek();
            list.add(temp.val);
            if (temp.left != null) queue.offer(temp.left);
            if (temp.right != null) queue.offer(temp.right);
            queue.poll();
        }
        return list;
    }

    public int maxScoreSightseeingPair(int[] A) {
        int max = A[0];
        int ans = 0;
        for (int j = 1; j < A.length; j++) {
            ans = Math.max(ans, max + A[j] - j);
            max = Math.max(max, A[j] + j);
        }
        return ans;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = "";
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                prefix = strs[i];
            } else {
                prefix = longestCommonPrefix(strs[i], prefix);
            }
            if (prefix.length() == 0) {
                return "";
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String strs, String prefix) {
        int length = Math.min(strs.length(), prefix.length());
        for (int i = 0; i < length; i++) {
            if (strs.charAt(i) != prefix.charAt(i)) {
                return prefix.substring(0, i);
            }
        }
        return prefix.substring(0, length);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int num = nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = num + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(num, nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        if (T.length == 0) return result;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x = x / 10;
        }
        return x == reverseNum || x == reverseNum / 10;
    }

    public int translateNum(int num) {
        String numString = String.valueOf(num);
        return translateNum(numString);
    }

    public int translateNum(String num) {
        if (num.length() == 0) {
            return 0;
        }
        if (num.length() == 1) {
            return 1;
        }
        if (num.length() == 2) {
            if (Integer.parseInt(num) < 26) {
                int substring1 = Integer.parseInt(num.substring(0, 1));
                if (substring1 == 0) {
                    return 1;
                }
                return 2;
            } else {
                return 1;
            }
        }
        int substring2 = Integer.parseInt(num.substring(0, 2));
        if (substring2 >= 10 && substring2 <= 25) {
            return translateNum(num.substring(1)) + translateNum(num.substring(2));
        } else {
            return translateNum(num.substring(1));
        }
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null) return new int[]{};
        int right = 0, down = 1, left = 2, top = 3;

        int row = matrix.length;
        int col = matrix[0].length;
        int[] result = new int[row * col];

        int topBoundary = 1;
        int rightBoundary = col - 1;
        int bottomBoundary = row - 1;
        int leftBoundary = 0;

        int x = 0;
        int y = 0;
        int direction = right;
        int index = 0;

        while (true) {
            int num = matrix[x][y];
            result[index++] = num;
            switch (direction) {
                case 0:// 向右
                    if (y < rightBoundary) {
                        y++;
                    } else {
                        rightBoundary--;
                        x++;
                        direction = down;
                    }
                    if (x > bottomBoundary) {
                        return result;
                    }
                    break;
                case 1: // 向下
                    if (x < bottomBoundary) {
                        x++;
                    } else {
                        bottomBoundary--;
                        y--;
                        direction = left;
                    }
                    if (y < leftBoundary) {
                        return result;
                    }
                    break;
                case 2:// 向左
                    if (y > leftBoundary) {
                        y--;
                    } else {
                        leftBoundary++;
                        x--;
                        direction = top;
                    }
                    if (x < topBoundary) {
                        return result;
                    }
                    break;
                case 3:// 向上
                    if (x > topBoundary) {
                        x--;
                    } else {
                        topBoundary++;
                        y++;
                        direction = right;
                    }
                    if (y > rightBoundary) {
                        return result;
                    }
                    break;
            }
            if (x > bottomBoundary && x < topBoundary && y > rightBoundary && y < leftBoundary) {
                break;
            }
        }
        return result;
    }

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = k;
            k *= nums[i];
        }
        k = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= k;
            k *= nums[i];
        }

        return res;
    }

    public int sumNums(int n) {
        return sunNums(n, 0);
    }

    private int sunNums(int n, int result) {
        if (n == 1) {
            return result + 1;
        }
        result += n;
        return sunNums(n - 1, result);
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int i = 0; i < candies.length; i++) {
            max = Math.max(max, candies[i]);
        }
        ArrayList<Boolean> list = new ArrayList<>(candies.length);
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) {
                list.add(true);
            } else {
                list.add(false);
            }
        }
        return list;
    }

    public String decodeString(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String split = comb(s, i, "");
            stringBuilder.append(split);
            i = lastIndex;
        }

        return stringBuilder.toString();
    }

    private int lastIndex;

    private String comb(String s, int index, String preS) {
        StringBuilder currentS = new StringBuilder();
        int num = -1;
        for (int i = index; i < s.length(); i++) {
            char c = s.charAt(i);
            // 处理数字
            if (c >= '0' && c <= '9') {
                if (num != -1) {
                    num = num * 10;
                } else {
                    num = c - '0';
                    continue;
                }
                num += c - '0';
                continue;
            }
            // 处理[
            if (c == '[') {
                String temp = comb(s, i + 1, currentS.toString());
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    stringBuilder.append(temp);
                }
                currentS.append(stringBuilder);
                i = lastIndex;
                num = -1;
                continue;
            }
            if (c == ']') {
                lastIndex = i;
                return currentS.toString();
            }
            lastIndex = i;
            currentS.append(c);
        }
        return currentS.toString();
    }

    public int subarraysDivByK(int[] A, int K) {
        int count = 0;
        int preSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : A) {
            preSum = (preSum + num) % K;
            if (preSum < 0) preSum += K;

            Integer value = map.get(preSum);
            if (value != null) {
                count += value;
            }

            if (value == null) {
                value = 1;
                map.put(preSum, value);
            } else {
                value++;
                map.put(preSum, value);
            }
        }

        return count;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        if (fast == null) return null;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                return null;
            }
        }
        ListNode p1 = head;
        ListNode p2 = slow;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public int findDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int cnt;
        int mid;
        while (left <= right) {
            cnt = 0;
            mid = left + (right - left) / 2;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt > mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        if (preorder.length != inorder.length) return null;

        TreeNode root = new TreeNode(preorder[0]);
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                index = i;
                break;
            }
        }
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1),
                Arrays.copyOfRange(inorder, 0, index));
        root.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length),
                Arrays.copyOfRange(inorder, index + 1, inorder.length));
        return root;
    }

    public static void levelTravel2(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            System.out.print(temp.val + " ");
            if (temp.left != null) q.add(temp.left);
            if (temp.right != null) q.add(temp.right);
        }
        System.out.println();
    }

    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0);
    }

    public boolean validPalindrome(String s, int count) {
        if (s.length() == 0) return true;
        int right = s.length() - 1;
        int left = 0;
        while (left <= right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if (l == r) {
                left++;
                right--;
            } else {
                count++;
                if (count > 1) {
                    return false;
                }
                return validPalindrome(s.substring(++left, right + 1), count) || validPalindrome(s.substring(--left, right), count);
            }
        }
        return count <= 1;
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int imax = 1;
        int imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(imax, max);
        }
        return max;
    }

    private int center(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
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

    static class LRUCache extends LinkedHashMap<Integer, Integer> {

        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 1, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer value = super.get(key);
            return value == null ? -1 : value;
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

}