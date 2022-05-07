package com.jason.leetCode;

import java.util.*;

/**
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * <p>
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * <p>
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 * <p>
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * <p>
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * 输出：3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Administrator
 */
public class LC433 {

    public static final char[] CONTENTS = {'A', 'C', 'G', 'T'};

    public static int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        Set<String> bankSet = new HashSet<>();
        for (String str : bank) {
            bankSet.add(str);
        }
        if (!bankSet.contains(end)) {
            return -1;
        }
        // 已访问的不重复访问
        Set<String> visited = new HashSet<>();
        int level = -1;
        while (!queue.isEmpty()) {
            // 按层遍历
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                visited.add(cur);
                if (cur.equals(end)) {
                    return level;
                }
                // 遍历每一个位置是否需要变化，以及是否能变化
                for (int j = 0; j < 8; j++) {
                    for (char c : CONTENTS) {
                        String next = cur.substring(0, j) + c;
                        if (j != 7) {
                            next += cur.substring(j + 1, 8);
                        }
                        if (bankSet.contains(next) && !visited.contains(next)) {
                            queue.add(next);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        String start = "AACCGGTT", end = "AACCGGTA";
//        String[] bank = {"AACCGGTA"};
        String start = "AACCGGTT", end = "AACCGCTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
//        String start = "AAAAACCC", end = "AACCCCCC";
//        String[] bank = {"AAAACCCC","AAACCCCC","AACCCCCC"};
//        String start = "AACCGGTT", end = "AAACGGTA";
//        String[] bank = {"AACCGATT", "AACCGATA", "AAACGATA", "AAACGGTA"};
        System.out.println(minMutation(start, end, bank));
    }
}
