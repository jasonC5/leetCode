package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给出矩阵matrix和目标值target，返回元素总和等于目标值的非空子矩阵的数量。
 *
 * 子矩阵x1, y1, x2, y2是满足 x1 <= x <= x2且y1 <= y <= y2的所有单元matrix[x][y]的集合。
 *
 * 如果(x1, y1, x2, y2) 和(x1', y1', x2', y2')两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * 输出：4
 * 解释：四个只含 0 的 1x1 子矩阵。
 * 示例 2：
 *
 * 输入：matrix = [[1,-1],[-1,1]], target = 0
 * 输出：5
 * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
 * 示例 3：
 *
 * 输入：matrix = [[904]], target = 0
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC1074 {
    public static void main(String[] args) {
        //方案 前缀和+哈希表
        int[][] a= {{0,1,0},{1,1,1},{0,1,0}};
        int count = numSubmatrixSumTarget2(a, 0);
        System.out.println(count);
    }

    private static int numSubmatrixSumTarget2(int[][] matrix, int target) {
        //枚举上下边界，算出每列的和，求出每列的和，左右边界通过前缀和往前推
        int ans = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        //4个for循环
        for (int i = 0; i < m; i++) {//上边界
            for (int j = i; j < m; ++j) { // 枚举下边界
                int[] sum = new int[n];
                for (int k = 0; k < n; k++) {//每一列的和
                    sum[k] += matrix[j][k];
                }
                ans += subarraySum(sum, target);
            }
        }
        return ans;
    }

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        int count = 0, pre = 0;
        for (int x : nums) {
            pre += x;
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {//想岔了，这种方案太太复杂了
        int ans = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        Map<Integer, Integer> checkMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] += matrix[i][j];
                if (i > 0) {
                    dp[i][j] += dp[i-1][j];
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j-1];
                }
                if (i>0 && j>0) {
                    dp[i][j] -= dp[i-1][j-1];
                }
                checkMap.put(dp[i][j], checkMap.getOrDefault(dp[i][j],0)+1);
            }
        }
        //前缀和
        for (Integer key : checkMap.keySet()) {//这里不行【需要三个加减法，太复杂了】
            if (checkMap.containsKey(target-key) ){
                ans += checkMap.get(target-key);
            }
        }
        return ans;
    }

}
