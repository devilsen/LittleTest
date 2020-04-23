package com.wuba.acm.leetcode;

/**
 * desc :
 * date : 2020/4/22 10:04 AM
 *
 * @author : dongSen
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    static TreeNode obtain() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        return root;
    }
}