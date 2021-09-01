package com.jason.leetCode;

/**
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * <p>
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * <p>
 * 请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 * 示例 2：
 * <p>
 * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
 * 输出：[10,25]
 * 解释：
 * 航班编号        1   2
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       15
 * 总座位数：      10  25
 * 因此，answer = [10,25]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1109 {
    public static void main(String[] args) {
        int[][] booking = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        System.out.println(corpFlightBookings2(booking, n));
    }

    //暴力解
    public static int[] corpFlightBookings0(int[][] bookings, int n) {
        int[] nums = new int[n];
        for (int[] booking : bookings) {
            for (int i = booking[0] - 1; i < booking[1]; i++) {
                nums[i] += booking[2];
            }
        }
        return nums;
    }

    //差分数组
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        //差分数组
        for (int[] booking : bookings) {
            nums[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                nums[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    //线段树
    public static int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] nums = new int[n];
        SegmentTree segmentTree = new SegmentTree(nums);
        for (int[] booking : bookings) {
            segmentTree.add(booking[0], booking[1], booking[2], 1, n, 1);
        }
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = (int) segmentTree.query(i, i, 1, n, 1);
        }
        return nums;
    }

    public static class SegmentTree {
        //传进来的原数组，但是从1开始
        private int[] arr;
        //arr的长度
        private int len;
        //线段树的区间和数组
        private int[] sum;
        //累加 懒加载缓存
        private int[] lazy;

        public SegmentTree(int[] source) {
            this.len = source.length + 1;
            this.arr = new int[len];
            for (int i = 1; i <= source.length; i++) {
                this.arr[i] = source[i - 1];
            }
            //4 * len，够用
            sum = new int[len << 2]; // 用来支持脑补概念中，某一个范围的累加和信息
            lazy = new int[len << 2]; // 用来支持脑补概念中，某一个范围沒有往下傳遞的纍加任務
        }

        // L~R, C 任务！
        // rt，l~r
        public void add(int L, int R, int C, int l, int r, int rt) {
            //L到R范围内，全部加上C， 当前任务范围：从l,到r，当前任务根节点-rt  --root
            //全包住了，懒缓存
            if (l >= L && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            //没包住，需要下发
            int mid = (r + l) >> 1;
            //需要先把懒缓存处理下去，再处理子任务
            pushDown(rt, mid - l + 1, r - mid);
            //任务下发
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            //任务处理完了，求本届点的和
            pushUp(rt);
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            //处理加缓存
            if (lazy[rt] != 0) {
                int addVal = lazy[rt];
                lazy[rt] = 0;
                lazy[rt << 1] += addVal;
                lazy[rt << 1 | 1] += addVal;
                sum[rt << 1] += addVal * ln;
                sum[rt << 1 | 1] += addVal * rn;
            }
        }

        // 1~6 累加和是多少？ 1~8 rt
        public long query(int L, int R, int l, int r, int rt) {
            if (l >= L && r <= R) {
                return sum[rt];
            }
            //没包住，需要下发
            long ans = 0L;
            int mid = l + ((r - l) >> 1);
            //马上要往下发任务了，懒缓存先处理，下推
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }

}
