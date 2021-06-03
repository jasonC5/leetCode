package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 * 
 *
 * 示例 1:
 *
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * 示例 2:
 *
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 * 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contiguous-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author JasonC5
 */
public class LC525 {

    public static void main(String[] args) {
        int maxLength = 100;
        int times = 1000;
        //对数器
        for (int i = 0; i < times; i++) {
            int[] nums = build01Arr(maxLength);
//            int[] nums = {0,0,0,1,1,1,0};
            int ans1 = findMaxLength(nums);
            int ans2 = findMaxLength2(nums);
            if (ans1!=ans2) {
                System.out.println("filed, ans1="+ans1+",ans2="+ans2);
                System.out.print("nums:");
                Arrays.stream(nums).boxed().forEach(x -> {
                    System.out.print(x + ",");
                });
                return;
            }
        }
        System.out.println("complete!!!");

    }

    private static int[] build01Arr(int maxLength) {
        int length = (int)(Math.random() * maxLength) + 1;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int)(Math.random() * 2);
        }
        return arr;
    }

    public static int findMaxLength(int[] nums) {
        int max = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        //1=1,0=-1,互相抵消，sum[a] - sum[b] = 0的时候，a~b为含有相同数量0和1的字串
        int sum = 0;
        sumMap.put(sum, -1);//从第0个开始，下标写作-1
        for (int i = 0; i < nums.length; i++) {
            sum += (nums[i] == 0 ? -1 : 1);
            if (sumMap.containsKey(sum)) {
                max = Math.max(i - sumMap.get(sum), max);
            } else {
                sumMap.put(sum,i);
            }
        }
        return max;
    }

    public static int findMaxLength2(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }

}
