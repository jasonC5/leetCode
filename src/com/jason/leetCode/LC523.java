package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JasonC5
 */
public class LC523 {

    public static void main(String[] args) {
        int[] nums = {23,2,6,4,7};
        boolean b = checkSubarraySum(nums, 13);
        System.out.println(b);
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        //前缀和+哈希表
        int length = nums.length;
        if (length < 2) {
            return false;
        }
//        int[] sum = new int[length+1];
        //key = 前缀和的余数【要找的是倍数，不用保存整个前缀和，只要余数能抵消，就是倍数】， value = 下标
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0,-1);
        int checkSum = 0;
        for (int i = 0; i < length; i++) {
            checkSum = (checkSum + nums[i]) % k;
            if (sumMap.containsKey(checkSum)) {
                if ((i - sumMap.get(checkSum)) > 1){
                    return true;
                }
            } else {
                sumMap.put(checkSum, i);
            }
        }
        return false;
    }
}
