package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LC1711 {

    public static void main(String[] args) {
        int[] num = {1,1,1,3,3,3,7};
        System.out.println(countPairs(num));
    }

    public static int countPairs(int[] deliciousness) {
        int mod = 1000000007, ans = 0, maxVal = 0;
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : deliciousness) {
            for (int i = 0; i < 31; i++) {
                int sum = 1 << i;
                if (sum > num + maxVal) {
                    break;
                }
                if (num <= sum) {
                    int subNum = numCountMap.getOrDefault(sum - num, 0);
                    ans = (ans + subNum) % mod;
                }
            }
            maxVal = Math.max(maxVal, num);
            Integer exist = numCountMap.getOrDefault(num, 0);
            numCountMap.put(num, exist + 1);
        }
        return ans;
    }

}
