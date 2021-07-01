package com.jason.leetCode;

import java.util.*;

/**
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 * <p>
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, relation = [[0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4]], k = 3
 * <p>
 * 输出：3
 * <p>
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3, relation = [[0,2},{2,1]], k = 2
 * <p>
 * 输出：0
 * <p>
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LCP07 {

    public static void main(String[] args) {
        int n = 5, k = 3;
        int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
//        int n = 3, k = 2;
//        int[][] relation = {{0, 2}, {2, 1}};
        System.out.println(numWays(n, relation, k));
    }

    public static int numWays(int n, int[][] relation, int k) {
//        if (n <= 1) {
//            return 0;
//        }
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        for (int[] ints : relation) {
            List<Integer> targetList = edgeMap.getOrDefault(ints[0], new ArrayList<>());
            targetList.add(ints[1]);
            edgeMap.put(ints[0], targetList);
        }
        int ans = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty() && k != 0) {
            k--;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                List<Integer> list = edgeMap.getOrDefault(poll, new ArrayList<>());
                for (Integer next : list) {
                    if (k == 0 && next == n - 1) {
                        ans++;
                    }
                    queue.add(next);
                }
            }
        }
        return ans;
    }


}
