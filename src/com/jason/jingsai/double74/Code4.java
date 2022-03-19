package com.jason.jingsai.double74;

import java.util.HashMap;
import java.util.Map;

public class Code4 {
    // 记忆化搜索，超时
    public static int minimumWhiteTiles1(String floor, int numCarpets, int carpetLen) {
        if (carpetLen * numCarpets >= floor.length()) {
            return 0;
        }
        char[] chars = floor.toCharArray();
        int[] postNum = new int[chars.length];//后面还有几块白色的
        postNum[chars.length - 1] = chars[chars.length - 1] == '1' ? 1 : 0;
        for (int i = chars.length - 2; i >= 0; i--) {
            postNum[i] += postNum[i + 1] + (chars[i] == '1' ? 1 : 0);
        }
        Map<Integer, Integer> cache = new HashMap<>();
        return process(chars, postNum, carpetLen, numCarpets, 0, cache);
    }

    private static int process(char[] chars, int[] postNum, int carpetLen, int num, int idx, Map<Integer, Integer> cache) {
        if (idx >= chars.length) {// 已经到最后了，收答案
            return 0;
        } else if (num == 0) {// 没有地毯了，收答案
            return postNum[idx];
        }
        int key = num << 10 | idx;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        // 当前放地毯
        int p1 = process(chars, postNum, carpetLen, num - 1, idx + carpetLen, cache);
        // 当前不放地毯
        int p2 = process(chars, postNum, carpetLen, num, idx + 1, cache) + (chars[idx] == '1' ? 1 : 0);
        int ans = Math.min(p1, p2);
        cache.put(key, ans);
        return ans;
    }

    // 严格依赖动态规划
    public static int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        if (carpetLen * numCarpets >= floor.length()) {
            return 0;
        }
        char[] chars = floor.toCharArray();
        int[] postNum = new int[chars.length];//后面还有几块白色的
        postNum[chars.length - 1] = chars[chars.length - 1] == '1' ? 1 : 0;
        for (int i = chars.length - 2; i >= 0; i--) {
            postNum[i] += postNum[i + 1] + (chars[i] == '1' ? 1 : 0);
        }
        int[][] dp = new int[numCarpets + 1][chars.length + 1];
        for (int i = 0; i < chars.length; i++) {
            dp[0][i] = postNum[i];
        }
        for (int i = 1; i <= numCarpets; i++) {
            for (int j = chars.length - 2; j >= 0; j--) {
                // 当前放地毯
                int p1 = 0;
                if (j + carpetLen < chars.length) {
                    p1 = dp[i - 1][j + carpetLen];
                }
                // 当前不放地毯
                int p2 = dp[i][j + 1] + (chars[j] == '1' ? 1 : 0);
                int ans = Math.min(p1, p2);
                dp[i][j] = ans;
            }
        }
        return dp[numCarpets][0];
    }

    public static void main(String[] args) {
        String floor = "11111";
        int numCarpets = 2, carpetLen = 3;
        System.out.println(minimumWhiteTiles1(floor, numCarpets, carpetLen));
        System.out.println(minimumWhiteTiles(floor, numCarpets, carpetLen));
    }
}
