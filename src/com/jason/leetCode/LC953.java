package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2022/5/17 8:48:25
 * @description
 */
public class LC953 {
    public static boolean isAlienSorted(String[] words, String order) {
        char[] chars = order.toCharArray();
        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            indexMap.put(chars[i], i);
        }
        next:
        for (int i = 1; i < words.length; i++) {
            String pre = words[i - 1];
            String cur = words[i];
            int len = Math.min(pre.length(), cur.length());
            for (int idx = 0; idx < len; idx++) {
                char pc = pre.charAt(idx);
                char cc = cur.charAt(idx);
                if (cc != pc) {
                    if (indexMap.get(cc) < indexMap.get(pc)) {
                        return false;
                    } else {
                        continue next;
                    }
                }
            }
            // 都遍历完了，说明公共长度的部分都相同，长度长的字典序高
            if (pre.length() > cur.length()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] ss = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(isAlienSorted(ss, order));

    }
}
