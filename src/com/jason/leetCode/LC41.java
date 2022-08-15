package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/8/15 9:07:00
 * @description
 */
public class LC41 {
    class MyCircularDeque {

        int[] container;
        int front, last;
        int capacity;

        public MyCircularDeque(int k) {
            capacity = k + 1;
            front = last = 0;
            container = new int[k + 1];
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            front = (front - 1 + capacity) % capacity;
            container[front] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            container[last] = value;
            last = (last + 1) % capacity;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % capacity;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            last = (last - 1 + capacity) % capacity;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return container[front];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return container[(last - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return front == last;
        }

        public boolean isFull() {
            return (last + 1) % capacity == front;
        }
    }

    class MyCircularDeque1 {
        private int[] container;
        private int last, front;
        private int capacity;

        public MyCircularDeque1(int k) {
            capacity = k + 1;
            last = front = 0;
            container = new int[k + 1];
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            front = (front - 1 + capacity) % capacity;
            container[front] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            container[last] = value;
            last = (last + 1) % capacity;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % capacity;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            last = (last - 1 + capacity) % capacity;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return container[front];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return container[(last - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return last == front;
        }

        public boolean isFull() {
            return (last + 1) % capacity == front;
        }
    }
}
