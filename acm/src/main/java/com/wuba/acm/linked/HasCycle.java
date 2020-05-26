package com.wuba.acm.linked;

/**
 * desc : 链表中环的检测
 * date : 2018/12/11
 * https://leetcode-cn.com/problems/linked-list-cycle/
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

    /**
     * 输出环的起始点
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode p = null;
        // 检测有环
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                p = slow;
                break;
            }
        }
        if (p == null) {
            return null;
        }
        // 找到入口 Floyd算法
        ListNode p1 = head;
        ListNode p2 = slow;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
