package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LC969 {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        int length = arr.length;
        for (int i = length - 1; i > 0; i--) {
            int maxIdx = 0;
            // 找到0~i中最大的位置
            for (int j = 0; j <= i; j++) {
                if (arr[j] >= arr[maxIdx]) {
                    maxIdx = j;
                }
            }
            if (maxIdx != i) {// 如果本来就在最后的位置就不用换了
                ans.add(maxIdx + 1);
                ans.add(i + 1);
                reverse(arr, maxIdx);
                reverse(arr, i);
            }
        }
        return ans;
    }

    private void reverse(int[] arr, int idx) {
        int left = 0;
        while (left < idx) {
            arr[left] = arr[left] ^ arr[idx];
            arr[idx] = arr[left] ^ arr[idx];
            arr[left] = arr[left++] ^ arr[idx--];
        }
    }

    public static void main(String[] args) {
        System.out.println(new LC969().pancakeSort(new int[]{3, 2, 4, 1}));
    }


}
