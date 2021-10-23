package com.jason.leetCode;

import java.util.Arrays;

/**
 * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
 * <p>
 * 1. 你设计的矩形页面必须等于给定的目标面积。
 * <p>
 * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
 * <p>
 * 3. 长度 L 和宽度 W 之间的差距应当尽可能小。
 * 你需要按顺序输出你设计的页面的长度 L 和宽度 W。
 * <p>
 * 示例：
 * <p>
 * 输入: 4
 * 输出: [2, 2]
 * 解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
 * 但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-the-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC492 {
    public static int[] constructRectangle(int area) {
        int prescribe = (int) Math.pow(area, 0.5);
        for (int height = prescribe; height < area; height++) {
            int width = area / height;
            if (width > height) {
                continue;
            }
            if (height * width == area) {
                return new int[]{height, width};
            }
        }
        return new int[]{area, 1};
    }

    public static int[] constructRectangle2(int area) {
        int w = (int) Math.pow(area, 0.5);
        while (area % w != 0) {
            w--;
        }
        return new int[]{area / w, w};
    }

    public static void main(String[] args) {
        int area = 2;
        System.out.println(Arrays.toString(constructRectangle2(area)));
    }

}
