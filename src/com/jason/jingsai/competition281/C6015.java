package com.jason.jingsai.competition281;

import java.util.HashMap;
import java.util.Map;

public class C6015 {

    public static long coutPairs(int[] nums, int k) {
        int length = nums.length;
        int cnt = 0;
        Map<Integer, Integer> gcdCntMap = new HashMap<>();
        for (int num : nums) {
            if (num % k == 0) {
                cnt++;
            } else {
                // 最大公因数
                int gcd = gcd(k, num);
                gcdCntMap.put(gcd, gcdCntMap.getOrDefault(gcd, 0) + 1);
            }
        }
        // 本身就能整除的，配上任何不能整除的数数都能整除
        long ans = (long) cnt * (length - cnt);
        // 本身能整除的配上能整除的
        ans += (long) cnt * (cnt - 1) >> 1;
        // 凑不能整除的，凑因子相乘如果能整除
        long tmp = 0;
        for (Integer key : gcdCntMap.keySet()) {
            if (key == 1) {
                continue;
            }
            Integer count = gcdCntMap.get(key);
            for (Integer nextKey : gcdCntMap.keySet()) {
                if ((key * nextKey) % k == 0) {
                    if (key.equals(nextKey)) {// 乘自己，只会算一遍，直接加
                        ans += (long) count * (count - 1) >> 1;
                    } else {
                        // 会计算两遍，放到一个临时变量里
                        tmp += (long) count * gcdCntMap.get(nextKey);
                    }
                }
            }
        }
        return ans + (tmp >> 1);
    }
    // 求最大公因数
    private static int gcd(int i, int j) {
        return j != 0 ? gcd(j, i % j) : i;
    }

    public static void main(String[] args) {
        int[] nums = {100, 17, 3, 77, 64, 74, 11, 43, 10, 37};
        int k = 20;
        System.out.println(coutPairs(nums, k));
    }
}
