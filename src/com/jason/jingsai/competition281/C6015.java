package com.jason.jingsai.competition281;

import java.util.HashMap;
import java.util.Map;

/**
 * 6015. 统计可以被 K 整除的下标对数目
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums 和一个整数 k ，返回满足下述条件的下标对 (i, j) 的数目：
 *
 * 0 <= i < j <= n - 1 且
 * nums[i] * nums[j] 能被 k 整除。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4,5], k = 2
 * 输出：7
 * 解释：
 * 共有 7 对下标的对应积可以被 2 整除：
 * (0, 1)、(0, 3)、(1, 2)、(1, 3)、(1, 4)、(2, 3) 和 (3, 4)
 * 它们的积分别是 2、4、6、8、10、12 和 20 。
 * 其他下标对，例如 (0, 2) 和 (2, 4) 的乘积分别是 3 和 15 ，都无法被 2 整除。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4], k = 5
 * 输出：0
 * 解释：不存在对应积可以被 5 整除的下标对。
 *
 * https://leetcode-cn.com/problems/count-array-pairs-divisible-by-k/
 * @author JasonC5
 */
public class C6015 {

    public static long coutPairs(int[] nums, int k) {
        int length = nums.length;
        int cnt = 0;
        Map<Integer, Integer> gcdCntMap = new HashMap<>();
        for (int num : nums) {
            if (num % k == 0) {
                cnt++;
            } else {
                // 最大公因数
                int gcd = gcd(k, num);
                gcdCntMap.put(gcd, gcdCntMap.getOrDefault(gcd, 0) + 1);
            }
        }
        // 本身就能整除的，配上任何不能整除的数数都能整除
        long ans = (long) cnt * (length - cnt);
        // 本身能整除的配上能整除的
        ans += (long) cnt * (cnt - 1) >> 1;
        // 凑不能整除的，凑因子相乘如果能整除
        long tmp = 0;
        for (Integer key : gcdCntMap.keySet()) {
            if (key == 1) {
                continue;
            }
            Integer count = gcdCntMap.get(key);
            for (Integer nextKey : gcdCntMap.keySet()) {
                if ((key * nextKey) % k == 0) {
                    if (key.equals(nextKey)) {// 乘自己，只会算一遍，直接加
                        ans += (long) count * (count - 1) >> 1;
                    } else {
                        // 会计算两遍，放到一个临时变量里
                        tmp += (long) count * gcdCntMap.get(nextKey);
                    }
                }
            }
        }
        return ans + (tmp >> 1);
    }
    // 求最大公因数
    private static int gcd(int i, int j) {
        return j != 0 ? gcd(j, i % j) : i;
    }

    public static void main(String[] args) {
        int[] nums = {100, 17, 3, 77, 64, 74, 11, 43, 10, 37};
        int k = 20;
        System.out.println(coutPairs(nums, k));
    }
}
