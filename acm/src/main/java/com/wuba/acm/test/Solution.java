package com.wuba.acm.test;

/**
 * desc :
 * date : 2018/12/10
 *
 * @author : dongSen
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode listNode = new ListNode(1);
//        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);

        ListNode result = solution.middleNode(listNode);

//        System.out.println(index);
//        for (int i = 0; i < strings.size(); i++) {
//            System.out.println(strings.get(i));
//        }
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public ListNode middleNode(ListNode head) {
        ListNode first = head;
        ListNode second = head;

        while (second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }
        return first;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}