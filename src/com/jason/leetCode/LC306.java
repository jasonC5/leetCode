package com.jason.leetCode;

/**
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * <p>
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * <p>
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入："112358"
 * 输出：true
 * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 示例 2：
 * <p>
 * 输入："199100199"
 * 输出：true
 * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/additive-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC306 {
    //关键在于如何确定前两个数
    public boolean isAdditiveNumber(String num) {
        int len = num.length();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((i > 0 && num.charAt(0) == '0') || (j > i + 1 && num.charAt(i + 1) == '0')) {
                    continue;
                }
                long x = Long.parseLong(num.substring(0, i + 1));
                long y = Long.parseLong(num.substring(i + 1, j + 1));
                if (j + 1 >= len) {// 没有下一个了
                    break;
                }
                if (validate(num, x, y, j + 1, 0L)) {
                    return true;
                }
            }
        }
        return false;
    }

    //DFS
    private boolean validate(String num, long prePre, long pre, int curIndex, long curNum) {
        curNum = curNum * 10 + num.charAt(curIndex) - '0';
        if (curNum == pre + prePre) {
            if (curIndex == num.length() - 1) {
                return true;
            }
            return validate(num, pre, curNum, curIndex + 1, 0);
        } else if (curNum != 0 && curNum < pre + prePre && curIndex < num.length() - 1) {
            return validate(num, prePre, pre, curIndex + 1, curNum);
        } else {
            return false;
        }
    }

    public boolean isAdditiveNumber2(String num) {
        int n = num.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((i > 0 && num.charAt(0) == '0') || (j > i + 1 && num.charAt(i + 1) == '0')) continue;
                long i0 = Long.parseLong(num.substring(0, i + 1));
                long i1 = Long.parseLong(num.substring(i + 1, j + 1));
                if (j + 1 >= n) break;
                if (dfs(i0, i1, num, 0, j + 1)) return true;
            }
        }
        return false;
    }

    public boolean dfs(long i, long j, String num, long cur, int index) {
        cur = cur * 10 + num.charAt(index) - '0';
        if (cur == i + j) {
            if (index == num.length() - 1) {
                return true;
            }
            else return dfs(j, cur, num, 0, index + 1);
        } else if (cur != 0 && cur < i + j && index < num.length() - 1) {
            return dfs(i, j, num, cur, index + 1);
        } else return false;
    }

    public static void main(String[] args) {
        LC306 lc306 = new LC306();
        System.out.println(lc306.isAdditiveNumber("121474836472147483648"));
    }
}
