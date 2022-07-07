package com.jason.leetCode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2022/7/7 9:05:33
 * @description
 */
public class LC648 {
    public static String replaceWords(List<String> dictionary, String sentence) {
        // 字典树
        Trie root = new Trie(false);
        for (String s : dictionary) {
            Trie cur = root;
            int n = s.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                sb.append(c);
                cur.son.putIfAbsent(c, new Trie(false));
                // 往下推
                cur = cur.son.get(c);
            }
            // 最后一个是叶子结点
            cur.isLeaf = true;
            cur.curStr = sb.toString();
        }
        String[] sens = sentence.split(" ");
        for (int i = 0; i < sens.length; i++) {
            sens[i] = replace(sens[i], root);
        }
        return String.join(" ", sens);
    }

    private static String replace(String sen, Trie root) {
        Trie cur = root;
        for (char c : sen.toCharArray()) {
            if (cur.son.containsKey(c)) {
                cur = cur.son.get(c);
                if (cur.isLeaf) {
                    return cur.curStr;
                }
            } else {
                return sen;
            }
        }
        return sen;
    }

    private static class Trie {
        public Map<Character, Trie> son;
        public String curStr;
        public boolean isLeaf;

        public Trie() {
            son = new HashMap<>();
        }

        public Trie(boolean isLeaf) {
            this();
            this.isLeaf = isLeaf;
        }
    }

    public static void main(String[] args) {
        String[] dictionary = new String[]{"cat", "bat", "rat"};
        String sentence = "the cattle was rattled by the battery";
        System.out.println(replaceWords(Arrays.asList(dictionary), sentence));
    }
}
