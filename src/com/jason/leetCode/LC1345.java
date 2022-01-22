package com.jason.leetCode;

import java.util.*;

public class LC1345 {
    public static int minJumps0(int[] arr) {
        int len = arr.length;
        if (len == 1) {
            return 0;
        }
        Map<Integer, List<Integer>> sameValListMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            List<Integer> sameValList = sameValListMap.getOrDefault(arr[i], new ArrayList<>());
            sameValList.add(i);
            sameValListMap.put(arr[i], sameValList);
        }
        int[] dp = new int[len];
        // 最多的步数，也就数组长度，而且能防止用Integer.MAX在计算时溢出
        Arrays.fill(dp, len);
        dp[len - 1] = 0;
        List<Integer> same = sameValListMap.get(arr[len - 1]);
        for (Integer j : same) {
            if (j != len - 1) {
                dp[j] = 1;
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            // 前面一个，后面一个，以及和自己值相同的，找步数最小的
            dp[i] = Math.min(dp[i], dp[i + 1] + 1);
            if (i != 0) {
                dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            }
            List<Integer> list = sameValListMap.get(arr[i]);
            for (Integer j : list) {
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
            // 自己确定了之后，再更新一次相同值的步数
            for (Integer j : list) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[] arr = {11, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        System.out.println(minJumps(arr));
    }

    public static int minJumps(int[] arr) {
        int len = arr.length;
        if (len == 1) {
            return 0;
        }
        Map<Integer, List<Integer>> sameValListMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            List<Integer> sameValList = sameValListMap.getOrDefault(arr[i], new ArrayList<>());
            sameValList.add(i);
            sameValListMap.put(arr[i], sameValList);
        }
        boolean[] visited = new boolean[len];
        int step = 0;
        //队列
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(len - 1);
        visited[len - 1] = true;
        // 按层遍历
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();
                if (poll == 0) {
                    return step;
                }
                if (poll - 1 >= 0 && !visited[poll - 1]) {
                    queue.offer(poll - 1);
                    visited[poll - 1] = true;
                }
                if (poll + 1 != len && !visited[poll + 1]) {
                    queue.offer(poll + 1);
                    visited[poll + 1] = true;
                }
                List<Integer> list = sameValListMap.get(arr[poll]);
                if (list.size() > 1) {
                    for (int next : list) {
                        if (next != poll && visited[next]) {
                            break;
                        }
                        if (next != poll && !visited[next]) {
                            queue.offer(next);
                            visited[next] = true;
                        }
                    }
                }
            }
            step++;
        }
        return len - 1;
    }
}
