package com.jason.leetCode;

import java.util.PriorityQueue;

public class LC23 {

    public static class ListNode {
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

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }
        ListNode head = heap.poll();
        if (head != null && head.next != null) {
            heap.offer(head.next);
        }
        ListNode pre = head;
        while (!heap.isEmpty()) {
            ListNode poll = heap.poll();
            if (poll.next != null) {
                heap.offer(poll.next);
            }
            pre.next = poll;
            pre = pre.next;
            pre.next = null;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode[] nodes = new ListNode[3];
        nodes[0] = new ListNode(1);
        nodes[0].next = new ListNode(4);
        nodes[0].next.next = new ListNode(5);

        nodes[1] = new ListNode(1);
        nodes[1].next = new ListNode(3);
        nodes[1].next.next = new ListNode(4);

        nodes[2] = new ListNode(2);
        nodes[2].next = new ListNode(6);
        ListNode listNode = mergeKLists(nodes);
        System.out.println(listNode);
    }
}
