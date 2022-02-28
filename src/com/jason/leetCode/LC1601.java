package com.jason.leetCode;

public class LC1601 {

    public static class Solution {
        int[] change;
        int ans, n, finished, curAns;

        public int maximumRequests(int n, int[][] requests) {
            change = new int[n];
            this.n = n;
            ans = 0;
            finished = n;
            dfs(requests, 0);
            return ans;
        }

        private void dfs(int[][] requests, int cur) {
            if (cur == requests.length) {
                if (finished == n) {
                    ans = Math.max(ans, curAns);
                }
                return;
            }
            // 不满足当前记录
            dfs(requests, cur + 1);
            // 满足当前记录
            // 保存当前状态
            int tempFin = finished;
            curAns++;//满足一个人
            int from = requests[cur][0];
            int to = requests[cur][1];
            finished -= change[from] == 0 ? 1 : 0;
            change[from]--;//房客减少
            finished += change[from] == 0 ? 1 : 0;

            finished -= change[to] == 0 ? 1 : 0;
            change[to]++;//房客增加
            finished += change[to] == 0 ? 1 : 0;

            //dfs
            dfs(requests, cur + 1);

            // 恢复现场
            change[from]++;
            change[to]--;
            curAns--;
            finished = tempFin;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] requests = {{0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 0}, {3, 4}};
        int n = 5;
        System.out.println(solution.maximumRequests(n, requests));

    }
}
