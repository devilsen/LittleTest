package com.wuba.acm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 按层遍历二叉树  102
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * @author dongsen
 * date: 2019/02/13 0013.
 */
public class LevelOrderTraversal {

    public static void main(String[] args) {
        LevelOrderTraversal traversal = new LevelOrderTraversal();

        List<List<Integer>> lists = traversal.levelOrder2(TreeMaker.obtain());

        for (List<Integer> list : lists) {
            for (int val : list) {
                System.out.print(val);
            }
            System.out.println();
        }

        List<List<Integer>> lists2 = traversal.zigzagLevelOrder(TreeMaker.obtain());
        for (List<Integer> list : lists2) {
            for (int val : list) {
                System.out.print(val);
            }
            System.out.println();
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>(0);

        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        Queue<TreeNode> curLevelNodes = new LinkedList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curLevelNodes.offer(node);

            if (queue.isEmpty()) {
                List<Integer> list = new ArrayList<>(curLevelNodes.size());
                while (!curLevelNodes.isEmpty()) {
                    TreeNode curNode = curLevelNodes.poll();
                    list.add(curNode.val);

                    if (curNode.left != null) {
                        queue.offer(curNode.left);
                    }

                    if (curNode.right != null) {
                        queue.offer(curNode.right);
                    }
                }
                result.add(list);
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
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
