package com.jason.leetCode;

public class LC1791 {
    public int findCenter(int[][] edges) {
        return edges[1][0] == edges[0][0] || edges[1][0] == edges[0][1] ? edges[1][0] : edges[1][1];
    }
}
