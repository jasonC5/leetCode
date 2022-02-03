package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LC1414 {
    public int findMinFibonacciNumbers(int k) {
        int f1 = 1, f2 = 1;
        TreeSet<Integer> orderedTable = new TreeSet<>();
        orderedTable.add(1);
        while (f1 + f2 <= k) {
            int fn = f1 + f2;
            orderedTable.add(fn);
            f1 = f2;
            f2 = fn;
        }
        int ans = 0;
        while (k > 0) {
            Integer floor = orderedTable.floor(k);
            k -= floor;
            ans++;
        }
        return ans;
    }
}
