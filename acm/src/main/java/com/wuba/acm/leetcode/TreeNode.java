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


    /**
     *           1
     *         /  \
     *        2    3
     *         \    \
     *          5    4
     */
    static TreeNode obtain() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        return root;
    }

    /**
     *           5
     *         /  \
     *        1   10
     *           /  \
     *          7   11
     */
    static TreeNode obtainBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(10);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(11);
        return root;
    }

    /**
     *           p
     *         /  \
     *        l    r
     */
    static TreeNode obtain(int parent, int left, int right) {
        TreeNode root = new TreeNode(parent);
        root.left = new TreeNode(left);
        root.right = new TreeNode(right);
        return root;
    }
}