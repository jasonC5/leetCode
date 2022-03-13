package com.jason.jingsai.competition284;

import java.util.*;

/**
 * @author JasonC5
 */
public class Code1 {
    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        Set<Integer> idxs = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                for (int j = -k; j <= k; j++) {
                    if (j + i >= 0 && j + i < n) {
                        idxs.add(i + j);
                    }
                }
            }
        }
        List<Integer> ans = new ArrayList<>(idxs);
        Collections.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int[] n = {2, 2, 2, 2};
        int key = 2, k = 2;
        System.out.println(findKDistantIndices(n, key, k));
    }
}
