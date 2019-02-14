package com.wuba.acm.tree;

/**
 * desc : 判断一个二叉树是否平衡
 * https://leetcode.com/problems/balanced-binary-tree/
 * date : 2019/2/14
 *
 * @author : dongSen
 */
public class IsBalancedTree {

    private boolean result = true;

    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        if (Math.abs(l - r) > 1)
            result = false;
        return 1 + Math.max(l, r);
    }





    public boolean isBalanced2(TreeNode root) {
        if (root != null) {
            if (Math.abs(getDepth(root.left, 0) - getDepth(root.right, 0)) > 1)
                return false;

            return isBalanced2(root.right) && isBalanced2(root.left);
        }
        return true;
    }

    private int getDepth(TreeNode node, int depth) {
        if (node == null) return depth;

        return Math.max(getDepth(node.left, depth + 1), getDepth(node.right, depth + 1));
    }
}
