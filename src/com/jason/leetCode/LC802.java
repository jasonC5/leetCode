package com.jason.leetCode;

import java.util.*;

/**
 * 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
 * <p>
 * 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
 * <p>
 * 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
 * <p>
 * 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-eventual-safe-states
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC802 {

    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
//        int[][] graph = {{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}};
        System.out.println(eventualSafeNodes(graph));
        System.out.println(eventualSafeNodes2(graph));
    }


    //拓扑排序
    public static List<Integer> eventualSafeNodes2(int[][] graph) {
        //出度
        int[] out = new int[graph.length];
        //反向索引，指向该点的点的集合
        Map<Integer, List<Integer>> reverseIndex = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            out[i] = graph[i] == null ? 0 : graph[i].length;
            if (graph[i] != null && graph[i].length > 0) {
                for (int point : graph[i]) {
                    List<Integer> inList = reverseIndex.getOrDefault(point, new ArrayList<>());
                    inList.add(i);
                    reverseIndex.put(point, inList);
                }
            }
        }
        //遍历出度，直到找不到出度为0的点
        List<Integer> ans = new ArrayList<>();
        while (true) {
            boolean find = false;
            for (int index = 0; index < out.length; index++) {
                if (out[index] == 0) {
                    find = true;
                    ans.add(index);
                    out[index] = -1;
                    List<Integer> inList = reverseIndex.getOrDefault(index, new ArrayList<>());
                    for (Integer inPoint : inList) {
                        out[inPoint]--;
                    }
                }
            }
            if (!find) {
                break;
            }
        }
        ans.sort((i1, i2) -> i1 - i2);
        return ans;
    }

    public static List<Integer> eventualSafeNodes(int[][] graph) {
        //1.有终点：入度>0，出度==0
        //2.从该点出发无环
        //3.从该点出发能到达终点

        //答案:1.终点【出度为0的点】，2.自己能到达的所有的点都是安全点，那么自己也是安全点
        //深度优先遍历，如果到底了，那么底部是安全点，往上返回，
        //如果出现环了，那么必不是安全点，且整个环上的都不是安全点，
        //如果自己能到达的所有的点，都是安全点，那么自己也是安全点
        int[] cache = new int[graph.length];//0 未处理 1 安全点 -1 非安全点
        Set<Integer> set = new HashSet();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (checkSafePoint(graph, cache, i, set)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private static Boolean checkSafePoint(int[][] graph, int[] cache, int index, Set<Integer> set) {
        if (cache[index] != 0) {
            return cache[index] == 1;
        } else if (set.contains(index)) {//环
            cache[index] = -1;
            return false;
        }
        set.add(index);
        int[] next = graph[index];
        if (next == null || next.length == 0) {
            //出度为0
            cache[index] = 1;
            return true;
        }
        Boolean safePoint = true;
        for (int j = 0; j < next.length; j++) {
            safePoint &= checkSafePoint(graph, cache, next[j], set);
        }
        cache[index] = safePoint ? 1 : -1;
        return cache[index] == 1;
    }
}
