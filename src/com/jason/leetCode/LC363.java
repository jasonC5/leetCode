package com.jason.leetCode;

/**
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 *
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC363 {
    public static void main(String[] args) {
//        int[][] nums =  {{1,0,1},{0,-2,3}};
//        int k = 2;
        int[][] nums =  {{2,2,-1}};
        int k = 0;
        System.out.println(Solution.maxSumSubmatrix(nums,k));
    }

    public static class Solution {
        public static int maxSumSubmatrix(int[][] matrix, int k) {
            //获取矩形区域，就需要两个点圈起来一个矩形，这两个店从左上角移到右下角，完成遍历
            //最小值
            Integer maxVal = null;
            final int n = matrix.length;
            final int m = matrix[0].length;
            int[][] dp = new int[n][m];//左上角到当前位置形成的矩形的和
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <m; j++) {
                    //自己+左边的数+上边的数+左上角的数
                    dp[i][j] += matrix[i][j];
                    if (i>0) {
                        dp[i][j] += dp[i-1][j];
                    }
                    if (j>0) {
                        dp[i][j] += dp[i][j-1];
                    }
                    if (i>0&&j>0) {//加了两遍了，减掉
                        dp[i][j] -= dp[i-1][j-1];
                    }
                }
            }
            //获得一个结果矩阵：0,0到本位制的矩形结果和
            //点1和点2，在矩阵内部
            for (int x1 = 0; x1 < n; x1++) {
                for (int y1 = 0; y1 < m; y1++) {
                    for (int x2 = x1; x2 < n; x2++) {
                        for (int y2 = y1; y2 < m; y2++) {
                            //得到两个点：[x1,y1],[x2,y2]
                            int sum = dp[x2][y2];
                            if (x1>0){
                                sum -= dp[x1-1][y2];
                            }
                            if (y1>0) {
                                sum -= dp[x2][y1-1];
                            }
                            if (x1>0 && y1>0) {
                                sum += dp[x1-1][y1-1];
                            }
                            if (sum == k){
                                return k;
                            }
                            if (sum < k && (maxVal == null || maxVal < sum)) {
                                maxVal=sum;
                            }
                        }
                    }
                }
            }
            return maxVal;
        }
    }
}
