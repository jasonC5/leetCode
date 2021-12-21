package com.jason.leetCode;

import java.util.*;

public class LC1610 {
    // 排序+滑动窗口，每个点并不重要，只看夹角，需要用到：Math.atan2(y , x);     与x轴的夹角 = Math.atan2(y2-y1,x2-x1)*180/Math.PI    ，后面是常数，所以只需要前面的就能排序
    public static int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        //
        int samePointNum = 0;// 和location在同一个点位上有几个点
        int locationX = location.get(0);
        int locationY = location.get(1);
        int num = points.size();
//        PriorityQueue<Double> minHeap = new PriorityQueue<>();
//        double[] polarDegrees = new double[2 * num];
        List<Double> polarDegrees = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int x = points.get(i).get(0);
            int y = points.get(i).get(1);
            if (x == locationX && y == locationY) {
                samePointNum++;
                continue;
            }
            Double delegate = Math.atan2(y - locationY, x - locationX);
            polarDegrees.add(delegate);
        }
//        Arrays.sort(polarDegrees, 0, num);
        Collections.sort(polarDegrees);
        num = polarDegrees.size();
        // 兼容转一圈的情况
        for (int i = 0; i < num; i++) {
            polarDegrees.add(polarDegrees.get(i) + 2 * Math.PI);
        }
        int max = 0;
        int right = 0;
        //转化成和堆内统一度量单位
        double toDegree = angle * Math.PI / 180;
//        while (!minHeap.isEmpty()) {
//            Double poll = minHeap.poll();
//            Double curr = poll + toDegree;
//
//        }
        for (int i = 0; i < num; i++) {
            Double curr = polarDegrees.get(i) + toDegree;
            while (right < polarDegrees.size() && polarDegrees.get(right) <= curr) {
                right++;
            }
            max = Math.max(max, right - i);
        }
        return max + samePointNum;
    }

    public static int visiblePoints2(List<List<Integer>> points, int angle, List<Integer> location) {
        int sameCnt = 0;
        List<Double> polarDegrees = new ArrayList<>();
        int locationX = location.get(0);
        int locationY = location.get(1);
        for (int i = 0; i < points.size(); ++i) {
            int x = points.get(i).get(0);
            int y = points.get(i).get(1);
            if (x == locationX && y == locationY) {
                sameCnt++;
                continue;
            }
            Double degree = Math.atan2(y - locationY, x - locationX);
            polarDegrees.add(degree);
        }
        Collections.sort(polarDegrees);

        int m = polarDegrees.size();
        for (int i = 0; i < m; ++i) {
            polarDegrees.add(polarDegrees.get(i) + 2 * Math.PI);
        }

        int maxCnt = 0;
        int right = 0;
        double toDegree = angle * Math.PI / 180;
        for (int i = 0; i < m; ++i) {
            Double curr = polarDegrees.get(i) + toDegree;
            while (right < polarDegrees.size() && polarDegrees.get(right) <= curr) {
                right++;
            }
            maxCnt = Math.max(maxCnt, right - i);
        }
        return maxCnt + sameCnt;
    }

    public static void main(String[] args) {
        List<List<Integer>> points = new ArrayList<>();
        points.add(Arrays.asList(2, 1));
        points.add(Arrays.asList(2, 2));
        points.add(Arrays.asList(3, 4));
        points.add(Arrays.asList(1, 1));
        int angle = 90;
        List<Integer> location = Arrays.asList(1, 1);
        System.out.println(visiblePoints(points, angle, location));
        System.out.println(visiblePoints2(points, angle, location));
    }

}
