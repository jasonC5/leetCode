package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

public class LC13 {
    public static void main(String[] args) {
        int abl = Solution.romanToInt("LVIII");
        System.out.println(abl);
    }

    static class Solution {
        public static int romanToInt(String s) {
            Map<Character,Integer> dir = new HashMap();
            dir.put('I', 1);
            dir.put('V', 5);
            dir.put('X', 10);
            dir.put('L', 50);
            dir.put('C', 100);
            dir.put('D', 500);
            dir.put('M', 1000);

            int res = 0;
            int length = s.length();
            for (int i = 0; i < length; i++) {
                int val = dir.get(s.charAt(i));
                if (i< length-1 && val < dir.get(s.charAt(i+1)) ) {//前面比后面还小，减【因为只有4/9这么展示，前面只有一个数】
                    res -= val;
                } else {
                    res += val;
                }
            }
            return res;
        }
    }
}
