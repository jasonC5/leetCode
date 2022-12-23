package com.jason.leetCode;

import java.util.stream.Stream;

/**
 * @author chenjieaj
 * @date 2022/12/23 9:23:34
 * @description
 */
public class LC2011 {
    public int finalValueAfterOperations(String[] operations) {
        int x = 0;
        for (String operation : operations) {
            x += operation.charAt(1) == '+' ? 1 : -1;
        }
        return x;
        // 一行
//        return Stream.of(operations).mapToInt((s) -> s.charAt(1) == '+' ? 1 : -1).sum();
    }
}
