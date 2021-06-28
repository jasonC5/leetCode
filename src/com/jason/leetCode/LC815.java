package com.jason.leetCode;

import java.util.*;

/**
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * <p>
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 * <p>
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * 示例 2：
 * <p>
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * 输出：-1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bus-routes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC815 {

    public static void main(String[] args) {
//        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        int[][] routes = {{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}};

        int source = 0, target = 9000;
        System.out.println(numBusesToDestination(routes, source, target));
    }

    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        //广度优先遍历
        //1.HashMap，每个站点有多少条公交路过
        //2.Set,记录已经走过的站点
        //3.Queue，本次有可能坐到的站点
        int step = 0;
        //从站点X，能坐到的站点
        Map<Integer, List<Integer>> stationMap = new HashMap<>();

        int busNum = routes.length;
        //填充stationMap
        for (int i = 0; i < busNum; i++) {
            int[] busStations = routes[i];
            for (int j = 0; j < busStations.length; j++) {
                //途径的车站
                int station = busStations[j];
                List<Integer> stationBuses = stationMap.getOrDefault(station, new ArrayList<>());
                for (int busStation : busStations) {
                    stationBuses.add(busStation);
                }
                //去重
                HashSet set = new HashSet(stationBuses);
                stationBuses.clear();
                stationBuses.addAll(set);
                stationMap.put(station, stationBuses);
            }
        }
        //已经扫过的车站
        Set<Integer> seen = new HashSet<>();
        seen.add(source);
        //队列
        Queue<Integer> q = new LinkedList();
        q.add(source);
        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Integer station = q.poll();
                //从这个站点，能一次坐车到达的站点
                List<Integer> nexts = stationMap.get(station);
                if (nexts != null && nexts.size() > 0) {
                    if (nexts.contains(target)) {
                        return step;
                    } else {
                        for (Integer next : nexts) {
                            if (!seen.contains(next)) {
                                seen.add(next);
                                q.offer(next);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
