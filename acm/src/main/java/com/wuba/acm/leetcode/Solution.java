package com.wuba.acm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * desc :
 * date : 2020/4/2 11:17 AM
 *
 * @author : dongSen
 */
public class Solution {

    static class MountainArray {
        int[] nums = {1, 2, 3, 4, 5, 2, 1};

        int get(int index) {
            return nums[index];
        }

        int length() {
            return nums.length;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int inMountainArray = solution.findInMountainArray(4, new MountainArray());
//        System.out.println(inMountainArray);

//        boolean happy = solution.isHappy(14);
//        System.out.println(happy);
        ListNode listNode1 = ListNode.obtain(3);
        ListNode listNode2 = ListNode.obtain(3);
        ListNode listNode = solution.sortList(listNode1);
        ListNode.print(listNode);
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null; // 掐断链表，不然会死循环
        // 得到两个排序好的链表
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        // 合并两个链表
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left == null ? right : left;
        return res.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            if (l1 != null) {
                return l1;
            } else {
                return l2;
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;

        while (true) {
            if (l1 == null) {
                tail.next = l2;
                break;
            }
            if (l2 == null) {
                tail.next = l1;
                break;
            }
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        return head.next;
    }

    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        slow = addNum(slow);
        fast = addNum(fast);
        fast = addNum(fast);

        while (slow != fast) {
            slow = addNum(slow);
            fast = addNum(fast);
            fast = addNum(fast);
        }
        return slow == 1;
    }

    private int addNum(int n) {
        int result = 0;
        while (n != 0) {
            int temp = n % 10;
            result = result + temp * temp;
            n = n / 10;
        }
        return result;
    }

    private boolean isHappy(int n, Map<Integer, Boolean> integers) {
        int num = addNum(n);
        if (num == 1) {
            return true;
        } else if (integers.get(num) != null) {
            return false;
        } else {
            return isHappy(num);
        }
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int maxIndex = findMaxIndex(mountainArr);

        int leftIndex = findLeft(target, 0, maxIndex, mountainArr);
        if (leftIndex != -1) {
            return leftIndex;
        }
        return findRight(target, maxIndex + 1, mountainArr.length() - 1, mountainArr);
    }

    private int findRight(int target, int left, int right, MountainArray mountainArr) {
        while (left <= right) {
            int middleIndex = left + (right - left) / 2;
            int middleNum = mountainArr.get(middleIndex);
            if (middleNum == target) {
                return middleIndex;
            } else if (middleNum >= target) {
                left = middleIndex + 1;
            } else {
                right = middleIndex - 1;
            }
        }
        return -1;
    }

    private int findLeft(int target, int left, int right, MountainArray mountainArr) {
        while (left <= right) {
            int middleIndex = left + (right - left) / 2;
            int middleNum = mountainArr.get(middleIndex);
            if (middleNum == target) {
                return middleIndex;
            } else if (middleNum >= target) {
                right = middleIndex - 1;
            } else {
                left = middleIndex + 1;
            }
        }
        return -1;
    }

    private int findMaxIndex(MountainArray mountainArr) {
        int left = 0;
        int right = mountainArr.length() - 1;

        while (left <= right) {
            int middleIndex = left + (right - left) / 2;
            int middleNum = mountainArr.get(middleIndex);
            int middleAdd;
            if (middleIndex + 1 > right) {
                return middleIndex;
            } else {
                middleAdd = mountainArr.get(middleIndex + 1);
            }
            int middleSub;
            if (middleIndex - 1 < 0) {
                middleSub = middleNum;
            } else {
                middleSub = mountainArr.get(middleIndex - 1);
            }

            if (middleNum > middleAdd && middleNum > middleSub) {
                return middleIndex;
            } else if (middleNum > middleAdd) {
                right = middleIndex - 1;
            } else if (middleNum < middleAdd) {
                left = middleIndex + 1;
            }
        }
        return left + (right - left) / 2;
    }

}