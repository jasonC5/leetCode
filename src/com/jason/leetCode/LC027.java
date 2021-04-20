package com.jason.leetCode;

public class LC027 {
    //和026一样的问题，只不过一个是和全面一个元素比，一个是和指定常量比

    class Solution {
        public int removeElement(int[] nums, int val) {
            if(nums == null || nums.length == 0){
                return 0;
            }
            int seek = 0;
            for(int i=0;i<nums.length;i++){
                if (nums[i] != val) {
                    nums[seek++] = nums[i];
                }
            }
            return seek;
        }
    }
}
