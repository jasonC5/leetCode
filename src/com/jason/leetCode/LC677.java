package com.jason.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 * <p>
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *  
 * <p>
 * 示例：
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/map-sum-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC677 {
    // 前缀树+哈希表
    static class MapSum {

        PrefixTreeNode root;
        HashMap<String, Info> values;

        public static class PrefixTreeNode {
            PrefixTreeNode[] nexts;
            int sum;

            public PrefixTreeNode() {
                nexts = new PrefixTreeNode[26];
                sum = 0;
            }
        }

        public static class Info {
            int value;
            List<PrefixTreeNode> nodeList;

            public Info(int value, List<PrefixTreeNode> nodeList) {
                this.value = value;
                this.nodeList = nodeList;
            }
        }

        public MapSum() {
            root = new PrefixTreeNode();
            values = new HashMap<>();
        }

        public void insert(String key, int val) {
            if (values.containsKey(key)) {
                Info info = values.get(key);
                int sur = val - info.value;
                info.value = val;
                // 更新前缀树节点
                for (PrefixTreeNode prefixTreeNode : info.nodeList) {
                    prefixTreeNode.sum += sur;
                }
            } else {
                // 没出现过这个字符串，重新构建节点
                List<PrefixTreeNode> nodeList = new ArrayList<>();
                nodeList.add(root);
                root.sum += val;
                PrefixTreeNode cur = root;
                char[] chars = key.toCharArray();
                for (char aChar : chars) {
                    if (cur.nexts[aChar - 'a'] == null) {// 新节点
                        cur.nexts[aChar - 'a'] = new PrefixTreeNode();
                        cur = cur.nexts[aChar - 'a'];
                        cur.sum = val;
                    } else {// 已有节点
                        cur = cur.nexts[aChar - 'a'];
                        cur.sum += val;
                    }
                    nodeList.add(cur);
                }
                Info info = new Info(val, nodeList);
                values.put(key, info);
            }
        }

        public int sum(String prefix) {
            char[] chars = prefix.toCharArray();
            PrefixTreeNode cur = root;
            for (int i = 0; i < chars.length; i++) {
                if (cur.nexts[chars[i] - 'a'] == null) {
                    return 0;
                } else {
                    cur = cur.nexts[chars[i] - 'a'];
                }
            }
            return cur.sum;
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        mapSum.insert("apple", 2);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
        System.out.println(mapSum.sum("appl"));
    }

}
