package com.jason.leetCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenjieaj
 * @date 2023/2/2 10:55:57
 * @description
 */
public class LC1129 {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        Map<Integer, List<Integer>> redNextMap = new HashMap<>();
        Map<Integer, List<Integer>> blueNextMap = new HashMap<>();
        for (int[] redEdge : redEdges) {
            List<Integer> next = redNextMap.getOrDefault(redEdge[0], new ArrayList<>());
            next.add(redEdge[1]);
            redNextMap.put(redEdge[0], next);
        }
        for (int[] redEdge : blueEdges) {
            List<Integer> next = blueNextMap.getOrDefault(redEdge[0], new ArrayList<>());
            next.add(redEdge[1]);
            blueNextMap.put(redEdge[0], next);
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = 0;
        int len = 0;
        Map<Integer, List<Integer>>[] container = new Map[]{redNextMap, blueNextMap};
        Queue<Integer> redQ = new LinkedList<>();
        redQ.add(0);
        Queue<Integer> blueQ = new LinkedList<>();
        blueQ.add(0);
        while (len <= n << 1) {
            len++;
            if (!redQ.isEmpty()) {
                int size = redQ.size();
                for (int i = 0; i < size; i++) {
                    Integer poll = redQ.poll();
                    Map<Integer, List<Integer>> nextMap = container[len & 1];
                    List<Integer> nextList = nextMap.get(poll);
                    if (nextList == null) {
                        continue;
                    }
                    for (Integer next : nextList) {
                        if (ans[next] == -1) {
                            ans[next] = len;
                        } else {
                            ans[next] = Math.min(ans[next], len);
                        }
                        redQ.add(next);
                    }
                }
            }
            if (!blueQ.isEmpty()) {
                int size = blueQ.size();
                for (int i = 0; i < size; i++) {
                    Integer poll = blueQ.poll();
                    Map<Integer, List<Integer>> nextMap = container[(len + 1) & 1];
                    List<Integer> nextList = nextMap.get(poll);
                    if (nextList == null) {
                        continue;
                    }
                    for (Integer next : nextList) {
                        if (ans[next] == -1) {
                            ans[next] = len;
                        } else {
                            ans[next] = Math.min(ans[next], len);
                        }
                        blueQ.add(next);
                    }
                }
            }
        }
        return ans;
    }
}
