package com.jason.leetCode;

public class LC83 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode pre = head;
        ListNode cur = pre.next;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == pre.val) {
                pre.next = next;
            } else {
                pre = cur;
            }
            cur = next;
        }
        return head;
    }
}
