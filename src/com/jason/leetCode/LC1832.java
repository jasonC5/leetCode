package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/13 9:30:08
 * @description
 */
public class LC1832 {
    public static final int MASK = (1 << 26) - 1;

    public boolean checkIfPangram(String sentence) {
        int delegate = 0;
        char[] chars = sentence.toCharArray();
        for (char c : chars) {
            delegate |= 1 << (c - 'a');
        }
        return delegate == MASK;
    }
}
