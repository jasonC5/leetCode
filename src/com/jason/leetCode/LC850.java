package com.jason.leetCode;

import java.util.*;

/**
 * 我们给出了一个（轴对齐的）二维矩形列表 rectangles 。
 * 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i 左下角的坐标，
 * (xi1, yi1) 是该矩形 左下角 的坐标， (xi2, yi2) 是该矩形 右上角 的坐标。
 * <p>
 * 计算平面中所有 rectangles 所覆盖的 总面积 。任何被两个或多个矩形覆盖的区域应只计算 一次 。
 * <p>
 * 返回 总面积 。因为答案可能太大，返回 109 + 7 的 模 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rectangle-area-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author chenjieaj
 * @date 2022/9/16 10:12:08
 * @description
 */
public class LC850 {
    static final int MOD = 1000000007;

    public static int rectangleArea1(int[][] rectangles) {
        Set<Integer> xSet = new HashSet<>();
        for (int[] rectangle : rectangles) {
            xSet.add(rectangle[0]);
            xSet.add(rectangle[2]);
        }
        // 离散化,双向索引
        Map<Integer, Integer> idxMap = new HashMap<>();// 真实值-->代表值
        Map<Integer, Integer> xMap = new HashMap<>();// 代表值-->真实值
        Integer[] integers = xSet.toArray(new Integer[0]);
        Arrays.sort(integers);
        int n = integers.length;
        for (int i = 0; i < n; i++) {
            xMap.put(i, integers[i]);
            idxMap.put(integers[i], i);
        }
        // 可以用线段树
        int[] top = new int[n];
        int[] bottom = new int[n];
        Arrays.fill(bottom, Integer.MAX_VALUE);
        for (int[] rectangle : rectangles) {
            int start = idxMap.get(rectangle[0]), end = idxMap.get(rectangle[2]);
            for (int i = start; i < end; i++) {
                top[i] = Math.max(top[i], rectangle[3]);
                bottom[i] = Math.min(bottom[i], rectangle[1]);
            }
        }
        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
            if (bottom[i] == Integer.MAX_VALUE) {
                continue;
            }
            ans += (long) (top[i] - bottom[i]) * (xMap.get(i + 1) - xMap.get(i));
        }
        return (int) (ans % MOD);
    }

    public static void main(String[] args) {
//        int[][] rectangles = {{0, 0, 1000000000, 1000000000}};
//        int[][] rectangles = {{0, 0, 1, 1}, {2, 2, 3, 3}};
        int[][] rectangles = {{25, 20, 70, 27}, {68, 80, 79, 100}, {37, 41, 66, 76}};
        System.out.println(rectangleArea(rectangles));
    }

    // 扫描线
    public static int rectangleArea(int[][] rs) {
        List<Integer> list = new ArrayList<>();
        for (int[] info : rs) {
            list.add(info[0]); list.add(info[2]);
        }
        Collections.sort(list);
        long ans = 0;
        for (int i = 1; i < list.size(); i++) {
            int a = list.get(i - 1), b = list.get(i), len = b - a;
            if (len == 0) continue;
            List<int[]> lines = new ArrayList<>();
            for (int[] info : rs) {
                if (info[0] <= a && b <= info[2]) lines.add(new int[]{info[1], info[3]});
            }
            Collections.sort(lines, (l1, l2)-> l1[0] != l2[0] ? l1[0] - l2[0] : l1[1] - l2[1]);
            long tot = 0, l = -1, r = -1;
            for (int[] cur : lines) {
                if (cur[0] > r) {
                    tot += r - l;
                    l = cur[0]; r = cur[1];
                } else if (cur[1] > r) {
                    r = cur[1];
                }
            }
            tot += r - l;
            ans += tot * len;
            ans %= MOD;
        }
        return (int) ans;
    }
}
