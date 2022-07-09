package com.jason.offer;

public class Offer_029 {
    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    ;

    public Node insert(Node head, int insertVal) {
        // 0
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }
        // 1
        Node next = head.next;
        if (head == next) {
            head.next = node;
            node.next = head;
            return head;
        }
        // n
        // 特殊情况，全都相等，
        while(next != head){
            if (next.val != head.val){
                break;
            }
            next = next.next;
        }
        if (next == head){
            next = head.next;
            head.next = node;
            node.next = next;
            return head;
        }
        next = head.next;
        Node cur = head;
        while (true) {
            if (cur.val == insertVal) {
                break;
            } else if (cur.val < insertVal && next.val >= insertVal) {
                break;
            } else if (next.val < cur.val && insertVal > cur.val) {
                break;
            } else if (next.val < cur.val && insertVal < next.val) {
                break;
            }
            cur = next;
            next = next.next;
        }
        cur.next = node;
        node.next = next;
        return head;
    }
}
