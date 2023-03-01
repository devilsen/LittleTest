package com.wuba.acm.linked;

import java.util.concurrent.Semaphore;

/**
 * desc : 单链表反转
 * date : 2018/12/11
 * https://leetcode.com/problems/reverse-linked-list/submissions/
 *
 * @author : dongSen
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode previous = null;
        ListNode current = head;
        ListNode next = current.next;

        while (current != null) {
            current.next = previous;
            previous = current;
            current = next;
            if (next != null)
                next = next.next;
        }

        return previous;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

}
