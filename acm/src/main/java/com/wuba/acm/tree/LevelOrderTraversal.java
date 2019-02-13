package com.wuba.acm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 按层遍历二叉树  102
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * @author dongsen
 * date: 2019/02/13 0013.
 */
public class LevelOrderTraversal {

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
}
