package com.jason.jingsai.competition282;

/**
 * 给你一个数组 time ，其中 time[i] 表示第 i 辆公交车完成 一趟旅途 所需要花费的时间。
 * <p>
 * 每辆公交车可以 连续 完成多趟旅途，也就是说，一辆公交车当前旅途完成后，可以 立马开始 下一趟旅途。每辆公交车 独立 运行，也就是说可以同时有多辆公交车在运行且互不影响。
 * <p>
 * 给你一个整数 totalTrips ，表示所有公交车 总共 需要完成的旅途数目。请你返回完成 至少 totalTrips 趟旅途需要花费的 最少 时间。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：time = [1,2,3], totalTrips = 5
 * 输出：3
 * 解释：
 * - 时刻 t = 1 ，每辆公交车完成的旅途数分别为 [1,0,0] 。
 * 已完成的总旅途数为 1 + 0 + 0 = 1 。
 * - 时刻 t = 2 ，每辆公交车完成的旅途数分别为 [2,1,0] 。
 * 已完成的总旅途数为 2 + 1 + 0 = 3 。
 * - 时刻 t = 3 ，每辆公交车完成的旅途数分别为 [3,1,1] 。
 * 已完成的总旅途数为 3 + 1 + 1 = 5 。
 * 所以总共完成至少 5 趟旅途的最少时间为 3 。
 * 示例 2：
 * <p>
 * 输入：time = [2], totalTrips = 1
 * 输出：2
 * 解释：
 * 只有一辆公交车，它将在时刻 t = 2 完成第一趟旅途。
 * 所以完成 1 趟旅途的最少时间为 2 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-time-to-complete-trips
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC2187 {
    public long minimumTime(int[] time, int totalTrips) {
        long minTime = (long) time[0] * totalTrips;
        long left = 0L, right = minTime;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            if (canFinish(time, mid, totalTrips)) {
                minTime = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return minTime;
    }

    private boolean canFinish(int[] time, long totalTime, int totalTrips) {
        int all = 0;
        for (int t : time) {
            all += totalTime / t;
            if (all >= totalTrips) {
                return true;
            }
        }
        return false;
    }
}
