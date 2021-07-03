package com.jason.leetCode;

import java.util.*;

/**
 * * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "tree"
 * <p>
 * 输出:
 * "eert"
 * <p>
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 * <p>
 * 输入:
 * "cccaaa"
 * <p>
 * 输出:
 * "cccaaa"
 * <p>
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 * <p>
 * 输入:
 * "Aabb"
 * <p>
 * 输出:
 * "bbAa"
 * <p>
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC451 {

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
    }

    static class Node {
        char c;
        int count;

        public Node(char c) {
            this.c = c;
            this.count = 0;
        }
    }

    // 加强堆
    public static String frequencySort(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Node> countMap = new HashMap<>();
        for (char c : chars) {
            Node node = countMap.getOrDefault(c, new Node(c));
            node.count++;
            countMap.put(c, node);
        }
        PriorityQueue<Node> heap = new PriorityQueue<Node>((n1, n2) -> n2.count - n1.count);
        for (Node node : countMap.values()) {
            heap.offer(node);
        }

        StringBuilder ans = new StringBuilder();
        while (!heap.isEmpty()) {
            Node poll = heap.poll();
            for (int i = 0; i < poll.count; i++) {
                ans.append(poll.c);
            }
        }
        return ans.toString();
    }

}
