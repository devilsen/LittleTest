package com.wuba.acm.tree;

/**
 * desc : 构造一个简单二叉树
 * date : 2019/2/14
 *
 * @author : dongSen
 */
public class TreeMaker {

    /**
     *               1
     *          2         3
     *       4    5    6     7
     */
    public static TreeNode obtain() {
        TreeNode root = new TreeNode(1);
        TreeNode childLeft = root.left = new TreeNode(2);
        TreeNode childRight = root.right = new TreeNode(3);
        childLeft.left = new TreeNode(4);
        childLeft.right = new TreeNode(5);
        childRight.left = new TreeNode(6);
        childRight.right = new TreeNode(7);

        return root;
    }

}
