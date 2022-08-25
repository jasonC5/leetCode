package com.jason.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2022/8/25 11:05:33
 * @description
 */
public class LC658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<Integer>();
        for (int num : arr) {
            list.add(num);
        }
        list.sort((i1, i2) -> {
            int abs1 = Math.abs(i1 - x);
            int abs2 = Math.abs(i2 - x);
            if (abs1 == abs2) {
                return i1 - i2;
            } else {
                return abs1 - abs2;
            }
        });
        List<Integer> ans = list.subList(0, k);
        Collections.sort(ans);
        return ans;
    }
}
