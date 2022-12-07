package com.jason.leetCode;

import java.util.TreeSet;

/**
 * @author chenjieaj
 * @date 2022/12/5 10:51:02
 * @description
 */
public class LC1774 {
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        TreeSet<Integer> sortTable = new TreeSet<>();
        sortTable.add(0);// 不加料
        int length = toppingCosts.length;
        for (int i = 0; i < length; i++) {
            sortTable.add(toppingCosts[i]);// 一个料
            for (int j = 0; j < i; j++) {
                sortTable.add(toppingCosts[i] + toppingCosts[j]);// 两个料
            }
        }
        int ans = baseCosts[0];
        for (int baseCost : baseCosts) {
            int find = target - baseCost;
            Integer floor = sortTable.floor(find);
            Integer ceiling = sortTable.ceiling(find);
            int cur = Math.abs(baseCost + floor - target) < Math.abs(baseCost + ceiling - target) ? baseCost + floor : baseCost + ceiling;
            if (Math.abs(target - cur) < Math.abs(target - ans)) {
                ans = cur;
            } else if (Math.abs(target - cur) == Math.abs(target - ans) && cur < ans) {
                ans = cur;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
