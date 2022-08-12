package com.jason.leetCode;

import java.util.*;

/**
 * @author chenjieaj
 * @date 2022/8/9 17:08:09
 * @description
 */
public class LC2368 {
    public static int reachableNodes(int n, int[][] edges, int[] restricted) {
        Set<Integer> res = new HashSet<>();
        for (int i : restricted) {
            res.add(i);
        }
        List<Integer>[] connect = new List[n];
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            if (res.contains(i) || res.contains(j)) {
                continue;
            }
            List<Integer> listI = connect[i];
            List<Integer> listJ = connect[j];
            listI = listI == null ? new ArrayList() : listI;
            listJ = listJ == null ? new ArrayList() : listJ;
            listI.add(j);
            listJ.add(i);
            connect[i] = listI;
            connect[j] = listJ;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()){
            Integer poll = q.poll();
            visited.add(poll);
            if (connect[poll] != null) {
                for (Integer next : connect[poll]) {
                    if (!visited.contains(next)) {
                        q.add(next);
                    }
                }
            }
        }
        return visited.size();
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {{0,1}, {1,2}, {3,1}, {4,0}, {0,5}, {5,6}};
        int[] restricted = {4,5};
        System.out.println(reachableNodes(n, edges, restricted));
    }
}
