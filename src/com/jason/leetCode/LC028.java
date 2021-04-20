package com.jason.leetCode;

public class LC028 {

    public static void main(String[] args) {
        String haystack = "hello", needle = "ll";
        final int i = Solution.strStr(haystack, needle);
        System.out.println(i);
    }

    public static class Solution {

        //TODO 进阶解法：KMP

        public static int strStr(String haystack, String needle) {
            if (haystack == null){
                return -1;
            } else if (needle == null) {
                return 0;
            }
            // return haystack.indexOf(needle);
            final int hLength = haystack.length();
            final int nLength = needle.length();
            if (nLength == 0) {
                return 0;
            }else if (nLength > hLength){
                return -1;
            }
            retry:
            for (int i = 0; i <= hLength - nLength; i++) {
//                final String substring = haystack.substring(i, i + nLength);
//                if (substring.equals(needle)){
//                    return i;
//                }
                for (int j = 0; j < nLength; j++) {
                    if (haystack.charAt(i+j) != needle.charAt(j)){
                        continue retry;
                    }
                }
                return i;
            }
            return -1;
        }
    }
}
