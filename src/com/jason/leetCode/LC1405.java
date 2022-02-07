package com.jason.leetCode;

import java.util.PriorityQueue;

/**
 * 果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * <p>
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 * <p>
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 示例 2：
 * <p>
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 示例 3：
 * <p>
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *
 * <p>
 * 提示：
 * <p>
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-happy-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1405 {
    public static String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((i1, i2) -> i2[1] - i1[1]);
        if (a > 0) {
            maxHeap.offer(new int[]{0, a});
        }
        if (b > 0) {
            maxHeap.offer(new int[]{1, b});
        }
        if (c > 0) {
            maxHeap.offer(new int[]{2, c});
        }
        StringBuilder sb = new StringBuilder();
        int pre = -1, prePre = -1;
        while (!maxHeap.isEmpty()) {
            int[] poll = maxHeap.poll();
            if (poll[0] == pre && poll[0] == prePre) {//发生了连续三个相同的情况
                if (maxHeap.isEmpty()) {
                    return sb.toString();
                } else {
                    int[] curTop = maxHeap.poll();
                    sb.append((char) ('a' + curTop[0]));
                    if (--curTop[1] > 0) {
                        maxHeap.offer(curTop);
                    }
                    maxHeap.offer(poll);
                    prePre = pre;
                    pre = curTop[0];
                }
            } else {//不同，直接用
                sb.append((char) ('a' + poll[0]));
                if (--poll[1] > 0) {
                    maxHeap.offer(poll);
                }
                prePre = pre;
                pre = poll[0];
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestDiverseString(7, 1, 0));
    }
}
