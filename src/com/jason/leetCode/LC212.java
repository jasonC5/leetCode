package com.jason.leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC212 {

    public static class Node {
        public String str = null;
        public int end;
        public Node[] nexts;//数组写法--针对元素固定的场景

        public Node() {
            end = 0;
            nexts = new Node[26];//26个字母
        }
    }

    // 前缀树
    public static class PreTrie {
        Node root;

        public PreTrie() {
            this.root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node point = root;
            for (int i = 0; i < word.length(); i++) {
                //第几个
                if (point.nexts[chars[i] - 'a'] == null) {
                    point.nexts[chars[i] - 'a'] = new Node();
                }
                point = point.nexts[chars[i] - 'a'];
            }
            point.str = word;
            point.end++;//以改节点结尾+1
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                //第几个
                if ((node = node.nexts[chars[i] - 'a']) == null) {
                    return 0;
                }
            }
            return node.end;
        }

        //查询是否存在指定字符串为前缀的开头的字符串
        public int prefix(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node point = root;

            for (int i = 0; i < word.length(); i++) {
                //第几个
                if ((point = point.nexts[chars[i] - 'a']) == null) {
                    return 0;
                }
            }
            return 1;
        }

    }

    public static List<String> findWords(char[][] board, String[] words) {
        PreTrie preTrie = new PreTrie();
        for (String word : words) {
            preTrie.insert(word);
        }
        //board所有能生成的字符串，放进去
        Set<String> ans = new HashSet<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                boolean[][] visit = new boolean[board.length][board[0].length];
                find(board, visit, ans, preTrie, i, j, "");
            }
        }
        return new ArrayList<>(ans);
    }

    private static void find(char[][] board, boolean[][] visit, Set<String> ans, PreTrie preTrie, int row, int col, String pre) {
        //越界或者已经访问过
        if (row == -1 || row == board.length || col == -1 || col == board[0].length || visit[row][col]) {
            return;
        }
        String cur = pre + board[row][col];
        //前缀树断了
        if (preTrie.prefix(cur) == 0) {
            return;
        }
        if (preTrie.search(cur) > 0) {
            ans.add(cur);
        }
        // 标记为已访问--不能重复使用
        visit[row][col] = true;
        //四种情况
        find(board, visit, ans, preTrie, row + 1, col, cur);
        find(board, visit, ans, preTrie, row - 1, col, cur);
        find(board, visit, ans, preTrie, row, col + 1, cur);
        find(board, visit, ans, preTrie, row, col - 1, cur);
        // 改回未访问
        visit[row][col] = false;
    }

    public static void main(String[] args) {
        String[] words = {"oath", "pea", "eat", "rain"};
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        System.out.println(findWords(board, words));
    }
}
