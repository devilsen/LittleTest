package com.wuba.acm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 按层遍历二叉树  102
 * 题目：https://leetcode.com/problems/binary-tree-level-order-traversal/
 * 解法：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/tao-mo-ban-bfs-he-dfs-du-ke-yi-jie-jue-by-fuxuemin/
 *
 * @author dongsen
 * date: 2019/02/13 0013.
 */
public class LevelOrderTraversal {

    public static void main(String[] args) {
        LevelOrderTraversal traversal = new LevelOrderTraversal();

        List<List<Integer>> lists = traversal.levelOrder(TreeMaker.obtain());
        List<List<Integer>> lists2 = traversal.levelOrder3(TreeMaker.obtain());

        for (List<Integer> list : lists) {
            for (int val : list) {
                System.out.print(val);
            }
            System.out.println();
        }


        List<List<Integer>> lists3 = traversal.zigzagLevelOrder(TreeMaker.obtain());
        for (List<Integer> list : lists2) {
            for (int val : list) {
                System.out.print(val);
            }
            System.out.println();
        }

    }

    /**
     * BFS
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> wrapList = new LinkedList<>();

        if (root == null) return wrapList;

        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode == null) break;
                if (treeNode.left != null) queue.offer(treeNode.left);
                if (treeNode.right != null) queue.offer(treeNode.right);
                subList.add(treeNode.val);
            }
            wrapList.add(subList);
        }
        return wrapList;
    }

    /**
     * BFS 代码优化
     */
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

    /**
     * DFS
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
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

    /**
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/submissions/
     * 之字型打印二叉树
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>(0);

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> lists = new LinkedList<>();
        boolean dir = true;

        while (!queue.isEmpty()) {
            lists.add(nodeToInt(queue, dir));
            dir = !dir;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode == null) break;

                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }

                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
        }
        return lists;
    }

    private List<Integer> nodeToInt(LinkedList<TreeNode> nodes, boolean dir) {
        List<Integer> list = new ArrayList<>();
        if (dir) {
            for (int i = 0; i < nodes.size(); i++) {
                list.add(nodes.get(i).val);
            }
        } else {
            for (int i = nodes.size() - 1; i >= 0; i--) {
                list.add(nodes.get(i).val);
            }
        }
        return list;
    }
}
