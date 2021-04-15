package com.jason.leetCode;

public class LC213 {
    public static void main(String[] args) {
        int[] nums = {0,0};

        final int rob = Solution.rob(nums);
        System.out.println(rob);//
    }

    public static class Solution {
        public static int rob(int[] nums) {
            //暴力递归解法梳理：
            //1.起点，从0开始，那么只能到n-2结束  从1开始，到n-1结束
            //2.偷当前这家，或者偷下一家，+2
            if (nums == null|| nums.length == 0) {
                return 0;
            } else if (nums.length==1){
                return nums[0];
            }
            final int length = nums.length;
            int max = Math.max(steal2(nums,0,length-2), steal2(nums,1,length-1));
            return max;
        }

        public static int steal(int[] nums, int start, int end){
            if (start > end) {
                return 0;
            }
            //偷这家，或者偷下一家
//            return Math.max(nums[start]+steal(nums,start+2,end), start+1>end?0:nums[start+1]+steal(nums,start+3,end));//超时了
            //跑到当前位置的时候，两种情况，偷，或者偷，偷则是往前两个位置偷的总和+本家金额，不偷则可以偷下一家=前一家为止的最大值
            int pointOne = nums[start];//第一个
            int pointTwo = Math.max(pointOne, start+1>end?Integer.MIN_VALUE:nums[start+1]);//第二个
            for (int i= start +2;i<=end;i++) {
                int temp = pointTwo;
                pointTwo = Math.max(pointOne + nums[i], pointTwo);
                pointOne = temp;
            }
            return pointTwo;
        }

        public static int steal2(int[] nums, int start, int end){
            int[] result = new int[end+1];
            result[start] = nums[start];
            if (start+1<=end) {
                result[start + 1] = Math.max(result[start], nums[start+1]);
                for (int i=start+2; i<=end;i++) {
                    result[i] = Math.max(result[i-1], result[i-2] + nums[i]);
                }
            }
            return result[end];
        }
    }

    public static class Solution2 {
        public static int rob(int[] nums) {
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            } else if (length == 2) {
                return Math.max(nums[0], nums[1]);
            }
            return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
        }

        public static int robRange(int[] nums, int start, int end) {
            int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
            for (int i = start + 2; i <= end; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }
    }
}

