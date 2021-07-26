package com.jason.leetCode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
 * <p>
 * 每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,2] 。你可以在数组最开始或最后面添加整数。
 * <p>
 * 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
 * <p>
 * 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = [5,1,3], arr = [9,4,2,3,4]
 * 输出：2
 * 解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
 * 示例 2：
 * <p>
 * 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
 * 输出：3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1713 {

    public static void main(String[] args) {
//[5,1,3]
//[9,4,2,3,4]
        int[] target = {5, 1, 3}, arr = {9, 4, 2, 3, 4};
//        int[] target = {11,5,16,19,10,3,12,18,1,8}, arr = {9,6,5,4,6,4,18,3,10,11};
        System.out.println(new LC1713().minOperations(target, arr));
        System.out.println(new LC1713().minOperations2(target, arr));
    }

    //答案
    public int minOperations2(int[] target, int[] arr) {
        int n = target.length;
        Map<Integer, Integer> pos = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; ++i) {
            pos.put(target[i], i);
        }
        List<Integer> d = new ArrayList<Integer>();
        for (int val : arr) {
            if (pos.containsKey(val)) {
                int idx = pos.get(val);
                int it = binarySearch(d, idx);
                if (it != d.size()) {
                    d.set(it, idx);
                } else {
                    d.add(idx);
                }
            }
        }
        return n - d.size();
    }
    public int binarySearch(List<Integer> d, int target) {
        int size = d.size();
        if (size == 0 || d.get(size - 1) < target) {
            return size;
        }
        int low = 0, high = size - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (d.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int minOperations(int[] target, int[] arr) {
        //求最长公共子序列，然后 ans = target.length - n
        //动态规划求最长公共子序列
        //--若数组过长，且arr中有很多target中根本不存在的元素，则会凭空增加时间复杂度，【target中不存在的元素，一定不在公共子序列中】
        Set<Integer> collect = Arrays.stream(target).boxed().collect(Collectors.toSet());
        int[] filterList = Arrays.stream(arr).filter(i -> collect.contains(i)).toArray();
        int n1 = longestCommonSubsequence1(target, filterList);
        int n2 = longestCommonSubsequence2(target, filterList);
        int n3 = longestCommonSubsequence3(target, filterList);
        //  target′  是严格单调递增的，因此arr' 在最长公共子序列中的部分也必须是严格单调递增的，因此问题可进一步地转换成求 arr'  的最长递增子序列的长度
        int n4 = longestCommonSubsequence4(target, arr);
        System.out.println(n1 + " " + n2 + " " + n3+ " " + n4);
        return target.length - n1;
    }

    private int longestCommonSubsequence4(int[] target, int[] arr) {
        Map<Integer, Integer> dir = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            dir.put(target[i], i);
        }
        List<Integer> transfer = new ArrayList();
        for (int num : arr) {
            if (dir.containsKey(num)) {
                transfer.add(dir.get(num));
            }
        }
        //得到一个转移数组，这个转移数组和 [1,2,3,4,5……]的最长公共子序列长度，就是原数组的最长公共子序列的长度
        //转移数组和递增数组的最长公共子序列长度，就是自身的最长递增子序列长度
        int n = lengthOfLIS(transfer.stream().mapToInt(Integer::intValue).toArray());
        return n;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    public static int longestCommonSubsequence1(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1 == null || arr1.length == 0 || arr1.length == 0) {
            return 0;
        }
        return process1(arr1, arr2, arr1.length - 1, arr2.length - 1);
    }

    //arr1 中 0~idx1 和 arr2 中 0~idx2 中 最大公共子序列长度
    private static int process1(int[] arr1, int[] arr2, int idx1, int idx2) {
        if (idx1 == 0 && idx2 == 0) {
            return arr1[0] == arr2[0] ? 1 : 0;
        } else if (idx1 == 0) {
            return arr1[idx1] == arr2[idx2] ? 1 : process1(arr1, arr2, idx1, idx2 - 1);
        } else if (idx2 == 0) {
            return arr1[idx1] == arr2[idx2] ? 1 : process1(arr1, arr2, idx1 - 1, idx2);
        } else {
            int ans1 = process1(arr1, arr2, idx1, idx2 - 1);
            int ans2 = process1(arr1, arr2, idx1 - 1, idx2);
            int ans3 = arr1[idx1] == arr2[idx2] ? (1 + process1(arr1, arr2, idx1 - 1, idx2 - 1)) : 0;
            return Math.max(ans1, Math.max(ans2, ans3));
        }
    }


    public static int longestCommonSubsequence2(int[] target, int[] arr) {
        if (target == null || arr == null || target.length == 0 || arr.length == 0) {
            return 0;
        }
        int length1 = target.length;
        int length2 = arr.length;
        int[][] dp = new int[length1][length2];
        //先推第0行，再推第0列
        dp[0][0] = target[0] == arr[0] ? 1 : 0;
        for (int idx2 = 1; idx2 < length2; idx2++) {
            dp[0][idx2] = target[0] == arr[idx2] ? 1 : dp[0][idx2 - 1];
        }
        for (int idx1 = 1; idx1 < length1; idx1++) {
            dp[idx1][0] = target[idx1] == arr[0] ? 1 : dp[idx1 - 1][0];
        }
        //最后依次往下推
        for (int idx1 = 1; idx1 < length1; idx1++) {
            for (int idx2 = 1; idx2 < length2; idx2++) {
                int ans1 = dp[idx1][idx2 - 1];
                int ans2 = dp[idx1 - 1][idx2];
                int ans3 = target[idx1] == arr[idx2] ? (1 + dp[idx1 - 1][idx2 - 1]) : 0;
                dp[idx1][idx2] = Math.max(ans1, Math.max(ans2, ans3));
            }
        }
        return dp[length1 - 1][length2 - 1];
    }

    //空间压缩
    public static int longestCommonSubsequence3(int[] target, int[] arr) {
        if (target == null || arr == null || target.length == 0 || arr.length == 0) {
            return 0;
        }
        int length1 = target.length;
        int length2 = arr.length;
//        int[][] dp = new int[length1][length2];
        int[] pre = new int[length2];
        int[] cur = new int[length2];
        //先推第0行，再推第0列
        pre[0] = target[0] == arr[0] ? 1 : 0;
        for (int idx2 = 1; idx2 < length2; idx2++) {
            pre[idx2] = target[0] == arr[idx2] ? 1 : pre[idx2 - 1];
        }
        //最后依次往下推
        for (int idx1 = 1; idx1 < length1; idx1++) {
            cur[0] = target[idx1] == arr[0] ? 1 : pre[0];
            for (int idx2 = 1; idx2 < length2; idx2++) {
                int ans1 = cur[idx2 - 1];
                int ans2 = pre[idx2];
                int ans3 = target[idx1] == arr[idx2] ? (1 + pre[idx2 - 1]) : 0;
                cur[idx2] = Math.max(ans1, Math.max(ans2, ans3));
            }
            int[] tmp = pre;
            pre = cur;
            cur = tmp;
        }
        return pre[length2 - 1];
    }

}
