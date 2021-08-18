package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * <p>
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
 * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/student-attendance-record-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC552 {
    //可能获得出勤奖励的记录情况 数量
//'A'：Absent，缺勤
//'L'：Late，迟到
//'P'：Present，到场
    public static void main(String[] args) {
//        System.out.println(checkRecord(2));
//        System.out.println(checkRecord(1));
        System.out.println(checkRecord2(10));
        System.out.println(checkRecord(10));
    }

    public static final int MASK = 1000000007;

    public static int checkRecord(int n) {
        //动态规划
        //上一题的两个mark和n维护成dp
        Map<String, Integer> dp = new HashMap();
        return process("", 0, n, dp);
    }

    private static int process(String pre, int index, int length, Map<String, Integer> dp) {
        if (!checkRecord(pre)) {
            return 0;
        }
        if (index == length) {
            return 1;
        }
        if (dp.containsKey(pre + "_" + index)) {
            return dp.get(pre + "_" + index);
        }
        //以A结尾
        int ans = (process(pre + 'A', index + 1, length, dp) % MASK);
        //以L结尾
        ans = ((process(pre + 'L', index + 1, length, dp) % MASK) + ans) % MASK;
        //以P结尾
        ans = ((process(pre + 'P', index + 1, length, dp) % MASK) + ans) % MASK;
        dp.put(pre + "_" + index, ans);
        return ans;
    }

    public static boolean checkRecord(String s) {
        int absent = 0;
        int continuousLate = 0;
        for (char c : s.toCharArray()) {
            absent = c == 'A' ? absent + 1 : absent;
            continuousLate = c == 'L' ? continuousLate + 1 : 0;
            if (absent == 2 || continuousLate == 3) {
                return false;
            }
        }
        return true;
    }

    public static int checkRecord2(int n) {
        int[][][] dp = new int[n + 1][2][3]; // 长度，A 的数量，结尾连续 L 的数量
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            //以A结尾(最多只能旷课一次，如果这次旷课了，之前一定没旷过课，所以之前是尾部L出现0次、1次、2次)
            for (int j = 0; j < 3; j++) {
                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][j]) % MASK;
            }
            //以L结尾(之前最多有两次连续迟到，最多旷课一次)
            for (int j = 0; j < 2; j++) {//出现的A
                for (int k = 1; k < 3; k++) {//尾部的L
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % MASK;
                }
            }
            //以p结尾,只要前面是合法的，随便放
            for (int j = 0; j < 2; j++) {//出现的A
                for (int k = 0; k < 3; k++) {//尾部的L
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][k]) % MASK;
                }
            }
        }
        //统计总共有多少合法的
        int sum = 0;
        for (int j = 0; j <= 1; j++) {
            for (int k = 0; k <= 2; k++) {
                sum = (sum + dp[n][j][k]) % MASK;
            }
        }
        return sum;
    }

}
