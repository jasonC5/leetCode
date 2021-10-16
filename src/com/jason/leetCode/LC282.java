package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 * 示例 2:
 * <p>
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 * 示例 3:
 * <p>
 * 输入: num = "105", target = 5
 * 输出: ["1*0+5","10-5"]
 * 示例 4:
 * <p>
 * 输入: num = "00", target = 0
 * 输出: ["0+0", "0-0", "0*0"]
 * 示例 5:
 * <p>
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/expression-add-operators
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC282 {
    public static List<String> addOperators1(String num, int target) {
        char[] chars = num.toCharArray();
        List<String> ans = new ArrayList<>();
        process1(target, chars, String.valueOf(chars[0]), String.valueOf(chars[0]), 1, ans);
        return ans;
    }

    private static void process1(int target, char[] chars, String preItem, String preStr, int index, List<String> ans) {
        if (index == chars.length) {
            //收集答案，看是否符合
            if (target == calc(preStr)) {
                ans.add(preStr);
            }
            return;
        }
        if (!preItem.equals("0")) {
            process1(target, chars, preItem + chars[index], preStr + chars[index], index + 1, ans);
        }
        process1(target, chars, String.valueOf(chars[0]), preStr + "+" + chars[index], index + 1, ans);
        process1(target, chars, String.valueOf(chars[0]), preStr + "-" + chars[index], index + 1, ans);
        process1(target, chars, String.valueOf(chars[0]), preStr + "*" + chars[index], index + 1, ans);
    }

    private static int calc(String str) {
//        JexlEngine jexlEngine = new JexlBuilder().create();
        return 0;
    }

    public static void main(String[] args) {
        String num = "3456237490";
        Integer taget = 9191;
        System.out.println(addOperators(num, taget));
    }


    public static List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        process(num, target, "", 0, 0, 0, ans);
        return ans;
    }

    private static void process(String num, int target, String preStr, int index, long preSum, long preMul, List<String> ans) {
        if (index == num.length()) {
            //收集答案，看是否符合
            if (target == preSum) {
                ans.add(preStr);
            }
//            System.out.println(preStr);
            return;
        }
        //当前字符 从index开始，往后截
        int length = num.length();
        for (int i = index + 1; i <= length; i++) {
            String substring = num.substring(index, i);
            //不允许0开头且不是0的数组出现
            if (substring.length() > 1 && substring.startsWith("0")) {
                continue;
            }
            long cur = Long.parseLong(substring);
            if (index == 0) {
                //第一个字符，只有加法
                process(num, target, cur + "", i, preSum + cur, cur, ans);
            } else {
                process(num, target, preStr + "+" + cur, i, preSum + cur, cur, ans);
                process(num, target, preStr + "-" + cur, i, preSum - cur, -cur, ans);
                process(num, target, preStr + "*" + cur, i, preSum - preMul + preMul * cur, preMul * cur, ans);
            }
        }
    }
}
