package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定平面上n 对 互不相同 的点points ，其中 points[i] = [xi, yi] 。回旋镖 是由点(i, j, k) 表示的元组 ，其中i和j之间的距离和i和k之间的距离相等（需要考虑元组的顺序）。
 * <p>
 * 返回平面上所有回旋镖的数量。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 * <p>
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：points = [[1,1]]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-boomerangs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC447 {

    public static int numberOfBoomerangs(int[][] points) {
        if (points == null || points.length < 3) {
            return 0;
        }
        int length = points.length;
        int ans = 0;
        // 真对每一个点，作为i，有多少个回旋镖：以该点为圆心，同一半径的圆上，有多个点，则这个圆上的回旋镖数量为：A n 2
        // 计算每两个点之间的距离，然后找
        // 距离的平方
        long[][] instanceSquare = new long[length][length];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if(instanceSquare[i][j] == 0){
                    instanceSquare[i][j] = instanceSquare[j][i] = instance(points, i, j);
                }
            }
        }
        //每一行上面相同的元素有几个--每个点，对其他点的距离的平方
        for (long[] pointInstance : instanceSquare) {
            Map<Long, Integer> instanceMap = new HashMap<>();
            for (long instance : pointInstance) {
                Integer count = instanceMap.getOrDefault(instance, 0);
                instanceMap.put(instance, count + 1);
            }
            for (Long instance : instanceMap.keySet()) {
                Integer count = instanceMap.get(instance);
                if (count > 1) {
                    //有一个圆上有超过两个点
//                    ans += Amn(count, 2);
                    ans += count * (count - 1);
                }
            }

        }
        return ans;
    }

    //Amn
    private static int Amn(Integer m, int n) {
        long ans = 1;
        for (int i = m; i >= (m - n + 1); i--) {
            ans *= i;
        }
        return (int) ans;
    }

    // 两点之间，距离的平方
    private static long instance(int[][] points, int i, int j) {
        int[] pointI = points[i];
        int[] pointJ = points[j];
        return (long) (pointI[0] - pointJ[0]) * (pointI[0] - pointJ[0]) + (long) (pointI[1] - pointJ[1]) * (pointI[1] - pointJ[1]);
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {1, 0}, {2, 0}};
        System.out.println(numberOfBoomerangs(points));
        System.out.println(numberOfBoomerangs2(points));
//        System.out.println(numberOfBoomerangs3(points));
    }

    public static int numberOfBoomerangs2(int[][] points) {
        if (points == null || points.length < 3) {
            return 0;
        }
        int ans = 0;
        for (int[] pointI : points) {
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int[] pointJ : points) {
                // 这个点，针对其他点的距离 的平方
                int dis = (pointI[0] - pointJ[0]) * (pointI[0] - pointJ[0]) + (pointI[1] - pointJ[1]) * (pointI[1] - pointJ[1]);
                if (dis > 0) {
                    cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
                }
            }
            for (Integer instance : cnt.keySet()) {
                int count = cnt.get(instance);
                ans += count * (count - 1);
            }
        }
        return ans;
    }

//    public static int numberOfBoomerangs3(int[][] points) {
//        if (points == null || points.length < 3) {
//            return 0;
//        }
//        int ans = 0;
//        int length = points.length;
//        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
//        for (int i = 1; i < length; i++) {
//            for (int j = 0; j < i; j++) {
//                // 这个点，针对其他点的距离 的平方
//                int[] pointI = points[i], pointJ = points[j];
//                int dis = (pointI[0] - pointJ[0]) * (pointI[0] - pointJ[0]) + (pointI[1] - pointJ[1]) * (pointI[1] - pointJ[1]);
//                if (dis > 0) {
//                    cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
//                }
//            }
//        }
//        for (Integer instance : cnt.keySet()) {
//            int count = cnt.get(instance);
//            ans += count * (count - 1);
//        }
//        return ans;
//    }


}
