package com.jason.leetCode;

import java.util.TreeSet;

/**
 * @author chenjieaj
 * @date 2023/2/8 12:43:05
 * @description
 */
public class LC1675 {
    public static int minimumDeviation(int[] nums) {
        TreeSet<Integer> orderTable = new TreeSet<>();
        for (int num : nums) {
            if ((num & 1) == 1) {// 奇数先 * 2
                orderTable.add(num << 1);
            } else {
                orderTable.add(num);
            }
        }
        int ans = orderTable.last() - orderTable.first();
        while ((orderTable.last() & 1) == 0) {
            Integer max = orderTable.pollLast();
            orderTable.add(max >> 1);
            ans = Math.min(ans, orderTable.last() - orderTable.first());
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 3, 4};
        System.out.println(minimumDeviation(num));
    }
}
