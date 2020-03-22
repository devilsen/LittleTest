package com.wuba.acm.tree;

import java.util.Arrays;

/**
 * desc : 根据二叉树的先序遍历和中序遍历，构建二叉树
 * date : 2019/2/15
 *
 * @author : dongSen
 * <p>
 * 实际上这也是一个拆分的过程，由前序遍历找到根节点，根节点通过中序遍历找到左右子树（左右子树的结点再通过更小子树的父节点得到），这就完成了一个TreeNode的构建
 * 之后递归，找到更小子树的序列，通过这个序列再次构建，直到构建完成。
 */
public class ReConstructBinaryTree {

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        ReConstructBinaryTree reConstructBinaryTree = new ReConstructBinaryTree();
        TreeNode root = reConstructBinaryTree.reConstructBinaryTree(pre, in);

        TraversalTree traversalTree = new TraversalTree();
        traversalTree.preOrder(root);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length == 0 || in.length == 0) {
            return null;
        }
        if (pre.length != in.length) {
            return null;
        }

        TreeNode node = new TreeNode(pre[0]);
        for (int i = 0; i < pre.length; i++) {
            if (pre[0] == in[i]) {
                node.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                node.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return node;
    }

    public TreeNode reConstructBinaryTree1(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn)
            return null;

        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
            }
        }
        return root;
    }

}
