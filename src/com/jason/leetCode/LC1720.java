package com.jason.leetCode;

import java.util.Arrays;

public class LC1720 {
    public static void main(String[] args) {
        int[] encoded = {1,2,3};
        int first = 1;
        Arrays.stream(Solution.decode(encoded, first)).boxed().forEach(System.out::print);
    }

    static class Solution {
        public static int[] decode(int[] encoded, int first) {
            int[] result = new int[encoded.length +1];
            result[0] = first;
            for(int i = 1;i<result.length;i++){
                result[i] =  result[i - 1] ^ encoded[i - 1];
            }
            return result;
        }
    }
}
