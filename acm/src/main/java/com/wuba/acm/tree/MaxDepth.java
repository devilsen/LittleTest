package com.wuba.acm.tree;

/**
 * 二叉树的最大深度   104
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * @author dongsen
 * date: 2019/02/13 0013.
 */
public class MaxDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode childLeft = root.left = new TreeNode(2);
        TreeNode childRight = root.right = new TreeNode(3);
        childLeft.left = new TreeNode(4);

        MaxDepth maxDepth = new MaxDepth();
        int max = maxDepth.maxDepth(root);

        System.out.println("max depth: " + max);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


}
