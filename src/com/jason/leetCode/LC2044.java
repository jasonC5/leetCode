package com.jason.leetCode;

public class LC2044 {
    int max = Integer.MIN_VALUE;
    int count = 0;
    int[] nums;

    public int countMaxOrSubsets(int[] nums) {
        this.nums = nums;
        dfs(0, 0);
        return count;
    }

    private void dfs(int idx, int pre) {
        if (idx == nums.length) {//到最后了，收答案
            if (pre > max) {
                max = pre;
                count = 1;
            } else if (pre == max) {
                count++;
            }
            return;
        }
        dfs(idx + 1, pre);
        dfs(idx + 1, pre | nums[idx]);
    }

    public static void main(String[] args) {
        LC2044 o = new LC2044();
        int[] nums = {3, 1};
        System.out.println(o.countMaxOrSubsets(nums));
    }

}
