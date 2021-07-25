package com.jason.leetCode;

import java.util.*;

/**
 * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
 * <p>
 * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
 * <p>
 * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
 * <p>
 * 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1743 {

    public static void main(String[] args) {
        int[][] adjacentPairs = {{2, 1}, {3, 4}, {3, 2}};
        int[] ints = restoreArray(adjacentPairs);
        Arrays.stream(ints).boxed().forEach(System.out::println);
    }

    //想想并查集能否实现

    public static int[] restoreArray(int[][] adjacentPairs) {
        //相邻元素对的长度 = 原数组长度 -1
        int length = adjacentPairs.length + 1;
        int[] ans = new int[length];
        //哈希表
        Map<Integer, List<Integer>> cacheIdx = new HashMap<>();
        for (int[] adjacentPair : adjacentPairs) {
            int num1 = adjacentPair[0];
            int num2 = adjacentPair[1];
            List<Integer> pointIdx1 = cacheIdx.getOrDefault(num1, new ArrayList<>());
            pointIdx1.add(num2);
            cacheIdx.put(num1, pointIdx1);
            List<Integer> pointIdx2 = cacheIdx.getOrDefault(adjacentPair[1], new ArrayList<>());
            pointIdx2.add(num1);
            cacheIdx.put(num2, pointIdx2);
        }
        //一定有两个点，是只有一个相邻数字的，剩下的都有两个，由于顺序可以任意，任意找到一个即可
        Integer next = null;
        for (Map.Entry<Integer, List<Integer>> entry : cacheIdx.entrySet()) {
            if (entry.getValue().size() == 1) {
                ans[0] = entry.getKey();
                next = entry.getValue().get(0);
                break;
            }
        }
//        int idx = 0;
//        ans[idx++] = start;
//        while (true) {
//            ans[idx++] = next;
//            List<Integer> list = cacheIdx.get(next);
//            for (Integer val : list) {
//                if (!val.equals(start)) {
//                    start = next;
//                    next = val;
//                    break;
//                }
//            }
//            if (list.size() == 1) {
//                break;
//            }
//        }
        ans[1] = next;
        for (int i = 2; i < length; i++) {
            List<Integer> adj = cacheIdx.get(next);
            ans[i] = next = ans[i - 2] == adj.get(0) ? adj.get(1) : adj.get(0);
        }
        return ans;
    }

}
