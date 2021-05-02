package com.jason.leetCode;

import java.util.*;
import java.util.stream.Stream;

/**
 * 你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。
 *
 * 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
 *
 * 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
 *
 * 示例 1：
 * 输入：wall = {{1,2,2,1},{3,1,2},{1,3,2},{2,4},{3,1,2},{1,3,1,1}}
 * 输出：2
 * 
 * 示例 2：
 * 输入：wall = {{1},{1},{1}}
 * 输出：3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/brick-wall
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LC554 {
    /**
     * 方案1：
     * 数组中每个数字代表每个砖块长度，此砖块长度+前面所有砖块长度=本砖块右缝隙离左边距的距离，暂且称为绝对距离
     * 类似动态规划，算出每个砖块的的绝对距离【除去最后一个，最后一个是右边界】
     * 每一行一个List<Integer>,遍历所有List，某一元素出现的最多，说明这个地方的缝隙最多假如是x，穿过的砖块就越少n-x
     * 方案2：（方案1优化）
     * 不用每一行一个List，直接一个Map，key=缝隙的位置，value=出现的次数，找到最大的x，得到结果n-x
     */

    public static void main(String[] args) {
//        List<List<Integer>> num = new ArrayList<>();
//        int[][]a = {{1,2,2,1},{3,1,2},{1,3,2},{2,4},{3,1,2},{1,3,1,1}};
//        Arrays.stream(a).
//        List<int[]> ints = Arrays.asList(a[0]);
//        num.add();
    }

    static class Solution {
        public static int leastBricks(List<List<Integer>> wall) {
            Map<Integer , Integer> countMap = new HashMap<>();
            for (List<Integer> list : wall) {
                Integer width = 0;
                for (int i = 0; i < list.size()-1; i++) {
                    width += list.get(i);
                    countMap.put(width, countMap.getOrDefault(width, 0) + 1);
                }
            }
            //遍历得到value最大的
            int max = 0;
            if(countMap.size() > 0) {
                max = countMap.values().stream().mapToInt(x -> x).max().getAsInt();
            }
            return wall.size() - max;
        }
    }
}
