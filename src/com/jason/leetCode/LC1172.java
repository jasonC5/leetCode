package com.jason.leetCode;

import java.util.*;

/**
 * @author chenjieaj
 * @date 2023/4/28 9:29:07
 * @description
 */
public class LC1172 {
//    static class DinnerPlates {
//
//        int size;
//        int capacity;
//        TreeSet<Integer> notFull;
//        List<Stack<Integer>> stackList;
//
//        // 构造
//        public DinnerPlates(int capacity) {
//            size = 1;
//            this.capacity = capacity;
//            notFull = new TreeSet<>();
//            stackList = new ArrayList<>();
//            stackList.add(new Stack<>());
//            notFull.add(0);
//        }
//
//        // 往第一个空栈里面push
//        public void push(int val) {
//            Integer first = notFull.first();
//            Stack<Integer> stack = stackList.get(first);
//            stack.add(val);
//            if (stack.size() == capacity) {
//                notFull.remove(first);
//                if (notFull.isEmpty()) {
//                    notFull.add(++size);
//                    if (stackList.size() > size) {
//                        stackList.set(size, new Stack());
//                    } else {
//                        stackList.add(new Stack());
//                    }
//                }
//            }
//        }
//
//        // 尾部栈pop
//        public int pop() {
//            Stack<Integer> stack = stackList.get(size - 1);
//            if (stack.isEmpty() && size == 1) {
//                return -1;
//            }
//            if (stack.isEmpty()) {
//                stack = stackList.get(size - 2);
//            }
//            Integer pop = stack.pop();
//            handleLess();
//            return pop;
//        }
//
//        // 指定栈pos
//        public int popAtStack(int index) {
//            if (index >= size) {
//                return -1;
//            }
//            Stack<Integer> stack = stackList.get(index);
//            if (stack.isEmpty()) {
//                return -1;
//            }
//            if (stack.size() == capacity) {
//                notFull.add(index);
//            }
//            Integer pop = stack.pop();
//            handleLess();
//            return pop;
//        }
//
//        private void handleLess() {
//            if (!stackList.get(size - 1).isEmpty()) {
//                return;
//            }
//            while (size > 0 && stackList.get(size - 1).isEmpty()) {
//                notFull.remove(--size);
//            }
//            if (size == 0) {
//                size = 1;
//                notFull.add(0);
//            } else if (notFull.isEmpty()) {
//                notFull.add(++size);
//                if (stackList.size() > size) {
//                    stackList.set(size, new Stack());
//                } else {
//                    stackList.add(new Stack());
//                }
//            }
//        }
//    }

    static class DinnerPlates {
        int capacity;
        List<Integer> stack;
        List<Integer> top;
        TreeSet<Integer> poppedPos;

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
            stack = new LinkedList<>();
            top = new ArrayList<>();
            poppedPos = new TreeSet<>();
        }

        public void push(int val) {
            if (poppedPos.isEmpty()) {
                int pos = stack.size();
                stack.add(val);
                if (pos % capacity == 0) {
                    top.add(0);
                }
                else {
                    int stackPos = top.size()-1;
                    int stackTop = top.get(stackPos);
                    top.set(stackPos, stackTop + 1);
                }
            } else {
                int pos = poppedPos.pollFirst();
                stack.set(pos, val);
                int index = pos / capacity;
                int stackTop = top.get(index);
                top.set(index, stackTop + 1);
            }
        }

        public int pop() {
            while (!stack.isEmpty() && poppedPos.contains(stack.size()-1)) {
                stack.remove(stack.size()-1);
                int pos = poppedPos.pollLast();
                if (pos % capacity == 0) {
                    top.remove(top.size()-1);
                }
            }
            if (stack.isEmpty()) {
                return -1;
            } else {
                int pos = stack.size() - 1;
                int val = stack.get(pos);
                stack.remove(pos);
                int index = top.size()-1;
                if (pos % capacity == 0) {
                    top.remove(index);
                } else {
                    top.set(index, index - 1);
                }
                return val;
            }
        }

        public int popAtStack(int index) {
            if (index >= top.size()) {
                return -1;
            }
            int stackTop = top.get(index);
            if (stackTop < 0) {
                return -1;
            }
            top.set(index, stackTop - 1);
            int pos = index * capacity + stackTop;
            poppedPos.add(pos);
            return stack.get(pos);
        }
    }

    public static void main(String[] args) {
//        DinnerPlates D = new DinnerPlates(2);  // 初始化，栈最大容量 capacity = 2
//        D.push(1);
//        D.push(2);
//        D.push(3);
//        D.push(4);
//        D.push(5);
//        System.out.println(D.popAtStack(0));
//        D.push(20);
//        D.push(21);
//        System.out.println(D.popAtStack(0));
//        System.out.println(D.popAtStack(2));
//        System.out.println(D.pop());
//        System.out.println(D.pop());
//        System.out.println(D.pop());
//        System.out.println(D.pop());
//        System.out.println(D.pop());

        DinnerPlates D = new DinnerPlates(1);  // 初始化，栈最大容量 capacity = 2
        D.push(1);
        D.push(2);
        System.out.println(D.popAtStack(1));
        System.out.println(D.pop());
        D.push(1);
        D.push(2);
        System.out.println(D.pop());
        System.out.println(D.pop());
    }
}
