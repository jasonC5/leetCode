package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/7/11 9:03:50
 * @description
 */
public class LC676 {
    class MagicDictionary {
        Trie root;

        public MagicDictionary() {
            root = new Trie();
        }

        public void buildDict(String[] dictionary) {
            for (String str : dictionary) {
                int n = str.length();
                Trie node = root;
                for (int i = 0; i < n; i++) {
                    char c = str.charAt(i);
                    if (node.children[c - 'a'] == null) {
                        node.children[c - 'a'] = new Trie();
                    }
                    node = node.children[c - 'a'];
                }
                node.isLeaf = true;
            }
        }

        public boolean search(String searchWord) {
            return dfs(searchWord, 0, root, false);
        }

        private boolean dfs(String searchWord, int idx, Trie node, boolean changed) {
            if (idx == searchWord.length()) {
                return changed && node.isLeaf;
            }
            int next = searchWord.charAt(idx) - 'a';
            if (node.children[next] != null && dfs(searchWord, idx + 1, node.children[next], changed)) {
                return true;
            }
            if (!changed) {
                for (int i = 0; i < 26; i++) {
                    if (i != next && node.children[i] != null && dfs(searchWord, idx + 1, node.children[i], true)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    class Trie {
        Trie[] children;
        boolean isLeaf;

        public Trie() {
            this.children = new Trie[26];
            this.isLeaf = false;
        }
    }

}
