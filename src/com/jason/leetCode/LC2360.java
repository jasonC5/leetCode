package com.jason.leetCode;

public class LC2360 {

    public int longestCycle(int[] edges) {
        int ans = -1;
        int n = edges.length;
        int[] ids = new int[n];
        for (int from = 0, cnt = 1; from < n; from++) {
            if (ids[from] == 0) {
                for (int cur = from, fromId = cnt; cur != -1; cur = edges[cur]) {
                    if (ids[cur] > 0) {// 可能是新的环，也可能是之前的环
                        if (ids[cur] >= fromId) {
                            ans = Math.max(ans, cnt - ids[cur]);
                        }
                        break;
                    }
                    ids[cur] = cnt++;
                }
            }
        }
        return ans;
    }
}
