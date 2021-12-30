package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

public class LC825 {
    public int numFriendRequestsError(int[] ages) {
        Map<Integer, Integer> agesCountMap = new HashMap<>();
        // 统计数量
        for (int age : ages) {
            Integer count = agesCountMap.getOrDefault(age, 0);
            agesCountMap.put(age, ++count);
        }
        int ans = 0;
        for (Integer count : agesCountMap.values()) {
            // count-1 + count-2 + …… + 1
            ans += ((count - 1) * count) >> 1;
        }
        return ans;
    }

    public static void main(String[] args) {

    }

    public int numFriendRequests(int[] ages) {
        int[] ageCountBucket = new int[121];
        int ans = 0;

        return ans;
    }
}
