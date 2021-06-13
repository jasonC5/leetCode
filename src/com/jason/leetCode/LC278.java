package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC278 {

    public static int BadVersion;

    public static void main(String[] args) {
        int times = 1000;
        for (int i = 0; i < times; i++) {
            int maxVersion = 99;
            BadVersion = (int) (Math.random() * maxVersion + 1);
            int findVersion = firstBadVersion(maxVersion);
            if (findVersion != BadVersion){
                System.out.println("Filed!!! findVersion = "+findVersion+", BadVersion="+BadVersion );
                return;
            }
        }
        System.out.println("Complete!!");

    }

    public static int firstBadVersion(int n) {
        //二分法
        int left = 0, right = n;
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean isBadVersion(int mid) {
        return mid >= BadVersion;//版本10出的bug
    }


}
