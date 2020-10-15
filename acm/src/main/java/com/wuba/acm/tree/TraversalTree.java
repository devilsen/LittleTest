package com.wuba.acm.tree;

import java.util.LinkedList;
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
        traversalTree.preOrderStack2(root);
        System.out.println();
        System.out.println();

        traversalTree.inOrder(root);
        System.out.println();
        traversalTree.inOrderStack(root);
        System.out.println();
        System.out.println();

        traversalTree.postOrder(root);
        System.out.println();
        traversalTree.postOrderStack(root);
        System.out.println();
        System.out.println();

        traversalTree.level(root);

    }

    public void preOrder(TreeNode node) {
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

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }

    private void preOrderStack(TreeNode root) {
        if (root == null) return; //如果为空树则返回

        Stack<TreeNode> treeStack = new Stack<>();
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

    private void preOrderStack2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            // 把所有的左节点都先打印出来
            while (treeNode != null) {
                System.out.print(treeNode.val + " ");
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
    }

    private void inOrderStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }

            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                System.out.print(treeNode.val + " ");
                treeNode = treeNode.right;
            }
        }
    }

    private void inOrderStack2(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null && !stack.isEmpty()){
            while (node != null){
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()){
                TreeNode pop = stack.pop();
                System.out.println(pop.val);
                node = pop.right;
            }
        }
    }

    private void postOrderStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode treeNode = root;
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            treeNode = stack.pop();
            stack2.push(treeNode);

            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
        }
        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            System.out.print(node.val + " ");
        }
    }

    private void level(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.pop();
            System.out.print(treeNode.val + " ");
            if (treeNode.left != null) queue.add(treeNode.left);
            if (treeNode.right != null) queue.add(treeNode.right);
        }

    }

}
