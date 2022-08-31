package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author chenjieaj
 * @date 2022/8/31 8:51:32
 * @description
 */
public class LC946 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        Set<Integer> inStack = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        int push = 0;
        for (int num : popped) {
            if (inStack.contains(num)) {
                Integer pop = stack.pop();
                if (pop != num) {
                    return false;
                }
            } else {
                while (push < n) {
                    int cur = pushed[push++];
                    // 找到了入栈的数，直接弹出
                    if (cur == num) {
                        break;
                    }
                    // 没找到，先入栈，下一个
                    inStack.add(cur);
                    stack.add(cur);
                }
            }
        }
        return true;
    }
}
