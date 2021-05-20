package com.jason.leetCode;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为m x n 由非负整数组成。
 *
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 *
 * 请你找出matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * 示例 2：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * 示例 3：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * 示例 4：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC1738 {

    public static void main(String[] args) {
        int[][] arr = {{5,2},{1,6}};
        int i = Solution.kthLargestValue(arr, 4);
        System.out.println(i);
    }

    static class Solution {
        public static int kthLargestValue(int[][] matrix, int k) {
            //前缀和、排序
            int height = matrix.length;
            int width = matrix[0].length;
            int dp[][] = new int[height+1][width+1];
            //dp[i][j] 怎么来的：dp[0][x]、dp[y][0] = 0
            //dp[1][1] = matrix[0][0]
            //dp[i+1][j+1] = matrix[i][j]为右下角整个矩形异或来的
            //dp[i+1][j+1] = dp[i][j] ^ dp[i+1][j] ^ dp[i][j+1] ^ matrix[i][j]  (dp[i][j] 异或了三遍，其中两遍抵消了， 右边和下边各异或了一遍，再异或上matrix[i][j] 完整了)
            List<Integer> dpList = new ArrayList<>();
            for (int i = 1; i < height+1; i++) {
                for (int j = 1; j < width + 1; j++) {
                    dp[i][j] = dp[i-1][j-1] ^ dp[i-1][j] ^ dp[i][j-1] ^ matrix[i-1][j-1];
                    dpList.add(dp[i][j]);
                }
            }
            dpList.sort((x1,x2)->x2-x1);
            return dpList.get(k-1);
        }
    }
}
