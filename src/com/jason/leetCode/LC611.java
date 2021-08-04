package com.jason.leetCode;

import java.util.Arrays;

/**
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 注意:
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-triangle-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC611 {

    public static void main(String[] args) {
//        int[] nums = {2,2,3,4};
//        System.out.println(triangleNumber(nums));
        int lengthMax = 100;
        int numMax = 100;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] arr = createArr(lengthMax, numMax);
            if (triangleNumber(arr) != triangleNumber3(arr)) {
                System.out.println("failed");
                return;
            }
        }
        System.out.println("finished");
    }

    private static int[] createArr(int lengthMax, int numMax) {
        int len = (int) (Math.random() * lengthMax);
//        len = len < 3 ? 3 : len;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * numMax);
        }
        return arr;
    }

    //暴力解
    public static int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (canBeTriangle(nums[i], nums[j], nums[k])) {//三角形
                        count++;
                    }
                }
            }
        }
        return count;
    }

    //暴力解 2
    public static int triangleNumber2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int count = 0;
        for (int i = 2; i < nums.length; i++) {
            for (int j = 1; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    if (canBeTriangle(nums[i], nums[j], nums[k])) {//三角形
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean canBeTriangle(int num1, int num2, int num3) {
        return num1 < num2 + num3 && num2 < num1 + num3 && num3 < num2 + num1;
    }

    public static int triangleNumber3(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int k = i + 1;//推高不回退，所以是O(n);
            for (int j = i + 1; j < nums.length; j++) {
                //前面两个小，后面一个大，只需要满足 前面两个相加小于第三个
                while (k + 1 < nums.length && nums[i] + nums[j] > nums[k + 1]) {
                    k++;
                }
                count += Math.max(k - j, 0);
            }
        }
        return count;
    }


}
