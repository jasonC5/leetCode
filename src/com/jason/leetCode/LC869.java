package com.jason.leetCode;

/**
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 * <p>
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：1
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：10
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：16
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：24
 * 输出：false
 * 示例 5：
 * <p>
 * 输入：46
 * 输出：true
 * 提示：
 * <p>
 * 1 <= N <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reordered-power-of-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC869 {
    public static boolean reorderedPowerOf2(int n) {
        if (n == (n & -n)) {
            return true;
        } else if (n < 10) {
            return false;
        }
        String str = String.valueOf(n);
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    private static boolean process(char[] chars, int idx) {
        if (idx == chars.length) {
            String s = String.valueOf(chars);
            int num;
            return !s.startsWith("0") && ((num = Integer.parseInt(s)) & -num) == num;
        }
        boolean[] visited = new boolean[10];
        for (int i = idx; i < chars.length; i++) {
            if (visited[chars[i] - '0']) {//已经处理过了,剪枝
                continue;
            }
            visited[chars[i] - '0'] = true;
            swap(chars, idx, i);
            if (process(chars, idx + 1)){
                return true;
            }
            swap(chars, idx, i);
        }
        return false;
    }

    private static void swap(char[] chars, int idx1, int idx2) {
        char tmp = chars[idx1];
        chars[idx1] = chars[idx2];
        chars[idx2] = tmp;
    }

    public static void main(String[] args) {
        int num = 831;
        System.out.println(reorderedPowerOf2(num));
    }

}
