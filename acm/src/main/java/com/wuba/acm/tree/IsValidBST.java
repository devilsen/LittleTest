package com.wuba.acm.tree;

import java.util.Stack;

/**
 * desc : https://leetcode-cn.com/problems/validate-binary-search-tree/
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * date : 2020/5/5 11:57 AM
 *
 * @author : dongSen
 */
public class IsValidBST {

    // 方法1 逐个判断，同样是O(n)但是效率比方法2高
    public boolean isValidBST1(TreeNode root) {
        return he(root, null, null);
    }

    private boolean he(TreeNode root, Integer lower, Integer upper) {
        if (root == null) return true;
        int val = root.val;
        if (lower != null && lower >= val) return false;
        if (upper != null && upper <= val) return false;
        if (!he(root.left, lower, val)) return false;
        if (!he(root.right, val, upper)) return false;
        return true;
    }

    // 方法2 中序遍历
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
//            System.out.println(root.val);
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

}
