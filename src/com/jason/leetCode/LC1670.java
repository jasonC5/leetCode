package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenjieaj
 * @date 2023/11/28 13:48:44
 * @description
 */
public class LC1670 {
    static class FrontMiddleBackQueue {
        LinkedList<Integer> left, right;

        public FrontMiddleBackQueue() {
            left = new LinkedList();
            right = new LinkedList();
        }

        public void pushFront(int val) {
            left.addFirst(val);
            balance();
        }

        public void pushMiddle(int val) {
            left.addLast(val);
            balance();
        }

        public void pushBack(int val) {
            right.addLast(val);
            balance();
        }

        public int popFront() {
            int val = -1;
            if (left.size() > 0) {
                val = left.pollFirst();
            } else if (right.size() > 0) {
                val = right.pollFirst();
            }
            balance();
            return val;
        }

        public int popMiddle() {
            int val = -1;
            if (left.size() == 0 && right.size() == 0) {
                return -1;
            } else if (left.size() == right.size()) {
                val = left.pollLast();
            } else {
                val = right.pollFirst();
            }
            balance();
            return val;
        }

        public int popBack() {
            int val = -1;
            if (right.size() > 0) {
                val = right.pollLast();
            } else if (left.size() > 0) {
                val = left.pollFirst();
            }
            balance();
            return val;
        }

        private void balance() {
            if (left.size() > right.size()) {
                right.addFirst(left.pollLast());
            }
            if (right.size() - left.size() > 1) {
                left.addLast(right.pollFirst());
            }
        }
    }

    public static void main(String[] args) {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        q.pushFront(1);   // [1]
        q.pushBack(2);    // [1, 2]
        q.pushMiddle(3);  // [1, 3, 2]
        q.pushMiddle(4);  // [1, 4, 3, 2]
        System.out.println(q.popFront());
        System.out.println(q.popMiddle());
        System.out.println(q.popMiddle());
        System.out.println(q.popBack());
        System.out.println(q.popFront());
    }
}
