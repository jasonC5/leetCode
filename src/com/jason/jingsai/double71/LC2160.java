package com.jason.jingsai.double71;

import java.util.Arrays;

public class LC2160 {
    public int minimumSum(int num) {
        int[] i = new int[4];
        i[0] = num % 10;
        i[1] = (num / 10) % 10;
        i[2] = (num / 100) % 10;
        i[3] = num / 1000;
        Arrays.sort(i);
        return (i[0] + i[1]) * 10 + i[2] + i[3];
    }
}
