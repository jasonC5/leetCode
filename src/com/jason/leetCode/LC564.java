package com.jason.leetCode;

/**
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * <p>
 * “最近的”定义为两个整数差的绝对值最小。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = "123"
 * 输出: "121"
 * 示例 2:
 * <p>
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= n.length <= 18
 * n 只由数字组成
 * n 不含前导 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-closest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC564 {
    public static String nearestPalindromicError(String n) {
        if (n.equals("0")) {
            return "1";
        } else if (n.length() == 1) {
            return (Integer.parseInt(n) - 1) + "";
        }
        char[] chars = n.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length >> 1; i++) {
            chars[length - 1 - i] = chars[i];
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
//        System.out.println(nearestPalindromic("123"));
//        System.out.println(nearestPalindromic("1"));
//        System.out.println(nearestPalindromic("1220"));
        System.out.println(nearestPalindromic("10"));
//        System.out.println(nearestPalindromic("99"));
    }

    private static String nearestPalindromic(String n) {
        /*
        可能性:    1.前半部分直接替换后半部分
                  2.前半部分+1替换后半部分
                  3.前半部分-1替换后半部分
         */
        if (n.equals("0")) {
            return "1";
        } else if (n.length() == 1) {
            return (Integer.parseInt(n) - 1) + "";
        }
        long cur = Long.parseLong(n), ans = -1;
        long[] cdts = new long[5];
        int len = n.length();
        int half = (len + 1) >> 1;
        String pre = n.substring(0, half);
        long preNum = Long.parseLong(pre);
        for (int i = 0; i < 3; i++) {
            long curPre = preNum - 1 + i;
            if (curPre < 0) {
                cdts[i] = Long.MIN_VALUE;
                continue;
            }
            StringBuffer sb = new StringBuffer();
            sb.append(curPre);
            sb.append(new StringBuffer().append(curPre).reverse().substring(len & 1));
            cdts[i] = Long.parseLong(sb.toString());
        }
        // 99……99  和 10……01
        cdts[3] = (long) Math.pow(10, len - 1) - 1;
        cdts[4] = (long) Math.pow(10, len) + 1;
        for (long cdt : cdts) {
            if (cdt != cur) {
                if (ans == -1 ||
                        Math.abs(cdt - cur) < Math.abs(ans - cur) ||
                        Math.abs(cdt - cur) == Math.abs(ans - cur) && cdt < ans) {
                    ans = cdt;
                }
            }
        }
        return ans + "";
    }


}
