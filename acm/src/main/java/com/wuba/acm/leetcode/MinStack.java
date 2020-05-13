package com.wuba.acm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * desc :
 * date : 2020/5/12 10:54 AM
 *
 * @author : dongSen
 */
public class MinStack {

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(-2);

        System.out.println("getMin: " + stack.getMin());
        stack.pop();
        System.out.println("top: " + stack.top());
        System.out.println("getMin: " + stack.getMin());

    }

    private final LinkedList<Integer> mList = new LinkedList<>();

    private final LinkedList<Integer> mMinList = new LinkedList<>();

    private final PriorityQueue<Integer> queue = new PriorityQueue<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
//        mList.push(x);
//        if (mMinList.isEmpty() || x <= getMin()) {
//            mMinList.push(x);
//        }
        queue.offer(x);
    }

    public void pop() {
//        Integer pop = mList.pop();
//        if (pop == getMin()) {
//            mMinList.pop();
//        }
        queue.poll();
    }

    public int top() {
//        return mList.getFirst();
        return queue.peek();
    }

    public int getMin() {
//        return mMinList.getFirst();
        return queue.peek();
    }

}
