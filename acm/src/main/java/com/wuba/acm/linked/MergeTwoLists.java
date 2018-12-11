package com.wuba.acm.linked;

/**
 * desc : 两个有序的链表合并
 * date : 2018/12/11
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * @author : dongSen
 */
public class MergeTwoLists {

    //my
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode listNode = new ListNode(0);
        ListNode tail = listNode;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.val > l2.val) {
                    tail.next = l2;
                    l2 = l2.next;
                } else {
                    tail.next = l1;
                    l1 = l1.next;
                }
            } else if (l1 == null) {
                tail.next = l2;
                l2 = l2.next;
            } else {
                tail.next = l1;
                l1 = l1.next;
            }
            tail = tail.next;
        }
        return listNode.next;
    }

    //best
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

}
