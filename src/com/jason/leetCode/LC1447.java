package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LC1447 {
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(i, j) == 1) {
                    ans.add(j + "/" + i);
                }
            }
        }
        return ans;
    }

    // 最大公因数
    private int gcd(int i, int j) {
        return j != 0 ? gcd(j, i % j) : i;
    }
}
