package com.wuba.acm.leetcode;

/**
 * desc :
 * date : 2020/4/14 10:57 AM
 *
 * @author : dongSen
 */
class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    static ListNode obtain(int count) {
        ListNode node = new ListNode(1);
        ListNode head = node;
        for (int i = 2; i <= count; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        return head;
    }

    static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
        System.out.println();
    }

    static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
