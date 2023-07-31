package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenjieaj
 * @date 2023/7/31 10:00:53
 * @description
 */
public class LC143 {
    public static void reorderList(ListNode head) {
        LinkedList<ListNode> linkedList = new LinkedList<>();
        ListNode node = head.next;
        while (node != null) {
            linkedList.add(node);
            node = node.next;
        }
        boolean tail = true;
        node = head;
        while (!linkedList.isEmpty()) {
            if (tail) {
                node.next = linkedList.pollLast();
            } else {
                node.next = linkedList.pollFirst();
            }
            node = node.next;
            tail = !tail;
        }
        node.next = null;
    }

    public static void main(String[] args) {
        int[] list = {1,2,3,4,5};
        ListNode head = buildLinkedList(list);
        reorderList(head);
    }

    private static ListNode buildLinkedList(int[] list) {
        ListNode head = new ListNode(list[0]);
        ListNode tail = head;
        for (int i = 1; i < list.length; i++) {
            tail.next = new ListNode(list[i]);
            tail = tail.next;
        }
        return head;
    }
}
