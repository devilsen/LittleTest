package com.wuba.acm.tree;

import java.util.Stack;

/**
 * desc : 遍历二叉树
 * date : 2019/2/14
 *
 * @author : dongSen
 * <p>
 * 前序遍历的递推公式：
 * preOrder(r) = print r->preOrder(r->left)->preOrder(r->right)
 * <p>
 * 中序遍历的递推公式：
 * inOrder(r) = inOrder(r->left)->print r->inOrder(r->right)
 * <p>
 * 后序遍历的递推公式：
 * postOrder(r) = postOrder(r->left)->postOrder(r->right)->print r
 * <p>
 * 层遍历二叉树{@link LevelOrderTraversal}
 */
public class TraversalTree {

    public static void main(String[] args) {
        TreeNode root = TreeMaker.obtain();

        TraversalTree traversalTree = new TraversalTree();
        traversalTree.preOrder(root);
        System.out.println();
        traversalTree.preOrderStack(root);
        System.out.println();

        traversalTree.inOrder(root);
        System.out.println();

        traversalTree.postOrder(root);
        System.out.println();
    }

    private void preOrder(TreeNode node) {
        if (node == null)
            return;

        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    private void inOrder(TreeNode node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    private void postOrder(TreeNode node) {
        if (node == null)
            return;

        inOrder(node.left);
        inOrder(node.right);
        System.out.print(node.val + " ");
    }

    private void preOrderStack(TreeNode root) {
        Stack<TreeNode> treeStack = new Stack<>();
        if (root == null) //如果为空树则返回
            return;
        treeStack.push(root);
        while (!treeStack.isEmpty()) {
            TreeNode tempNode = treeStack.pop();
            if (tempNode != null) {
                System.out.print(tempNode.val + " ");//访问根节点
                treeStack.push(tempNode.right); //入栈右孩子
                treeStack.push(tempNode.left);//入栈左孩子
            }
        }
    }

}
