package com.jason.leetCode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *  给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
 * <p>
 * 实现 SummaryRanges 类：
 * <p>
 * SummaryRanges() 使用一个空数据流初始化对象。
 * void addNum(int val) 向数据流中加入整数 val 。
 * int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * 输出：
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 * <p>
 * 解释：
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // 返回 [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC352 {

    public static class SummaryRanges {
        TreeMap<Integer, Integer> leftMap;//左边界map
        TreeMap<Integer, Integer> rightMap;//右边界map
        Set<Integer> exists;//已存在数字

        public SummaryRanges() {
            leftMap = new TreeMap<>();
            rightMap = new TreeMap<>();
            exists = new HashSet<>();
        }

        public void addNum(int val) {
            if (exists.contains(val)) {
                //已存在
                return;
            }
            exists.add(val);
            if (rightMap.containsKey(val - 1) && leftMap.containsKey(val + 1)) {//需要合并
                Integer left = rightMap.get(val - 1);
                Integer right = leftMap.get(val + 1);
                rightMap.remove(val - 1);
                leftMap.remove(val + 1);
                rightMap.put(right, left);
                leftMap.put(left, right);
            } else if (rightMap.containsKey(val - 1)) {
                Integer left = rightMap.get(val - 1);
                rightMap.remove(val - 1);
                rightMap.put(val, left);
                leftMap.put(left, val);
            } else if (leftMap.containsKey(val + 1)) {
                Integer right = leftMap.get(val + 1);
                leftMap.remove(val + 1);
                leftMap.put(val, right);
                rightMap.put(right, val);
            } else {
                leftMap.put(val, val);
                rightMap.put(val, val);
            }
        }

        public int[][] getIntervals() {
            int size = leftMap.size();
            int[][] ans = new int[size][2];
            int index = 0;
            for (Map.Entry<Integer, Integer> entry : leftMap.entrySet()) {
                ans[index][0] = entry.getKey();
                ans[index++][1] = entry.getValue();
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(6);
        printIntervals(summaryRanges.getIntervals());
        summaryRanges.addNum(6);
        printIntervals(summaryRanges.getIntervals());
        summaryRanges.addNum(0);
        printIntervals(summaryRanges.getIntervals());
        summaryRanges.addNum(4);
        printIntervals(summaryRanges.getIntervals());
        summaryRanges.addNum(8);
        printIntervals(summaryRanges.getIntervals());
        summaryRanges.addNum(7);
        printIntervals(summaryRanges.getIntervals());
        summaryRanges.addNum(6);
        printIntervals(summaryRanges.getIntervals());
        summaryRanges.addNum(4);
        printIntervals(summaryRanges.getIntervals());
        summaryRanges.addNum(7);
        printIntervals(summaryRanges.getIntervals());
        summaryRanges.addNum(5);
        printIntervals(summaryRanges.getIntervals());

    }

    public static void printIntervals(int[][] interval) {
        for (int[] ints : interval) {
            System.out.print("[" + ints[0] + " , " + ints[1] + "] ");
        }
        System.out.println();
    }

}
