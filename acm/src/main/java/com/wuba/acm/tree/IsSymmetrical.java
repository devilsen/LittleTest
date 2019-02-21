package com.wuba.acm.tree;

/**
 * desc : 二叉树是否对称
 * date : 2019/2/15
 *
 * @author : dongSen
 */
public class IsSymmetrical {

    private boolean isSymmetrical(TreeNode root) {
        if (root == null)
            return true;

        return isSymmetricalTree(root.left, root.right);
    }

    private boolean isSymmetricalTree(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        }

        if (right == null) {
            return false;
        }

        if (right.val != left.val) {
            return false;
        }
        return isSymmetricalTree(left.right, right.left) && isSymmetricalTree(left.left, right.right);
    }


}
