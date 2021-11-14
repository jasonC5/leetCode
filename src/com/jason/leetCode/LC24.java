package com.jason.leetCode;

public class LC24 {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        ListNode next = newHead.next;
        newHead.next = head;
        newHead.next.next = next;
        ListNode cur = next;
        ListNode pre = head;
        while (cur != null && cur.next != null) {
            next = cur.next;
            ListNode nextNext = next.next;
            pre.next = next;
            next.next = cur;
            cur.next = nextNext;
            pre = cur;
            cur = nextNext;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        ListNode node = swapPairs(head);
        System.out.println(node);
    }
}
