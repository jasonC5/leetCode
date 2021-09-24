package com.jason.leetCode;

import java.util.Stack;

/**
 * @author JasonC5
 */
public class LC430 {

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Node reader = head;
        while (reader != null) {
            if (reader.child != null) {
                if (reader.next != null) {
                    stack.push(reader.next);
                }
                Node child = reader.child;
                reader.child = null;
                child.prev = reader;
                reader.next = child;
            } else if (reader.next == null && !stack.isEmpty()) {
                Node next = stack.pop();
                next.prev = reader;
                reader.next = next;
            }
            reader = reader.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
//        head.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//
//        node3.child = node7;
//        node7.next = node8;
//        node8.next = node9;
//        node9.next = node10;
//
//        node8.child = node11;
//        node11.next = node12;
        head.child = node2;
        node2.child = node3;
        Node node = flatten(head);
        System.out.println(node);
    }

}
