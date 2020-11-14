package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode3 {
        public static int lengthOfLongestSubstring(String s) {
            Integer max= 0;
            Integer padding = 0;//浮动窗口--起始点
            Map<Character, Integer> lengthMap = new HashMap();
            for(int i = 0; i< s.length() ; i++){//浮动窗口--结束点
                Character character = s.charAt(i);
                if (lengthMap.containsKey(character)) {
                    //有相同的值了，当前字符最大无重复 = 当前位置-重复位置
                    padding = Math.max(padding, lengthMap.get(character));//中间可能有重复值，不能一直从起始位置算起，跑到最近有重复的后面
                }
                lengthMap.put(character, i+1);
                max = Math.max(max, i-padding+1);
            }
            return max;
        }
        public static void main(String[] args) {
            String a = "abba";
            System.out.println(lengthOfLongestSubstring(a));
        }
}
