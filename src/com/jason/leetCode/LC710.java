package com.jason.leetCode;

import java.util.*;
import java.util.stream.Collectors;

public class LC710 {
    static class Solution {
        int k;
        Map<Integer, Integer> transfer;
        Random random;

        public Solution(int n, int[] blacklist) {
            random = new Random();
            transfer = new HashMap<>();
            if (blacklist == null || blacklist.length == 0) {
                k = n;
            } else {
                k = n - blacklist.length;
                int point = n - 1;
                Arrays.sort(blacklist);
                for (int i = blacklist.length - 1; i >= 0; i--) {
                    // 到不了这，不处理
                    if (blacklist[i] >= k) {
                        transfer.put(blacklist[i], -1);
                        continue;
                    }
                    // 找到后面不是黑名单的位置，做映射
                    while (transfer.containsKey(point)) {
                        point--;
                    }
                    transfer.put(blacklist[i], point--);
                }
            }
        }

        public int pick() {
            int i = random.nextInt(k);
            if (transfer.containsKey(i)) {
                return transfer.get(i);
            } else {
                return i;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution(4, new int[]{3,1});
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
    }


}
