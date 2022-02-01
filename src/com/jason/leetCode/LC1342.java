package com.jason.leetCode;

public class LC1342 {
    public static int numberOfSteps(int num) {
        int count = 0;
        while (num != 0) {
            num = (num & 1) == 1 ? (num - 1) : num >> 1;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOfSteps(8));
    }
}
