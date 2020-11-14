package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1 {
    static class Solution {
        public static int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int i = 0; i<nums.length ; i++){
                if(map.containsKey(nums[i])){
                    return new int[]{map.get(nums[i]),i};
                }
                map.put(target-nums[i],i);
            }
            return null;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int [] answer = Solution.twoSum(nums, target);
//        Stream.of(answer).mapToInt(x->Integer.valueOf(x)).forEach(x->System.out.println(x));
        Stream.of(Arrays.asList(answer)).forEach(System.out::println);
    }


}
