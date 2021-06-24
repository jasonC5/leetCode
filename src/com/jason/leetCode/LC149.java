package com.jason.leetCode;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * 示例 2：
 * <p>
 * <p>
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * points 中的所有点 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-points-on-a-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC149 {

    public static int maxPoints(int[][] points) {
        //求每两个点的曲率，曲率相同的点，就在一条直线上(错，从同一个点出发)，放入Hash表，最后拿Hash表中的最大元素
        if (points.length < 3) {
            return points.length;
        }
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int partMax = 1;
            HashMap<Double, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                Double x = (double) (points[j][0] - points[i][0]) / (double) (points[j][1] - points[i][1]);
                if (x.equals(Double.NEGATIVE_INFINITY)) {
                    x = Double.POSITIVE_INFINITY;
                } else if (x.equals(-0.0D) || x.equals(0.0D)) {
                    x = Double.NaN;
                }
                Integer put = map.put(x, map.getOrDefault(x, 1) + 1);
                put = put == null ? 2 : put + 1;
                partMax = Math.max(put, partMax);
            }
            max = Math.max(max, partMax);
        }
        return max;
    }

    public static void main(String[] args) {
//        int[][] points = {{2, 3}, {3, 3}, {-5, 3}};
        int[][] points = {{0, 1}, {0, 0}, {0, 4}, {0, -2}, {0, -1}, {0, 3}, {0, -4}};
        int i = maxPoints(points);
        System.out.println(i);
    }
}
