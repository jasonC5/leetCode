package com.jason.offer;

public class Offer30 {

    class MinStack {
        int[] dataStack;
        int[] minStack;
        int size;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            dataStack = new int[20000];
            minStack = new int[20000];
            size = 0;
        }

        public void push(int x) {
            dataStack[size] = x;
            if (size == 0 || x < minStack[size - 1]) {
                minStack[size++] = x;
            } else {
                minStack[size] = minStack[size++ - 1];
            }
        }

        public void pop() {
            size--;
        }

        public int top() {
            return dataStack[size - 1];
        }

        public int min() {
            return minStack[size - 1];
        }
    }

}
