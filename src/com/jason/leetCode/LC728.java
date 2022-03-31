package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LC728 {
    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int cur = left; cur <= right; cur++) {
            if (match(cur)) {
                ans.add(cur);
            }
        }
        return ans;
    }

    private static boolean match(int num) {
        int delegate = num;
        while (delegate > 0) {
            int k = delegate % 10;
            if (k == 0 || num % k != 0) {
                return false;
            }
            delegate /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(selfDividingNumbers(1, 22));
        System.out.println(selfDividingNumbers(47, 85));
    }
}
