package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个正整数 n ，你可以做如下操作：
 * <p>
 * 如果 n 是偶数，则用 n / 2替换 n 。
 * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
 * n 变为 1 所需的最小替换次数是多少？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 8
 * 输出：3
 * 解释：8 -> 4 -> 2 -> 1
 * 示例 2：
 * <p>
 * 输入：n = 7
 * 输出：4
 * 解释：7 -> 8 -> 4 -> 2 -> 1
 * 或 7 -> 6 -> 3 -> 2 -> 1
 * 示例 3：
 * <p>
 * 输入：n = 4
 * 输出：2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC397 {

    public static final Map<Integer, Integer> cache = new HashMap();

    public static int integerReplacement(int n) {
        Integer ans;
        if (n == 1) {
            return 0;
        } else if ((ans = cache.get(n)) != null) {
            return ans;
        }
        if ((n & 1) == 0) {
            ans = 1 + integerReplacement(n >> 1);
            cache.put(n, ans);
            return ans;
        } else {
            ans = 2 + Math.min(integerReplacement(n >> 1), integerReplacement((n >> 1) + 1));
            cache.put(n, ans);
            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(integerReplacement(2147483647));
    }

}
