package com.wuba.acm.stack;

import java.util.Stack;

/**
 * desc : 用两个栈实现队列
 * date : 2019/3/11
 *
 * @author : dongSen
 */
public class BeQueue {

    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    public static void main(String[] args) {

        BeQueue queue = new BeQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        System.out.println(queue.pop());
        System.out.println(queue.pop());

        queue.push(4);
        queue.push(5);
        queue.push(6);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    public void push(int node) {
        in.push(node);
    }

    public int pop() {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());

        if (out.isEmpty()) {
            System.out.println("the stack is empty");
            return -1;
        }

        return out.pop();
    }

}
