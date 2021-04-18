package com.jason.leetCode;

public class LC026 {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        final int i = Solution.removeDuplicates2(nums);
        System.out.println(nums);
        System.out.println(i);
    }

    public static class Solution {
        public static int removeDuplicates2(int[] nums) {//方案2，把一个数组当成两个数组来用，遍历指针往后移动了，前面的就没用了，就用来存放去重后的结果
            if(nums == null || nums.length == 0){
                return 0;
            }
            int seek = 1;//第0个不用看
            for(int i=1;i<nums.length;i++){
                if (nums[i] != nums[i-1]) {
                    nums[seek++] = nums[i];
                }
            }
            return seek;
        }

        public static int removeDuplicates(int[] nums) {//方案1，一个一个往后走，有重复的就删掉
            if(nums == null || nums.length == 0){
                return 0;
            }
            int tmp = nums[0], length = nums.length;
            int seek = 1;
            for(int i=1;i<length;i++){
                if(tmp == nums[seek]){
                    //重复，后面的往前移，指针不动
                    removeElement(nums,seek);
                } else {
                    //指针往后移动
                    tmp = nums[seek];
                    seek++;
                }
            }
            return seek;
        }

        public static void removeElement(int[] nums, int seek){
            for(int i = seek; i<(nums.length-1);i++){
                nums[i] = nums[i+1];
            }
        }
    }
}
