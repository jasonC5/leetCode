package com.jason.jingsai.double73;

import java.util.*;

/**
 * @author JasonC5
 */
public class Code3 {
    static List<Integer>[] ansList;
    public static List<List<Integer>> getAncestors(int n, int[][] edges) {
        Map<Integer, List<Integer>> parentsMap = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        ansList = new List[n];
        for (int[] edge : edges) {
            List<Integer> parents = parentsMap.getOrDefault(edge[1], new ArrayList<>());
            parents.add(edge[0]);
            parentsMap.put(edge[1], parents);
        }
        for (int i = 0; i < n; i++) {
            List<Integer> ancestor = findAnc(parentsMap, ans, i);
            ancestor.sort((i1, i2) -> i1 - i2);
            ans.add(ancestor);
        }
        return ans;
    }

    private static List<Integer> findAnc(Map<Integer, List<Integer>> parentsMap, List<List<Integer>> ans, int i) {
        if (ansList[i] != null) {
            return ansList[i];
        }
        Set<Integer> anc = new HashSet<>();
        List<Integer> list = parentsMap.getOrDefault(i, new ArrayList<>());
        if (list.isEmpty()) {
            ansList[i] = list;
            return ansList[i];
        }
        for (Integer p : list) {
            if (anc.contains(p)) {
                continue;
            }
            if (ans.size() > p) {
                anc.addAll(ans.get(p));
            } else {
                anc.addAll(findAnc(parentsMap, ans, p));
            }
            anc.add(p);
        }
        ansList[i] = new ArrayList<>(anc);
        return ansList[i];
    }

    public static void main(String[] args) {
//        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}};
//        int n = 5;
        int[][] edges = {{0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}};
        int n = 8;
        System.out.println(getAncestors(n, edges));
    }
}
