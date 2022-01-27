package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LC2013 {
    static class DetectSquares {
        // x相同时候，不同的y的分布
        Map<Integer, TreeSet<Integer>> xPoints;
        // x、y组成一个数字，每个点的数量
        Map<Integer, Integer> pointCount;

        public DetectSquares() {
            xPoints = new HashMap<>();
            pointCount = new HashMap<>();
        }

        public void add(int[] point) {
            // 统计点位
            TreeSet<Integer> ySet = xPoints.getOrDefault(point[0], new TreeSet<>((i1, i2) -> i1 - i2));
            ySet.add(point[1]);
            xPoints.put(point[0], ySet);
            // 统计点的个数
            int delegate = point[0] << 10 | point[1];
            Integer count = pointCount.getOrDefault(delegate, 0);
            pointCount.put(delegate, count + 1);
        }

        public int count(int[] point) {
            int ans = 0;
            TreeSet<Integer> points = xPoints.get(point[0]);
            if (points == null || points.size() == 0) {
                return 0;
            }
            for (Integer y : points) {
                // 重合的话面积为0，就不存在了
                if (y != point[1]) {
                    int len = y - point[1];
                    // 两种情况
                    Integer yCount = pointCount.get(point[0] << 10 | y);
                    if (yCount != null) {
                        int x1 = (point[0] + len) << 10;
                        ans += yCount * pointCount.getOrDefault(x1 | y, 0) * pointCount.getOrDefault(x1 | point[1], 0);
                        int x2 = (point[0] - len) << 10;
                        ans += yCount * pointCount.getOrDefault(x2 | y, 0) * pointCount.getOrDefault(x2 | point[1], 0);
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        System.out.println(detectSquares.count(new int[]{11, 10}));
        System.out.println(detectSquares.count(new int[]{14, 8}));
        detectSquares.add(new int[]{11, 2});
        System.out.println(detectSquares.count(new int[]{11, 10}));
    }
}
