package com.jason.jingsai.double72;

public class C5997 {
    public long[] sumOfThree(long num) {
        if (num % 3 != 0) {
            return new long[0];
        }
        return new long[]{num / 3 - 1, num / 3, num / 3 + 1};
    }
}
