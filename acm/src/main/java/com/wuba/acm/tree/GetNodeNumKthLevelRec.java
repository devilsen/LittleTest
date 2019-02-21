package com.wuba.acm.tree;

/**
 * desc : 求二叉树第k层的节点个数
 * date : 2019/2/15
 *
 * @author : dongSen
 */
public class GetNodeNumKthLevelRec {

    public int getNodeNumKthLevelRec(TreeNode node, int k) {
        if (node == null || k < 1) {
            return 0;
        }

        if (k == 1)
            return 1;

        return getNodeNumKthLevelRec(node.left, k - 1) + getNodeNumKthLevelRec(node.right, k - 1);

    }

}
