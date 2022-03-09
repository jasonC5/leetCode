package com.jason.leetCode;

public class LC798 {
    // 暴力解，超时
    public static int bestRotation_time_out(int[] nums) {
        int length = nums.length;
        int maxPoint = Integer.MIN_VALUE;
        int minK = -1;
        for (int k = 0; k < length; k++) {
            int cur = 0;
            for (int i = 0; i < length; i++) {
                cur += nums[i] > (i + length - k) % length ? 0 : 1;// 小于等于计一分，大于不计分
            }
            if (cur > maxPoint) {
                maxPoint = cur;
                minK = k;
            }
        }
        return minK;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,0,2,4};
        System.out.println(bestRotation(nums));
        System.out.println(bestRotation2(nums));
    }

    // 官方解 差分    在一段或者多段连续数组上做加减法：线段树 或者 差分
    public static int bestRotation(int[] nums) {
        int n = nums.length;
        int[] diffs = new int[n];//差分数组
        for (int i = 0; i < n; i++) {
            int low = (i + 1) % n;
            int high = (i - nums[i] + n + 1) % n;
            diffs[low]++;
            diffs[high]--;
            if (low >= high) {
                diffs[0]++;
            }
        }
        int bestIndex = 0;
        int maxScore = 0;
        int score = 0;
        for (int i = 0; i < n; i++) {
            score += diffs[i];
            if (score > maxScore) {
                bestIndex = i;
                maxScore = score;
            }
        }
        return bestIndex;
    }

    // 线段树
    public static int bestRotation2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 1];
        SegmentTree segmentTree = new SegmentTree(arr);
        for (int i = 0; i < n; i++) {
            int low = (i + 1) % n;
            int high = (i - nums[i] + n) % n;
            if (low >= high) {
                segmentTree.add(1, high + 1, 1, 1, n, 1);
                segmentTree.add(low + 1, n, 1, 1, n, 1);
            } else {
                segmentTree.add(low + 1, high + 1, 1, 1, n, 1);
            }
        }
        int bestIndex = 0;
        int maxScore = 0;
        for (int i = 1; i <= n; i++) {
            int cur = (int) segmentTree.query(i, i, 1, n, 1);
            if (cur > maxScore) {
                bestIndex = i - 1;
                maxScore = cur;
            }
        }
        return bestIndex;
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
