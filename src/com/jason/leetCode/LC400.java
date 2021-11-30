package com.jason.leetCode;

public class LC400 {
    public static int findNthDigit(int n) {
        //先确定是几位数
        int k = 0;
        long cur = 0, pre = 0;
        while (n > cur) {
            pre = cur;
            cur += 9 * (long) Math.pow(10, k++) * k;//这一位占多少个字符
        }
        // 确定了是几位，把前面的减掉
        n -= pre;
        // 然后就可以从 100……开始算了，每个数字，占k位
        int start = (int) Math.pow(10, k - 1);
        int num = start - 1 + (n + k - 1) / k;// 向下取整，能确定的数字是这个
        int count = n % k;// 第几位
        count = count == 0 ? k : count;
        return (num / (int) Math.pow(10, k - count)) % 10;
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(1000000000));
    }


}
