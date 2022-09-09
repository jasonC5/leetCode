package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/9/9 9:01:13
 * @description
 */
public class LC1598 {
    public int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if ("./".equals(log)) {
                continue;
            } else if ("../".equals(log)) {
                depth = Math.max(0, depth - 1);
            } else {
                depth++;
            }
        }
        return depth;
    }
}
