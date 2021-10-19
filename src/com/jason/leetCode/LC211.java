package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC211 {
    public class PrefixTreeNode {
        public PrefixTreeNode[] nexts;
        public boolean end;

        public PrefixTreeNode() {
            nexts = new PrefixTreeNode[26];
            end = false;
        }
    }

    class WordDictionary {

        PrefixTreeNode root;

        public WordDictionary() {
            root = new PrefixTreeNode();
        }

        public void addWord(String word) {
            char[] chars = word.toCharArray();
            PrefixTreeNode reader = root;
            for (char c : chars) {
                int index = c - 'a';
                if (reader.nexts[index] == null) {
                    reader.nexts[index] = new PrefixTreeNode();
                }
                reader = reader.nexts[index];
            }
            reader.end = true;
        }

        public boolean search(String word) {
            char[] chars = word.toCharArray();
            Queue<PrefixTreeNode> queue = new LinkedList<>();
            queue.offer(root);
            // 查找前缀树
            for (int i = 0; i < chars.length; i++) {
                int size = queue.size();
                char c = chars[i];
                for (int j = 0; j < size; j++) {
                    PrefixTreeNode node = queue.poll();
                    if (c == '.') {// 指代所有字母，全加进去
                        for (PrefixTreeNode next : node.nexts) {
                            if (next != null) {
                                queue.offer(next);
                            }
                        }
                    } else {// 特定的字母
                        if (node.nexts[c - 'a'] != null) {
                            queue.offer(node.nexts[c - 'a']);
                        }
                    }
                }
                if (queue.size() == 0) {
                    return false;
                }
            }
            // 只要其中有一个是字符串的end，就返回true
            while (!queue.isEmpty()) {
                PrefixTreeNode poll = queue.poll();
                if (poll.end) {
                    return true;
                }
            }
            // 没匹配到完整的字符串，false
            return false;
        }
    }
}
