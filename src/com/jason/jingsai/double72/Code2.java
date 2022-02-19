package com.jason.jingsai.double72;

public class Code2 {
    public long[] sumOfThree(long num) {
        if(num == 0){
            return new long[]{- 1, 0, 1};
        }
        if (num < 3 || num % 3 != 0) {
            return new long[0];
        }
        return new long[]{num / 3 - 1, num / 3, num / 3 + 1};
    }
}
