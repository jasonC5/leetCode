package com.jason.msjd;

import java.util.PriorityQueue;

/**
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 示例：
 * <p>
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-k-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class MS_17_14 {
    // 又是前k小问题
    // 门槛堆
    public static int[] smallestK1(int[] arr, int k) {
        if (k == 0) {
            return new int[]{};
        }
        //大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int num : arr) {
            if (heap.size() < k || heap.peek() > num) {
                heap.offer(num);
            }
            if (heap.size() == k + 1) {
                heap.poll();
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = heap.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] num = {1, 3, 5, 7, 2, 4, 6, 8};
//        int k = 4;
//        int[] num = {1, 2, 3};
//        int k = 0;
        int[] num = {62577, -220, -8737, -22, -6, 59956, 5363, -16699, 0, -10603, 64, -24528, -4818, 96, 5747, 2638, -223, 37663, -390, 35778, -4977, -3834, -56074, 7, -76, 601, -1712, -48874, 31, 3, -9417, -33152, 775, 9396, 60947, -1919, 683, -37092, -524, -8, 1458, 80, -8, 1, 7, -355, 9, 397, -30, -21019, -565, 8762, -4, 531, -211, -23702, 3, 3399, -67, 64542, 39546, 52500, -6263, 4, -16, -1, 861, 5134, 8, 63701, 40202, 43349, -4283, -3, -22721, -6, 42754, -726, 118, 51, 539, 790, -9972, 41752, 0, 31, -23957, -714, -446, 4, -61087, 84, -140, 6, 53, -48496, 9, -15357, 402, 5541, 4, 53936, 6, 3, 37591, 7, 30, -7197, -26607, 202, 140, -4, -7410, 2031, -715, 4, -60981, 365, -23620, -41, 4, -2482, -59, 5, -911, 52, 50068, 38, 61, 664, 0, -868, 8681, -8, 8, 29, 412};
        int k = 131;
        System.out.println(smallestK(num, k));
    }

    // 改写快排
    public static int[] smallestK2(int[] arr, int k) {
        if (k == 0) {
            return new int[]{};
        }
        //找到第k - 1个数，过程：随机快排荷兰国旗问题
        process(arr, 0, arr.length - 1, k - 1);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }


    private static int process(int[] arr, int left, int right, int idx) {
        if (left == right) {
            return arr[left];
        }
        //随机位置 -- 如果改成bfprt，只要改这里
        int randomIdx = (int) (Math.random() * (right - left + 1)) + left;
        int random = arr[randomIdx];
        // == random的位置
        int[] range = partition(arr, left, right, random);
        // 根据已排好的位置和idx的关系，重新确定left和right
        if (range[0] > idx) {
            right = range[0] - 1;
        } else if (range[1] < idx) {
            left = range[0] + 1;
        } else {
            return random;
        }
        return process(arr, left, right, idx);
    }

    private static int[] partition(int[] arr, int left, int right, int num) {
        //两个括号
        int l = left - 1;
        int r = right + 1;
        int cur = left;
        while (cur != r) {
            if (arr[cur] < num) {
                swap(arr, cur++, ++l);
            } else if (arr[cur] > num) {
                swap(arr, cur, --r);
            } else {
                cur++;
            }
        }
        return new int[]{l + 1, r - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }


    // bfprt
    public static int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[]{};
        }
        //找到第k - 1个数，过程：随机快排荷兰国旗问题
        bfprt(arr, 0, arr.length - 1, k - 1);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    private static int bfprt(int[] arr, int left, int right, int idx) {
        if (left == right) {
            return arr[left];
        }
        //随机位置 -- 如果改成bfprt，只要改这里
//        int randomIdx = (int) (Math.random() * (right - left + 1)) + left;
//        int random = arr[randomIdx];
        //bfprt --对比随机快排，只在找位置做了优化
        int pivot = medianOfMedians(arr, left, right);
        // == random的位置
        int[] range = partition(arr, left, right, pivot);
        // 根据已排好的位置和idx的关系，重新确定left和right
        if (range[0] > idx) {
            right = range[0] - 1;
        } else if (range[1] < idx) {
            left = range[0] + 1;
        } else {
            return pivot;
        }
        return bfprt(arr, left, right, idx);
    }

    // 每五个数一组 每一个小组内部排好序 小组的中位数组成新数组 这个新数组的中位数返回
    private static int medianOfMedians(int[] arr, int left, int right) {
        int size = right - left + 1;
        int[] midArr = new int[size / 5 + ((size % 5 == 0) ? 0 : 1)];
        for (int i = 0; i < midArr.length; i++) {
            int l = left + i * 5;
            // 局部排序，找中位数
            midArr[i] = getMid(arr, l, Math.min(l + 4, right));
        }
        // 找这个数组的中位数
        return bfprt(midArr, 0, midArr.length - 1, midArr.length >> 1);
    }

    private static int getMid(int[] arr, int l, int r) {
        //局部排序再返回中位数
        sort(arr, l, r);
        return arr[l + ((r - l) >> 1)];
    }

    //插入排序 --常数时间好
    private static void sort(int[] arr, int l, int r) {
        for (int i = l + 1; i < r; i++) {
            //arr[j + 1] > arr[j] 这里的大于小于不影响最终的结果，只是取中位数而已
            for (int j = i - 1; j >= l && arr[j + 1] < arr[j]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }
}
