package com.jason.leetCode;

public class LC307 {
    static class NumArray {
        //传进来的原数组，但是从1开始
        private int[] arr;
        //arr的长度
        private int len;
        //线段树的区间和数组
        private int[] sum;
        //修改 懒加载缓存
        private int[] change;
        //修改 懒加载标记
        private boolean[] update;
        //累加 懒加载缓存
        private int[] lazy;

        public NumArray(int[] nums) {
            this.len = nums.length + 1;
            this.arr = new int[len];
            for (int i = 1; i <= nums.length; i++) {
                this.arr[i] = nums[i - 1];
            }
            sum = new int[len << 2];
            change = new int[len << 2];
            lazy = new int[len << 2];
            update = new boolean[len << 2];
            build(1, nums.length, 1);
        }

        public void build(int left, int right, int rt) {
            if (left == right) {
                sum[rt] = arr[left];
                return;
            }
            //下推子任务赋值
            int mid = (left + right) >> 1;
            build(left, mid, rt << 1);
            build(mid + 1, right, rt << 1 | 1);
            //赋值完成，求和
            pushUp(rt);
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            //处理update缓存
            if (update[rt]) {
                //往下发
                int updateVal = change[rt];
                update[rt] = false;
                change[rt] = 0;
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = updateVal;
                change[rt << 1 | 1] = updateVal;
                sum[rt << 1] = updateVal * ln;
                sum[rt << 1 | 1] = updateVal * rn;
                //里面的值都被覆盖了，累加值肯定得清空，否则会影响下次任务
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
            }
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

        // L~R  所有的值变成C
        // l~r  rt
        public void update(int L, int R, int C, int l, int r, int rt) {
            //包住了，懒缓存
            if (l >= L && r <= R) {
                sum[rt] = C * (r - l + 1);
                update[rt] = true;
                change[rt] = C;
                //需要删除之前的加缓存【覆盖操作】
                lazy[rt] = 0;
                return;
            }
            //没包住，需要下发
            int mid = (r + l) >> 1;
            //需要先把懒缓存处理下去，再处理子任务
            pushDown(rt, mid - l + 1, r - mid);
            //缓存处理完了，处理子任务
            //任务下发
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            //任务处理完了，求本届点的和
            pushUp(rt);
        }

        public void update(int index, int val) {
            update(index + 1, index + 1, val, 1, len - 1, 1);
        }

        public int sumRange(int left, int right) {
            return (int) query(left + 1, right + 1, 1, len - 1, 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5};
        NumArray numArray = new NumArray(arr);
        System.out.println(numArray.sumRange(0, 2));
    }
}
