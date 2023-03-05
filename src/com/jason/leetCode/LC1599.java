package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/3/5 18:18:32
 * @description
 */
public class LC1599 {
    public static int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int rest = 0, ans = 0, times = 0;
        for (int customer : customers) {
            rest += customer;
            if (rest == 0) {
                continue;
            }
            ans += Math.min(rest, 4) * boardingCost - runningCost;
            rest = Math.max(0, rest - 4);
            times++;
        }
        if (rest > 0) {
            times += (rest + 3) / 4;
            int ans2 = ans + rest / 4 * 4 * boardingCost - rest / 4 * runningCost;
            ans += rest * boardingCost - (rest + 3) / 4 * runningCost;
            if (ans2 >= ans) {
                ans = ans2;
                times = times - 1;
            }
        }
        return ans >= 0 ? times : -1;
    }

    public static void main(String[] args) {
        int[] customers = {10, 10, 6, 4, 7};
        System.out.println(minOperationsMaxProfit(customers, 3, 8));
    }
}
