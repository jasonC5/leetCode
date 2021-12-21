package com.jason.leetCode;

import java.util.*;

/**
 * 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。为了方便起见，我们将编号为 x 的人简称为 "person x "。
 * <p>
 * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。另给你一个整数数组 quiet ，其中 quiet[i] 是 person i 的安静值。richer 中所给出的数据 逻辑自恰（也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
 * <p>
 * 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
 * 输出：[5,5,2,5,4,5,6,7]
 * 解释：
 * answer[0] = 5，
 * person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
 * 唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
 * 但是目前还不清楚他是否比 person 0 更有钱。
 * answer[7] = 7，
 * 在所有拥有的钱肯定不少于 person 7 的人中（这可能包括 person 3，4，5，6 以及 7），
 * 最安静（有较低安静值 quiet[x]）的人是 person 7。
 * 其他的答案也可以用类似的推理来解释。
 * 示例 2：
 * <p>
 * 输入：richer = [], quiet = [0]
 * 输出：[0]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/loud-and-rich
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC851 {
    // 有向无环图，拓扑排序  【错】
    public static int[] loudAndRich_error(int[][] richer, int[] quiet) {
        int length = quiet.length;
        int[] ans = new int[length];
        int[] in = new int[length];
        // 入度为0的队列
        Deque<Integer> queue = new LinkedList<>();
        Map<Integer, List<Integer>> lessThenMap = new HashMap<>();
        for (int[] r : richer) {
            //入度+1
            in[r[1]]++;
            List<Integer> lessThen = lessThenMap.getOrDefault(r[0], new ArrayList<>());
            lessThen.add(r[1]);
            lessThenMap.put(r[0], lessThen);
        }
        for (int i = 0; i < length; i++) {
            if (in[i] == 0) {
                queue.addLast(i);
            }
            ans[i] = i;
        }
        int min = Integer.MAX_VALUE;
        int minMan = -1;
        while (!queue.isEmpty()) {
            Integer man = queue.pollFirst();
            if (quiet[man] < min) {
                min = quiet[man];
                minMan = man;
            }
            ans[man] = minMan;
            List<Integer> list = lessThenMap.get(man);
            if (list != null) {
                for (Integer next : list) {
                    if (--in[next] == 0) {
                        queue.addLast(next);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] richer = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
        System.out.println(Arrays.toString(loudAndRich(richer, quiet)));
    }

    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[][] w = new int[n][n];
        int[] in = new int[n];
        for (int[] r : richer) {
            int a = r[0], b = r[1];
            w[a][b] = 1; in[b]++;
        }
        Deque<Integer> d = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
            if (in[i] == 0) d.addLast(i);
        }
        while (!d.isEmpty()) {
            int t = d.pollFirst();
            for (int u = 0; u < n; u++) {
                if (w[t][u] == 1) {
                    if (quiet[ans[t]] < quiet[ans[u]]) ans[u] = ans[t];
                    if (--in[u] == 0) d.addLast(u);
                }
            }
        }
        return ans;
    }

//    作者：AC_OIer
//    链接：https://leetcode-cn.com/problems/loud-and-rich/solution/gong-shui-san-xie-tuo-bu-pai-xu-yun-yong-ylih/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
