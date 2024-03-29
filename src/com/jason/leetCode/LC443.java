package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 * <p>
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 * <p>
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 * <p>
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 * <p>
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：chars = ["a","a","b","b","c","c","c"]
 * 输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
 * 解释：
 * "aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
 * 示例 2：
 * <p>
 * 输入：chars = ["a"]
 * 输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
 * 解释：
 * 没有任何字符串被替代。
 * 示例 3：
 * <p>
 * 输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
 * 解释：
 * 由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
 * 注意每个数字在数组中都有它自己的位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-compression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC443 {

    public static void main(String[] args) {
        System.out.println(compress("aabbccc".toCharArray()));
    }

    public static int compress(char[] chars) {
        int ansCount = 0;
        char pre = chars[0];
        int curCount = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == pre) {
                curCount++;
            } else {
                //统计前一个
                chars[ansCount++] = pre;
                if (curCount > 1) {
                    List<Integer> nums = new ArrayList<>();
                    while (curCount > 0) {
                        nums.add(curCount % 10);
                        curCount /= 10;
                    }
                    for (int j = nums.size() - 1; j >= 0; j--) {
                        chars[ansCount++] = (char) (nums.get(j) + '0');
                    }
                }
                //更新
                pre = chars[i];
                curCount = 1;
            }
        }
        //统计最后一批
        chars[ansCount++] = pre;
        if (curCount > 1) {
            List<Integer> nums = new ArrayList<>();
            while (curCount > 0) {
                nums.add(curCount % 10);
                curCount /= 10;
            }
            for (int j = nums.size() - 1; j >= 0; j--) {
                chars[ansCount++] = (char) (nums.get(j) + '0');
            }
        }
        return ansCount;
    }

    public static int compress2(char[] chars) {
        int ansCount = 0;
        char pre = chars[0];
        int curCount = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == pre) {
                curCount++;
            } else {
                //统计前一个
                chars[ansCount++] = pre;
                if (curCount > 1) {
                    char[] countArr = (curCount + "").toCharArray();
                    for (char c : countArr) {
                        chars[ansCount++] = c;
                    }
                }
                //更新
                pre = chars[i];
                curCount = 1;
            }
        }
        //统计最后一批
        chars[ansCount++] = pre;
        if (curCount > 1) {
            char[] countArr = (curCount + "").toCharArray();
            for (char c : countArr) {
                chars[ansCount++] = c;
            }
        }
        return ansCount;
    }
}
