package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/1/30 9:16:46
 * @description
 */
public class LC1669 {


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

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode x = null, y = null, cur = list1;
        for (int i = 0; i <= b; i++) {
            if (i == a - 1) {
                x = cur;
            }
            if (i == b) {
                y = cur.next;
            }
            cur = cur.next;
        }
        x.next = list2;
        while (true) {
            if (list2.next == null) {
                list2.next = y;
                break;
            }
            list2 = list2.next;
        }
        return list1;
    }
}
