package com.jason.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1442 {

    /**
     * 给你一个整数数组 arr 。
     *
     * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
     *
     * a 和 b 定义如下：
     *
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * 注意：^ 表示 按位异或 操作。
     *
     * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：arr = [2,3,1,6,7]
     * 输出：4
     * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
     * 示例 2：
     *
     * 输入：arr = [1,1,1,1,1]
     * 输出：10
     * 示例 3：
     *
     * 输入：arr = [2,3]
     * 输出：0
     * 示例 4：
     *
     * 输入：arr = [1,3,5,7,9]
     * 输出：3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1};
        int i = Solution.countTriplets(arr);
        System.out.println(i);
    }

    static class Solution {
        public static int countTriplets(int[] arr) {
            //1.dp[i] = arr[0] ^ arr[1] …… ^ arr[i-1];
            //2.a==b => arr[i] ^ arr[i+1] ^ …… ^arr[j-1] ^ arr[j] ^ arr[j+1] ^ …… ^arr[k-1] = 0;
            //3.==> dp[k+1] ^ dp [i] == 0
            //4.==> dp[k] == dp[i-1]; j可以为i到k中间任意一个数字：(i,k]
            //5.==>dp每有一个相等的数字，答案+k-i个

            int ans = 0;
            int length = arr.length;
            int[] dp = new int[length+1];
            dp[0] =0;
            for (int i = 0; i < length; i++) {
                dp[i+1] = dp[i] ^ arr[i];
            }
            Map<Integer, List<Integer>> sameMap = new HashMap<>();
            for (int k = 0; k < length+1; k++) {
                List<Integer> list = sameMap.get(dp[k]);
                if (list == null) {
                    list = new ArrayList<>();
                    list.add(k);
                    sameMap.put(dp[k], list);
                } else {
                    for (Integer con : list) {
                        ans += (k - con -1);//dp[i+1] = dp[0] ^ …… dp[i]
                    }
                    list.add(k);
                }
            }
            return ans;

//            int n = arr.length;
//            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
//            Map<Integer, Integer> total = new HashMap<Integer, Integer>();
//            int ans = 0, s = 0;
//            for (int k = 0; k < n; ++k) {
//                int val = arr[k];
//                if (cnt.containsKey(s ^ val)) {
//                    ans += cnt.get(s ^ val) * k - total.get(s ^ val);
//                }
//                cnt.put(s, cnt.getOrDefault(s, 0) + 1);
//                total.put(s, total.getOrDefault(s, 0) + k);
//                s ^= val;
//            }
//            return ans;
//
//            作者：LeetCode-Solution
//            链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/solution/xing-cheng-liang-ge-yi-huo-xiang-deng-sh-jud0/
//            来源：力扣（LeetCode）
//            著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        }
    }
}
