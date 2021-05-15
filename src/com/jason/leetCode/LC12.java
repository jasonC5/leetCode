package com.jason.leetCode;

public class LC12 {

    public static void main(String[] args) {
        String s = Solution.intToRoman(123);
        System.out.println(s);
    }

    static class Solution {
        public static String intToRoman(int num) {
            int alb[]={1000,900,500,400,100,90,50,40,10,9,5,4,1};
            String rom[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
            String res = "";
//            while (num > 0){
//                int j = 0;
//                for (int i = j; i < alb.length; i++) {
//                    if (num >= alb[i]) {
//                        num -= alb[i];
//                        res += rom[i];
//                        j = i;//优化，减少遍历次数
//                        break;
//                    }
//                }
//            }
            for (int i = 0; i < alb.length; i++) {
                while (num >= alb[i]){
                    num -= alb[i];
                    res += rom[i];
                }
                if (num ==0) {
                    break;
                }
            }
            return res;
        }
    }
}
