package com.wuba.acm.tree;

import java.util.List;
import java.util.Stack;

/**
 * desc : 翻转二叉树（输出二叉树的镜像）
 * https://mp.weixin.qq.com/s/ONKJyusGCIE2ctwT9uLv9g
 * date : 2019/2/14
 *
 * @author : dongSen
 */
public class ReverseTree {

    public static void main(String[] args) {
        LevelOrderTraversal traversal = new LevelOrderTraversal();

        TreeNode root = TreeMaker.obtain();
        List<List<Integer>> lists = traversal.levelOrder(root);
        printTree(lists);

        ReverseTree reverseTree = new ReverseTree();
        reverseTree.reverse2(root);
        List<List<Integer>> lists2 = traversal.levelOrder(root);
        printTree(lists2);

    }

    private void reverse(TreeNode node) {
        if (node == null)
            return;

        swapNode(node);
        reverse(node.left);
        reverse(node.right);
    }

    private TreeNode reverse2(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || stack.size() > 0) {
            while (node != null) {
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;

                stack.push(node);
                node = node.left;
            }
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.right;
            }
        }

        return root;
    }

    private void swapNode(TreeNode node) {
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    private static void printTree(List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            for (int val : list) {
                System.out.print(val);
            }
            System.out.println();
        }
    }
}
