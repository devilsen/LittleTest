package com.wuba.acm.linked;

/**
 * desc : 删除链表倒数第 n 个结点
 * date : 2018/12/11
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * @author : dongSen
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first;
        ListNode second = null;
        int time = 0;

        for (first = head; ; first = first.next) {
            if (second != null) {
                second = second.next;
            }
            if (n == time) {
                second = head;
                if (first == null) {
                    head = head.next;
                    break;
                }
            }

            if (first.next == null && n == 1 && second != null) {
                second.next = null;
                break;
            }

            if (first.next == null) {
                if (second != null && second.next != null) {
                    second.next = second.next.next;
                    break;
                }
            }

            time++;

        }
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode end = head;
        ListNode trailer = head;
        while (n > 0 && end.next != null) {
            end = end.next;
            n--;
        }
        while (end.next != null) {
            end = end.next;
            trailer = trailer.next;
        }
        if (n > 0) {
            return head.next;
        }
        trailer.next = trailer.next.next;
        return head;
    }
}
