package com.jason.leetCode;

import java.util.*;

/**
 * 你一个有n个节点的 有向无环图（DAG），请你找出所有从节点 0到节点 n-1的路径并输出（不要求按特定顺序）
 * <p>
 * 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
 * <p>
 * 译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：graph = [[1,2],[3],[3],[]]
 * 输出：[[0,1,3],[0,2,3]]
 * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 * 示例 3：
 * <p>
 * 输入：graph = [[1],[]]
 * 输出：[[0,1]]
 * 示例 4：
 * <p>
 * 输入：graph = [[1,2,3],[2],[3],[]]
 * 输出：[[0,1,2,3],[0,2,3],[0,3]]
 * 示例 5：
 * <p>
 * 输入：graph = [[1,3],[2],[3],[]]
 * 输出：[[0,1,2,3],[0,3]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC797 {

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        ArrayList<Integer> preList = new ArrayList<>();
        return process(graph, map, 0);
    }

    private static List<List<Integer>> process(int[][] graph, Map<Integer, List<List<Integer>>> map, int index) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> preList = new ArrayList<>();
        preList.add(index);
        if (index == graph.length - 1) {
            list.add(preList);
            map.put(index, list);
            return list;
        }
        if (map.containsKey(index)) {
            return map.get(index);
        }
        int[] next = graph[index];
        if (next == null || next.length == 0) {
            //无法到达
            map.put(index, null);
            return null;
        }
        for (int num : next) {
            List<List<Integer>> process = process(graph, map, num);
            if (process != null && process.size() > 0) {
                for (List<Integer> integerList : process) {
                    ArrayList<Integer> ways = new ArrayList<>();
                    ways.add(index);
                    ways.addAll(integerList);
                    list.add(ways);
                }
            }
        }
        map.put(index, list);
        return list;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{1, 2}, {3}, {3}, {}};
        System.out.println(allPathsSourceTarget(graph));
    }


    public static List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        return process(graph, map, 0);
    }
}
