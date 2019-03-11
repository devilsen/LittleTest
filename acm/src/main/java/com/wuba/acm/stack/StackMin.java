package com.wuba.acm.stack;

import java.util.Stack;

/**
 * desc : 包含 min 函数的栈
 * date : 2019/3/11
 *
 * @author : dongSen
 * <p>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的 min 函数。
 */
public class StackMin {

    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> min = new Stack<>();

    public static void main(String[] args) {

    }

    public void push(int node) {
        data.push(node);
        if (data.isEmpty() || node < data.peek()) {
            min.push(node);
        }
    }

    public void pop() {
        Integer pop = data.pop();
        if (pop.intValue() == min.peek()) {
            min.pop();
        }
    }

    public void min() {
        min.peek();
    }

}
