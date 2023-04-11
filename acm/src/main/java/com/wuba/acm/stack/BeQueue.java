package com.wuba.acm.stack;

import android.app.Dialog;
import android.os.Handler;

import java.util.Deque;
import java.util.LinkedList;
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

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new LinkedList<>(); // 维护一个递减的双端队列
        for (int i = 0; i < nums.length; i++) {
            // 弹出队列中不符合要求的元素
            while (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }
            // 弹出队列尾部比当前元素小的元素
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offer(i); // 将当前元素插入队列
            if (i >= k - 1) {
                res[i - k + 1] = nums[queue.peek()]; // 队列头即为当前滑动窗口的最大值
            }
        }
        return res;
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
