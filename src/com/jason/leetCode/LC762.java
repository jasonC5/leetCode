package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

public class LC762 {
    Set<Integer> primeSet;
    {
        primeSet = new HashSet<>();
//        primeSet.add(1);
        primeSet.add(2);
        primeSet.add(3);
        primeSet.add(5);
        primeSet.add(7);
        primeSet.add(11);
        primeSet.add(13);
        primeSet.add(17);
        primeSet.add(19);
        primeSet.add(23);
        primeSet.add(27);
        primeSet.add(29);
        primeSet.add(31);
    }

    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            if (isPrimeBit(i)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean isPrimeBit(int num) {
        int count = 0;
        while (num > 0) {
            num -= num & -num;
            count++;
        }
        return primeSet.contains(count);
    }


    public static void main(String[] args) {
        LC762 lc762 = new LC762();
        System.out.println(lc762.countPrimeSetBits(6, 10));
    }
}
