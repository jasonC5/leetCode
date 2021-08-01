package com.jason.leetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author JasonC5
 */
public class LC1337 {


    public static void main(String[] args) {
//        int[][] mat =
//                {{1, 1, 0, 0, 0},
//                        {1, 1, 1, 1, 0},
//                        {1, 0, 0, 0, 0},
//                        {1, 1, 0, 0, 0},
//                        {1, 1, 1, 1, 1}};
        int[][] mat = {{1, 0, 0}, {1, 0, 0}, {0, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 1, 1}, {1, 1, 0}};
//        int k = 3;
        int k = 7;
        int[] ans = kWeakestRows2(mat, k);
        Arrays.stream(ans).boxed().forEach(System.out::println);
    }

    public static final int OFFSET = 16;
    public static final int MASK = (1 << OFFSET) - 1;

    public static int[] kWeakestRows(int[][] mat, int k) {
        //前k小问题
        int[] ans = new int[k];
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < mat.length; i++) {
            int[] ints = mat[i];
            int count = 0;
            for (int j = 0; j < ints.length && ints[j] == 1; j++) {
                count++;
            }
            heap.offer(count << OFFSET | i);
        }
        for (int i = 0; i < k; i++) {
            ans[i] = MASK & heap.poll();
        }
        return ans;
    }

    public static int[] kWeakestRows2(int[][] mat, int k) {
        //前k小问题
        int[] container = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            int[] ints = mat[i];
            int count = 0;
            for (int j = 0; j < ints.length && ints[j] == 1; j++) {
                count++;
            }
            container[i] = count << OFFSET | i;
        }
        int[] ans = new int[k];
        if (k > mat.length) {
            k = mat.length;
        }
        int kMin = process(container, 0, mat.length - 1, k - 1);
        int idx = 0;
        for (int i = 0; i < k; i++) {
            ans[idx++] = container[i];
        }
        Arrays.sort(ans);
        for (int i = 0; i < k; i++) {
            ans[i] = ans[i] & MASK;
        }
        return ans;
    }

    private static int process(int[] arr, int left, int right, int idx) {
        if (left == right) {
            return arr[left];
        }
        int randomIdx = (int) (Math.random() * (right - left + 1)) + left;
        int random = arr[randomIdx];
        //range[0] = left，range[0] = right
        int[] range = partition(arr, left, right, random);
        if (range[0] > idx) {
            right = range[0] - 1;
        } else if (range[1] < idx) {
            left = range[1] + 1;
        } else {
            return random;
        }
        return process(arr, left, right, idx);
    }

    private static int[] partition(int[] arr, int left, int right, int num) {
        int l = left - 1;
        int r = right + 1;
        int cur = left;
        while (cur < r) {
            if (arr[cur] < num) {
                swap(arr, cur++, ++l);
            } else if (arr[cur] > num) {
                swap(arr, cur, --r);
            } else {
                cur++;
            }
        }
        return new int[]{l + 1, r - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }


}
