package com.wuba.acm.linked;

/**
 * desc : 求链表的中间结点
 * date : 2018/12/11
 * https://leetcode.com/problems/middle-of-the-linked-list/submissions/
 *
 * @author : dongSen
 */
public class MiddleNode {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
