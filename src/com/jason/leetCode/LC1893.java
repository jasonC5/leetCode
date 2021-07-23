package com.jason.leetCode;

public class LC1893 {

    //暴力
    public boolean isCovered(int[][] ranges, int left, int right) {
        jump:
        for (int i = left; i <= right; i++) {
            //只要有一个数字不在ranges内，就失败
            for (int[] range : ranges) {
                //在其中一个里面，就不用验了
                if (range[0] <= i && range[1] >= i) {
                    continue jump;
                }
            }
            //遍历完了，没找到一个匹配的
            return false;
        }
        return true;
    }

    //差分数组
    public boolean isCovered2(int[][] ranges, int left, int right) {

        return false;
    }
}
