package com.jason.jingsai.competition277;

/**
 * 5989. 元素计数 显示英文描述
 * 通过的用户数0
 * 尝试过的用户数0
 * 用户总通过次数0
 * 用户总提交次数0
 * 题目难度Easy
 * 给你一个整数数组 nums ，统计并返回在 nums 中同时具有一个严格较小元素和一个严格较大元素的元素数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [11,7,2,15]
 * 输出：2
 * 解释：元素 7 ：严格较小元素是元素 2 ，严格较大元素是元素 11 。
 * 元素 11 ：严格较小元素是元素 7 ，严格较大元素是元素 15 。
 * 总计有 2 个元素都满足在 nums 中同时存在一个严格较小元素和一个严格较大元素。
 * 示例 2：
 * <p>
 * 输入：nums = [-3,3,3,90]
 * 输出：2
 * 解释：元素 3 ：严格较小元素是元素 -3 ，严格较大元素是元素 90 。
 * 由于有两个元素的值为 3 ，总计有 2 个元素都满足在 nums 中同时存在一个严格较小元素和一个严格较大元素。
 *
 * @author JasonC5
 */
public class C5989 {
    public int countElements(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        int ans = 0;
        for (int num : nums) {
            if (num != min && num != max) {
                ans++;
            }
        }
        return ans;
    }
}
