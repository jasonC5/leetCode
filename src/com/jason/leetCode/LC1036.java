package com.jason.leetCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

/**
 * @author JasonC5
 */
public class LC1036 {
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked == null || blocked.length == 0) {
            return true;
        }
        int len = blocked.length;
        int maxArea = (len * len) >> 1;
        Set<Long> blockSet = new HashSet<>();
        for (int[] bk : blocked) {
            blockSet.add(((long) bk[0] << 32) | bk[1]);
        }
        // 只要bfs超过maxArea，就可以认为没有被blocked包围，只要两个点都不被包围，就一定可以走到
        // 或者两个点都被包围了，都在圈内，也能到达
        if (bfsCheck(blockSet, maxArea, new Stack<int[]>() {{ this.add(source); }}, new HashSet<Long>())
                ^ bfsCheck(blockSet, maxArea, new Stack<int[]>() {{ this.add(target); }}, new HashSet<Long>())) {
            return false;
        } else {
            return true;
        }
    }

    public static final int[] LOCATION = {1, 0, -1, 0, 1};

    private boolean bfsCheck(Set<Long> blockSet, int maxArea, Stack<int[]> stack, Set<Long> visited) {
        if (visited.size() > maxArea) {
            return true;
        } else if (stack.isEmpty()) {
            return false;
        }
        int[] pop = stack.pop();
        visited.add(((long) pop[0] << 32) | pop[1]);
        for (int i = 0; i < 4; i++) {
            int x = pop[0] + LOCATION[i];
            int y = pop[1] + LOCATION[i + 1];
            long delegate = ((long) x << 32) | y;
            if (x >= 0 && x < 1000000 && y >= 0 && y < 1000000 && !visited.contains(delegate) && !blockSet.contains(delegate)) {
                stack.push(new int[]{x, y});
            }
        }
        return bfsCheck(blockSet, maxArea, stack, visited);
    }

    public static void main(String[] args) {
        LC1036 lc1036 = new LC1036();
        int[][] blocked = {};
        int[] source = {0, 0}, target = {999999,999999};
        System.out.println(lc1036.isEscapePossible(blocked, source, target));
    }
}
