package com.jason.leetCode;

import java.util.*;

/**
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
 * <p>
 * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
 * <p>
 * lefti 是第 i 座建筑物左边缘的 x 坐标。
 * righti 是第 i 座建筑物右边缘的 x 坐标。
 * heighti 是第 i 座建筑物的高度。
 * 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * <p>
 * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-skyline-problem
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC218 {

    //把下落的点，映射到一个连续的区间内，节省空间
    public static HashMap<Integer, Integer> index(int[][] positions) {
        //存放所有的方块的起始位置和结束位置
        TreeSet<Integer> pos = new TreeSet<>();
        for (int[] cube : positions) {
            pos.add(cube[0]);//起始位置
            pos.add(cube[1]);//结束位置
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        //将这些点映射到0~pos.size的区间内，节省空间
        for (Integer index : pos) {
            map.put(index, ++count);
        }
        return map;
    }

    public static HashMap<Integer, Integer> reverseIndex(HashMap<Integer, Integer> index) {
        HashMap<Integer, Integer> reverseIndex = new HashMap<>();
        for (Integer key : index.keySet()) {
            reverseIndex.put(index.get(key), key);
        }
        return reverseIndex;
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        HashMap<Integer, Integer> points = index(buildings);
        HashMap<Integer, Integer> reverseIndex = reverseIndex(points);//反向索引
        Integer size = points.size();
        int arr[] = new int[size + 2];
        for (int[] cube : buildings) {
            int left = points.get(cube[0]);
            int right = points.get(cube[1]);
            for (int i = left; i < right; i++) {
                arr[i] = Math.max(arr[i], cube[2]);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        int pre = 0;
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            if (ans == null || pre != val) {
                List<Integer> point = new ArrayList<>();
                point.add(reverseIndex.get(i));
                point.add(arr[i]);
                ans.add(point);
            }
            pre = val;
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] segment = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
//        int[][] segment = {{0,3,3}, {1,5,3}, {2,4,3}, {3,7,3}};
        int[][] segment = {{2,13,10}, {10,17,25}, {12,20,14}};
        //遍历线段，暴力解
        System.out.println(getSkyline(segment));
        //堆
        System.out.println(getSkyline2(segment));
        //线段树
        System.out.println(getSkyline3(segment));
    }

    //只处理update的线段树
    private static class SegmentTree {
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int pointCount) {
            int len = pointCount + 1;
            max = new int[len << 2];
            change = new int[len << 2];
            update = new boolean[len << 2];
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }
            //没包住，需要下发
            int mid = (r + l) >> 1;
            //需要先把懒缓存处理下去，再处理子任务
            pushDown(rt, mid - l + 1, r - mid);
            //任务下发
            int leftMax = Integer.MIN_VALUE;
            if (L <= mid) {
                leftMax = query(L, R, l, mid, rt << 1);
            }
            int rightMax = Integer.MIN_VALUE;
            if (R > mid) {
                rightMax = query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.max(leftMax, rightMax);
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            //包住了，懒缓存
            if (l >= L && r <= R) {
                //如果区间内之前没有处理过，直接赋值，如果已经更改过，则保留最大的
                if (!update[rt] || C > max[rt]) {
                    update[rt] = true;
                    change[rt] = C;
                    max[rt] = C;
                }
                return;
            }
            //没包住，需要下发
            int mid = (r + l) >> 1;
            //需要先把懒缓存处理下去，再处理子任务
            pushDown(rt, mid - l + 1, r - mid);
            //缓存处理完了，处理子任务
            //任务下发
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            //任务处理完了，处理本节点下的最大值
            pushUp(rt);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            //把之前的任务往下发
            if (update[rt]) {
                int val = change[rt];
                update[rt] = false;
                change[rt] = 0;
                if (!update[rt << 1] || val > max[rt << 1] ) {
                    update[rt << 1] = true;
                    change[rt << 1] = val;
                    max[rt << 1] = val;
                }
                if (!update[rt << 1 | 1] || val > max[rt << 1 | 1]) {
                    update[rt << 1 | 1] = true;
                    change[rt << 1 | 1] = val;
                    max[rt << 1 | 1] =  val;
                }
            }
        }
    }

    private static List<List<Integer>> getSkyline3(int[][] segments) {
        HashMap<Integer, Integer> points = index(segments);
        //反向索引
        HashMap<Integer, Integer> reverseIndex = reverseIndex(points);
        int pointCount = reverseIndex.size();
        SegmentTree segmentTree = new SegmentTree(pointCount);
        for (int[] segment : segments) {
            //找到映射的点位
            Integer left = points.get(segment[0]);
            Integer right = points.get(segment[1]);
            segmentTree.update(left, right - 1, segment[2], 1, pointCount, 1);
        }
        List<List<Integer>> ans = new ArrayList<>();
        int pre = 0;
        for (int i = 1; i <= pointCount; i++) {
            int query = segmentTree.query(i, i, 1, pointCount, 1);
            if (ans.isEmpty() || pre != query) {
                List<Integer> point = new ArrayList<>();
                point.add(reverseIndex.get(i));
                point.add(query);
                ans.add(point);
            }
            pre = query;
        }
        return ans;
    }

    //堆+扫描
    public static List<List<Integer>> getSkyline2(int[][] buildings) {
        //结束点从小到大排序
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);
        //所有的点
        List<Integer> boundaries = new ArrayList<Integer>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        //排序
        Collections.sort(boundaries);
        //结果
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        //线段数，当前第几个线段
        int n = buildings.length, idx = 0;
        //遍历所有的点
        for (int boundary : boundaries) {
            //还有线段，并且线段经过该点，加入小根堆
            while (idx < n && buildings[idx][0] <= boundary) {
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }
            //小根堆中弹出已经过去的线段【包括包含终点的】
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }
            //此时小根堆中最上面的，就是该点的高度
            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            //第一个点，或者和前一个点的高度不同，加入答案
            if (ans.size() == 0 || maxn != ans.get(ans.size() - 1).get(1)) {
                ans.add(Arrays.asList(boundary, maxn));
            }
        }
        return ans;
    }


}
