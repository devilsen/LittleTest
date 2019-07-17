package com.wuba.acm.stack;

import java.util.Stack;

/**
 * desc : 栈的压入、弹出序列
 * date : 2019/3/11
 *
 * @author : dongSen
 * <p>
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列 1，2，3，4，5 是某栈的压入顺序，
 * 序列 4，5，3，2，1是该压栈序列对应的一个弹出序列，
 * 但4，3，5，1，2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class IsStackOrder {

    public static void main(String[] args) {
        int[] pushSequence = new int[]{1, 2, 3, 4, 5};
        int[] popSequence = new int[]{4, 5, 3, 2, 1};

        IsStackOrder isStackOrder = new IsStackOrder();
        boolean popOrder = isStackOrder.isPopOrder(pushSequence, popSequence);
        System.out.println(popOrder);
    }

    public boolean isPopOrder(int[] pushSequence, int[] popSequence) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushSequence.length; i++) {
            int top = popSequence[j];
            if (pushSequence[i] != top) {
                stack.push(pushSequence[i]);
            } else {
                j++;
            }
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != popSequence[j]) {
                return false;
            } else {
                j++;
            }
        }

        return true;
    }

}
