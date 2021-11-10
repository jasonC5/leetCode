package com.jason.leetCode;

public class LC21 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode head = (l2 == null || l1 == null) ? (l1 == null ? l2 : l1) : (l1.val < l2.val ? l1 : l2);
        ListNode cur = head;
        ListNode other = head == l1 ? l2 : l1;
        while (cur != null) {
            ListNode next = cur.next;
            if (other == null && next == null) {
                cur = null;
            } else if (other == null || next == null) {
                cur.next = other == null ? next : other;
                cur = cur.next;
                other = null;
            } else {
                cur.next = next.val < other.val ? next : other;
                other = cur.next == next ? other : next;
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode h1 = new ListNode(1);
        h1.next = new ListNode(2);
        h1.next.next = new ListNode(4);
        ListNode h2 = new ListNode(1);
        h2.next = new ListNode(3);
        h2.next.next = new ListNode(4);
        ListNode newHead = mergeTwoLists(h1, null);
        System.out.println(newHead);
    }
}
