package com.jason.jingsai.double72;

import java.util.HashMap;
import java.util.Map;

/**
 * 5999. 统计数组中好三元组数目
 * 给你两个下标从 0 开始且长度为 n 的整数数组 nums1 和 nums2 ，两者都是 [0, 1, ..., n - 1] 的 排列 。
 *
 * 好三元组 指的是 3 个 互不相同 的值，且它们在数组 nums1 和 nums2 中出现顺序保持一致。换句话说，如果我们将 pos1v 记为值 v 在 nums1 中出现的位置，pos2v 为值 v 在 nums2 中的位置，那么一个好三元组定义为 0 <= x, y, z <= n - 1 ，且 pos1x < pos1y < pos1z 和 pos2x < pos2y < pos2z 都成立的 (x, y, z) 。
 *
 * 请你返回好三元组的 总数目 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [2,0,1,3], nums2 = [0,1,2,3]
 * 输出：1
 * 解释：
 * 总共有 4 个三元组 (x,y,z) 满足 pos1x < pos1y < pos1z ，分别是 (2,0,1) ，(2,0,3) ，(2,1,3) 和 (0,1,3) 。
 * 这些三元组中，只有 (0,1,3) 满足 pos2x < pos2y < pos2z 。所以只有 1 个好三元组。
 * 示例 2：
 *
 * 输入：nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
 * 输出：4
 * 解释：总共有 4 个好三元组 (4,0,3) ，(4,0,2) ，(4,1,3) 和 (4,1,2) 。
 *
 * https://leetcode-cn.com/problems/count-good-triplets-in-an-array/
 * @author JasonC5
 */
public class C5999 {
    public long goodTriplets1(int[] nums1, int[] nums2) {
        // 相当于在num1 和 nums2 画三条不相交的线
        Map<Integer, Integer> nums2Idx = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            nums2Idx.put(nums2[i], i);
        }
        Map<Long, Long> cache = new HashMap<>();
        return process1(nums1, nums2Idx, nums1.length, 0, -1, 3, cache);
    }

    private long process1(int[] nums1, Map<Integer, Integer> nums2Idx, int length, int idx1, int idx2, int rest, Map<Long, Long> cache) {
        if (rest == 0) {
            return 1L;
        }
        long key = ((long) idx1 << 30 | idx2) << 30 | rest;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        long ans = 0;
        for (int i = idx1; i < length; i++) {
            Integer newIdx2 = nums2Idx.get(nums1[i]);
            if (newIdx2 > idx2) {
                ans += process1(nums1, nums2Idx, nums1.length, i + 1, newIdx2, rest - 1, cache);
            }
        }
        cache.put(key, ans);
        return ans;
    }

    int lowbit(int x) {
        return x & (-x);
    }

    void updata(int i, long k, long[] c) {    //在i位置加上k
        int n = c.length;
        while (i < n) {
            c[i] += k;
            i += lowbit(i);
        }
    }

    long getsum(int i, long[] c) {        //求A[1 - i]的和
        long res = 0;
        while (i > 0) {
            res += c[i];
            i -= lowbit(i);
        }
        return res;
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            hm.put(nums1[i], i);
        }
        int[] pos = new int[n];//nums2对应位置上的数，再nums1的位置
        for (int i = 0; i < n; i++) {
            pos[i] = hm.get(nums2[i]);
        }
        // System.out.println(Arrays.toString(pos));
        long[][] c = new long[3][n + 1];
        // int[][] dp = new int[3][n+1];
        for (int i = 0; i < n; i++) {
            updata(pos[i] + 1, 1, c[0]);
            updata(pos[i] + 1, getsum(pos[i], c[0]), c[1]);
            updata(pos[i] + 1, getsum(pos[i], c[1]), c[2]);
        }
        return getsum(n, c[2]);
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 0, 1, 3, 2}, nums2 = {4, 1, 0, 2, 3};
        System.out.println(new C5999().goodTriplets(nums1, nums2));
    }

}
