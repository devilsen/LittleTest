package com.wuba.acm.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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

//        int[] nums = {1, 2};

//        ListNode node = ListNode.obtain(3);
//        ListNode.print(node);
//        ListNode reverse = ListNode.reverse(node);
//        ListNode.print(reverse);

//        solution.addTwoNumbers(nums);
//        System.out.println(Arrays.toString(nums));

        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
//        ListNode listNode = solution.addTwoNumbers(l1, l2);
//        ListNode listNode = solution.addTwoNumbers(ListNode.obtain(5), ListNode.obtain(5));
//        ListNode.print(listNode);

//        int[][] directions = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
//
//        solution.updateMatrix(directions);

//        int[][] intervals = {{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
//        int[][] intervals = {{2, 3}, {4, 5}, {1, 10}};
//        int[][] intervals = {{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}};
//        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] intervals = {{1, 4}, {0, 0}};
//        int[][] merge = solution.merge(intervals);
//        for (int[] i : merge) {
//            System.out.print(Arrays.toString(i) + " ");
//        }

//        int[] source = {3, 2, 1, 0, 4};
//        solution.canJump(source);

//        char[][] intervals = {{'1', '1', '0', '0', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '0', '0'},
//                {'0', '0', '0', '1', '1'}};
//        int numIslands = solution.numIslands(intervals);
//        System.out.println(numIslands);

//        List<Integer> integers = solution.rightSideView(TreeNode.obtain());
//        System.out.println(integers.toString());

//        int[] nums = {7, 5, 6, 4};
//        int i = solution.reversePairs(nums);
//        System.out.println(i);

        ListNode[] listNodes = new ListNode[2];
        listNodes[0] = ListNode.obtain(3);
        listNodes[1] = ListNode.obtain(3);
//        listNodes[2] = ListNode.obtain(3);
        ListNode node = solution.mergeKLists(listNodes);
        ListNode.print(node);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                queue.offer(lists[i]);
        }

        ListNode tail = new ListNode(0);
        ListNode head = tail;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }

        return head.next;
    }

    private ListNode mergeTwoList(ListNode list1, ListNode list2) {
        if (list1 != null || list2 != null) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
        }

        ListNode head = new ListNode(0);
        ListNode tail = head;
        ListNode aPtr = list1;
        ListNode bPtr = list2;

        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        if (aPtr != null) {
            tail.next = aPtr;
        } else {
            tail.next = bPtr;
        }
        return head.next;
    }

    public int waysToChange(int n) {
        int[] f = new int[n + 1];
        int[] coins = {1, 5, 10, 25};

        f[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                f[i] = (f[i] + f[i - coin]) % 1000000007;
                System.out.println("f[" + i + "] = " + f[i] + " f[" + i + " - " + coin + "] = " + f[i - coin]);
            }
        }
        return f[n];
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        dfs(result, root, 0);
        return result;
    }

    private void dfs(List<Integer> result, TreeNode root, int n) {
        if (root == null) {
            return;
        }
        if (result.size() == n) {
            result.add(root.val);
        }
        dfs(result, root.right, n + 1);
        dfs(result, root.left, n + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int island = 0;
        int height = grid.length;
        int width = grid[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '1') {
                    findBorder(grid, i, j);
                    island++;
                }
            }
        }
        return island;
    }

    private void findBorder(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        findBorder(grid, i + 1, j);
        findBorder(grid, i, j + 1);
        findBorder(grid, i - 1, j);
        findBorder(grid, i, j - 1);
    }


    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && nums[j] >= i - j) {
                    dp[i] = true;
                }
            }
        }
        return dp[nums.length - 1];
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[][]{};

        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);

        LinkedList<int[]> merge = new LinkedList<>();
        for (int[] node : intervals) {
            int left = node[0];
            int right = node[1];

            int[] last = merge.peekLast();
            if (merge.size() == 0 || last[1] < left) {
                merge.addLast(node);
            } else {
                last[1] = Math.max(right, last[1]);
            }
        }

        return merge.toArray(new int[merge.size()][2]);
//        int[][] result = new int[ints.size()][2];
//        return ints.toArray(result);
    }

    /**
     * DP（两次遍历，可 AC）
     */
    public int[][] updateMatrix_1(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 第一次遍历，正向遍历，根据相邻左元素和上元素得出当前元素的对应结果
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = row + col;
                }
                if (i > 0) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 1][j] + 1);
                }
                if (j > 0) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j - 1] + 1);
                }
            }
        }
        // 第二次遍历，反向遍历，根据相邻右元素和下元素及当前元素的结果得出最终结果
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i < row - 1) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i + 1][j] + 1);
                }
                if (j < col - 1) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j + 1] + 1);
                }
            }
        }
        return matrix;
    }


    public int[][] updateMatrix1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] res = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        // 初始化队列
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            int x = top[0];
            int y = top[1];
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (newX < 0 || newX >= n || newY < 0 || newY >= m || visited[newX][newY]) {
                    continue;
                }
                res[newX][newY] = res[x][y] + 1;
                visited[newX][newY] = true;
                queue.add(new int[]{newX, newY});
            }
        }

        return res;
    }


    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return matrix;
        int n = matrix.length, m = matrix[0].length;
        int[][] res = new int[n][m];
        // 标记当前位置是否都看过
        boolean[][] visited = new boolean[n][m];
        // BFS 队列
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 将 0 全部放入队列
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int[][] directions = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            int x = top[0], y = top[1];
            // BFS 搜索四个方向
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (newX < 0 || newX >= n || newY < 0 || newY >= m || visited[newX][newY]) continue;
                res[newX][newY] = res[x][y] + 1;  // 距离更新
                visited[newX][newY] = true;
                queue.add(new int[]{newX, newY});   // 新元素入队
            }
        }
        return res;
    }
}