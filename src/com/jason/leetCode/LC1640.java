package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2022/9/23 12:58:49
 * @description
 */
public class LC1640 {
    public static boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] piece : pieces) {
            map.put(piece[0], piece);
        }
        int i = 0;
        while (i < arr.length) {
            if (map.containsKey(arr[i])) {
                int[] piece = map.get(arr[i]);
                for (int j = 0; j < piece.length; j++) {
                    if (arr[i++] != piece[j]) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
