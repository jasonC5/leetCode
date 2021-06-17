package com.jason.leetCode;

public class LC374 {

    private static int ans;

    public static void main(String[] args) {
        int max = 1_000;
        int i1 = (int) (Math.random() * max);
        int i2 = (int) (Math.random() * max);
        int n = Math.max(i1, i2);
        ans = Math.min(i1, i2);
        int find = guessNumber(n);
        System.out.println(find + "_" + ans);
    }

    public static int guessNumber(int n) {
        //二分法
        int left = 0;
        while (left <= n) {
            int mid = left + (int) ((n - left) >> 1);
            int flag = guess(mid);
            if (flag == 0) {
                return mid;
            } else if (flag < 0) {
                //ans < mid
                n = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int guess(int num) {
        if (num == ans) {
            return 0;
        } else if (ans > num) {
            return 1;
        } else {
            return -1;
        }
    }
}
