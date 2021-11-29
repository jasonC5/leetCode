package com.jason.leetCode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LC519 {
    static class Solution1 {
        int total;
        int[] container;
        int rest;
        int m, n;
        Random rand = new Random();

        public Solution1(int m, int n) {
            this.m = m;
            this.n = n;
            rest = total = m * n;
            container = new int[total];
            for (int i = 0; i < total; i++) {
                container[i] = i;
            }
        }

        public int[] flip() {
            int random = rand.nextInt(rest);// 0 ~ rest-1
            int index = container[random];
            // 把已经翻转的，挪到最后，缩小可翻转的下标集合的返回
            container[random] = container[rest - 1];
            container[--rest] = index;
            return new int[]{index / n, index % n};
        }

        public void reset() {
            // 全部重置为0，等价于，全部可翻转，重置剩余可翻转数量
            rest = total;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution(10000, 10000);
        solution.flip();
        solution.flip();
        solution.flip();
        solution.reset();
        solution.flip();

    }

    static class Solution {
        int m, n, total;
        Set<Integer> flipped;
        Random rand = new Random();

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
            total = m * n;
            flipped = new HashSet<>();
        }

        public int[] flip() {
            int a = rand.nextInt(total);// 0 ~ rest-1
            // 往两边找
            int b = a - 1;
            while (a < total && flipped.contains(a)) {
                a++;
            }
            while (b >= 0 && flipped.contains(b)) {
                b--;
            }
            int index = a < total ? a : b;
            flipped.add(index);
            return new int[]{index / n, index % n};
        }

        public void reset() {
            flipped.clear();
        }
    }

}
