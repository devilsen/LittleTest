package com.wuba.acm.linked;

/**
 * desc : 链表中环的检测
 * date : 2018/12/11
 * https://leetcode.com/problems/linked-list-cycle/submissions/
 *
 * @author : dongSen
 */
public class HasCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;

        ListNode first = head;
        ListNode second = head;
        while (first.next != null) {
            first = first.next;
            if (second != null && second.next != null) {
                second = second.next.next;
            } else {
                return false;
            }

            if (first == second) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        if (head == null) return false;
        ListNode p = head, q = head.next;
        while (q != null) {
            if (p == q) return true;
            p = p.next;
            q = q.next;
            if (q != null) q = q.next;
        }
        return false;
    }
}
