package com.wuba.acm.linked;

import com.wuba.acm.leetcode.ListNode;

/**
 * desc : 合并K个排序链表
 * date : 2020/4/26 12:41 PM
 *
 * @author : dongSen
 *
 * 题目：https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = null;
        for (int i = 0; i < lists.length; i++) {
            result = mergeTwoList(result, lists[i]);
        }
        return result;
    }

    private ListNode mergeTwoList(ListNode list1, ListNode list2) {
        if (list1 != null || list2 != null) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
        }

        ListNode head = new ListNode(0);
        ListNode tail = head;
        ListNode aPtr = list1;
        ListNode bPtr = list2;

        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        if (aPtr != null) {
            tail.next = aPtr;
        } else {
            tail.next = bPtr;
        }
        return head.next;
    }
}
