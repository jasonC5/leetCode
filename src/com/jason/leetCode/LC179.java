package com.jason.leetCode;

import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//一个数字数组，组成能表达的最大的数字
public class LC179 {
    //思路：两个数字组成一个新的数字，产生的结果：有一个大有一个小，或者两个一样大，那么按照大的数字排列两个数字，作为一个排序策略如何？
    // 这个排序策略，放到一堆数字里面，排好之后理拼接，论上就是能得到的最大数字
    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        final String s = Solution.largestNumber(nums);
        System.out.println(s);
    }

    public static class Solution {
        public static String largestNumber(int[] nums) {
            if (nums == null || nums.length==0) {
                return "0";
            } else if (nums.length == 1) {
                return nums[0]+"";
            }
//            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
//            String result = list.stream().sorted((x1, x2) -> {
//                String sum1= ""+x1+x2;
//                String sum2 = ""+x2+x1;
//                return Long.compare(Long.valueOf(sum2),Long.valueOf(sum1));
//            }).map(String::valueOf).collect(Collectors.joining());
//            if (result.startsWith("0")){
//                return "0";
//            } else {
//                return result;
//            }
            long sum1,sum2;
            int tmp;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length-1; j++) {
                    String str1 = ""+ nums[j] + nums[j+1];
                    String str2 = ""+ nums[j+1] + nums[j];
                    sum1 = Long.valueOf(str1);
                    sum2 = Long.valueOf(str2);
                    if (sum1 < sum2) {
                        //换顺序之后更大了，换
                        tmp = nums[j];
                        nums[j] = nums[j+1];
                        nums[j+1] = tmp;
                    }
                }
            }
            if (nums[0] == 0) {
                return "0";
            }
            String ans = "";
            for (int i = 0; i <nums.length; i++) {
                ans += nums[i];
            }
            return ans;
        }
    }
}
