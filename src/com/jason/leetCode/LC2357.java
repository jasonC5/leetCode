package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenjieaj
 * @date 2023/2/24 9:50:40
 * @description
 */
public class LC2357 {
    public static int minimumOperations(int[] nums) {
//        Set<Integer> set = new HashSet<>();
//        for (int num : nums) {
//            if (num != 0){
//                set.add(num);
//            }
//        }
//        return set.size();
        int[] cnt = new int[101];
        int ans = 0;
        for (int num : nums) {
            if (num != 0) {
                ans += (cnt[num]++ == 0 ? 1 : 0);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] num = {1,5,0,3,5};
        System.out.println(minimumOperations(num));
    }
}
