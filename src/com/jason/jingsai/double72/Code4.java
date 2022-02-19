package com.jason.jingsai.double72;

import java.util.HashMap;
import java.util.Map;

public class Code4 {
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
        System.out.println(new Code4().goodTriplets(nums1, nums2));
    }

}
