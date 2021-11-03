package com.jason.leetCode;

import java.util.*;

/**
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int length = nums.length;
        if (length < 4) {
            return ans;
        }
        int mid = length >> 1;
        //分治：左右两边分别找
        Map<Integer, List<List<Integer>>> leftOne = new HashMap<>();
        Map<Integer, List<List<Integer>>> leftTwo = new HashMap<>();
        Map<Integer, List<List<Integer>>> leftThree = new HashMap<>();
        Map<Integer, List<List<Integer>>> leftFore = new HashMap<>();
        process(nums, mid, 1, 0, new ArrayList(), leftOne);
        process(nums, mid, 2, 0, new ArrayList(), leftTwo);
        process(nums, mid, 3, 0, new ArrayList(), leftThree);
        process(nums, mid, 4, 0, new ArrayList(), leftFore);
        Map<Integer, List<List<Integer>>> rightOne = new HashMap<>();
        Map<Integer, List<List<Integer>>> rightTwo = new HashMap<>();
        Map<Integer, List<List<Integer>>> rightThree = new HashMap<>();
        Map<Integer, List<List<Integer>>> rightFore = new HashMap<>();
        process(nums, length, 1, mid, new ArrayList(), rightOne);
        process(nums, length, 2, mid, new ArrayList(), rightTwo);
        process(nums, length, 3, mid, new ArrayList(), rightThree);
        process(nums, length, 4, mid, new ArrayList(), rightFore);
        Comparator<Integer> comparator = (x1, x2) -> x1 - x2;
        Set<String> exists = new HashSet<>();
        if (leftFore.containsKey(target)) {
            List<List<Integer>> list = leftFore.get(target);
            for (List<Integer> newAns : list) {
                newAns.sort(comparator);
                if (!exists.contains(newAns.toString())) {
                    ans.add(newAns);
                    exists.add(newAns.toString());
                }
            }
        }
        if (rightFore.containsKey(target)) {
            List<List<Integer>> list = rightFore.get(target);
            for (List<Integer> newAns : list) {
                newAns.sort(comparator);
                if (!exists.contains(newAns.toString())) {
                    ans.add(newAns);
                    exists.add(newAns.toString());
                }
            }
        }
        for (Map.Entry<Integer, List<List<Integer>>> entry : leftThree.entrySet()) {
            if (rightOne.containsKey(target - entry.getKey())) {
                List<List<Integer>> leftList = entry.getValue();
                List<List<Integer>> rightList = rightOne.get(target - entry.getKey());
                for (List<Integer> left : leftList) {
                    for (List<Integer> right : rightList) {
                        List<Integer> newAns = new ArrayList<>();
                        newAns.addAll(left);
                        newAns.addAll(right);
                        newAns.sort(comparator);
                        if (!exists.contains(newAns.toString())) {
                            ans.add(newAns);
                            exists.add(newAns.toString());
                        }
                    }
                }
            }
        }
        for (Map.Entry<Integer, List<List<Integer>>> entry : leftTwo.entrySet()) {
            if (rightTwo.containsKey(target - entry.getKey())) {
                List<List<Integer>> leftList = entry.getValue();
                List<List<Integer>> rightList = rightTwo.get(target - entry.getKey());
                for (List<Integer> left : leftList) {
                    for (List<Integer> right : rightList) {
                        List<Integer> newAns = new ArrayList<>();
                        newAns.addAll(left);
                        newAns.addAll(right);
                        newAns.sort(comparator);
                        if (!exists.contains(newAns.toString())) {
                            ans.add(newAns);
                            exists.add(newAns.toString());
                        }
                    }
                }
            }
        }
        for (Map.Entry<Integer, List<List<Integer>>> entry : leftOne.entrySet()) {
            if (rightThree.containsKey(target - entry.getKey())) {
                List<List<Integer>> leftList = entry.getValue();
                List<List<Integer>> rightList = rightThree.get(target - entry.getKey());
                for (List<Integer> left : leftList) {
                    for (List<Integer> right : rightList) {
                        List<Integer> newAns = new ArrayList<>();
                        newAns.addAll(left);
                        newAns.addAll(right);
                        newAns.sort(comparator);
                        if (!exists.contains(newAns.toString())) {
                            ans.add(newAns);
                            exists.add(newAns.toString());
                        }
                    }
                }
            }
        }
        //去重
        return ans;
    }

    private static void process(int[] nums, int right, int need, int index, List<Integer> pre, Map<Integer, List<List<Integer>>> sumMap) {
        if (need == pre.size()) {
            int sum = pre.stream().mapToInt(x -> x).sum();
            List<List<Integer>> list = sumMap.getOrDefault(sum, new ArrayList<>());
            List<Integer> newList = new ArrayList<>(pre);
            list.add(newList);
            sumMap.put(sum, list);
            return;
        }
        if (index == right) {
            return;
        }
        //不要当前元素
        process(nums, right, need, index + 1, pre, sumMap);
        //要当前元素
        pre.add(nums[index]);
        process(nums, right, need, index + 1, pre, sumMap);
        pre.remove(pre.size() - 1);
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 2, 2};
        int target = 8;
        System.out.println(fourSum(nums, target));
    }
}
