package com.jason.jingsai.competition281;

import com.jason.leetCode.LC19;


public class C6013 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode mergeNodes(ListNode head) {
        ListNode newHead = head.next;
        ListNode pre = newHead;
        ListNode curNode = pre.next;
        while (curNode != null){
            if (curNode.val ==0) {
                pre.next = null;
            } else {
                if (pre.next == null) {
                    pre.next = curNode;
                    pre = curNode;
                } else {
                    pre.val += curNode.val;
                }
            }
            curNode = curNode.next;
        }
        return newHead;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next =  new ListNode(3);
        head.next.next =  new ListNode(1);
        head.next.next.next =  new ListNode(0);
        head.next.next.next.next =  new ListNode(4);
        head.next.next.next.next.next =  new ListNode(5);
        head.next.next.next.next.next.next =  new ListNode(2);
        head.next.next.next.next.next.next.next =  new ListNode(0);
        ListNode newHead = mergeNodes(head);
        System.out.println(newHead);
    }
}
