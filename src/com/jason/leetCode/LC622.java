package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/8/2 9:02:31
 * @description
 */
public class LC622 {

    static class MyCircularQueue1 {

        int head, tail, size;
        int[] container;

        public MyCircularQueue1(int k) {
            container = new int[k];
            head = tail = 0;
            size = k + 1;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            container[head] = value;
            head = (head + 1) % size;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            tail = (tail + 1) % size;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return container[tail];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            int ans = container[(head - 1 + size) % size];
            return ans;
        }

        public boolean isEmpty() {
            return head == tail;
        }

        public boolean isFull() {
            return ((head + 1) % size) == tail;
        }
    }

    static class MyCircularQueue {
        private int front;
        private int rear;
        private int capacity;
        private int[] elements;

        public MyCircularQueue(int k) {
            capacity = k + 1;
            elements = new int[capacity];
            rear = front = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            elements[rear] = value;
            rear = (rear + 1) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % capacity;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return elements[front];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return elements[(rear - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return rear == front;
        }

        public boolean isFull() {
            return ((rear + 1) % capacity) == front;
        }
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        circularQueue.enQueue(1); // 返回 true
        circularQueue.enQueue(2); // 返回 true
        circularQueue.enQueue(3); // 返回 true
        circularQueue.enQueue(4); // 返回 false，队列已满
        circularQueue.Rear(); // 返回 3
        circularQueue.isFull(); // 返回 true
        circularQueue.deQueue(); // 返回 true
        circularQueue.enQueue(4); // 返回 true
        circularQueue.Rear(); // 返回 4
    }
}
