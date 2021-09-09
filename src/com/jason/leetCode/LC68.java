package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * <p>
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * <p>
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * <p>
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 说明:
 * <p>
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 * <p>
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * 示例 2:
 * <p>
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 * 因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 * <p>
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 * "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 * "Science  is  what we",
 * "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/text-justification
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC68 {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        //1.最后一行左对齐
        //2.当某一行只有一个单词时,左对齐
        //3.某一行有多个单词时，最后一个单词后面无空格，每个单词之间有1到多个空格
        //4.当空格无法平均分配时，优先往左边分配
        List<String> ans = new ArrayList<>();
        int start = 0;
        while (start < words.length) {
            // 先确定当前行有几个单词，
            int end = start;
            int curLength = 0;
            // 字母的长度+每个单词中间至少包含一个空格 <= 每行长度
            while (end < words.length && curLength + words[end].length() + end - start <= maxWidth) {
                //只包含字母的长度
                curLength += words[end++].length();
            }
            // 看是否是最后一行
            if (end == words.length) {
                StringBuffer sb = new StringBuffer();
                for (int i = start; i < end - 1; i++) {
                    sb.append(words[i] + " ");
                }
                sb.append(words[end - 1]);
                //剩下的空格
                for (int i = 0; i < maxWidth - (curLength + end - start - 1); i++) {
                    sb.append(" ");
                }
                ans.add(sb.toString());
                return ans;
            }
            // 当前行只有一个单词，左对齐
            if (end - start == 1) {
                StringBuffer sb = new StringBuffer();
                sb.append(words[start]);
                //剩下的空格
                for (int i = 0; i < maxWidth - words[start].length(); i++) {
                    sb.append(" ");
                }
                ans.add(sb.toString());
                start = end;
                continue;
            }
            // 多个单词空格连接成maxWidth长度
            String cur = union(words, start, end, maxWidth - curLength);
            start = end;
            ans.add(cur);
        }
        return ans;
    }

    private static String union(String[] words, int start, int end, int spaceCount) {
        StringBuffer ans = new StringBuffer();
        int count = end - start - 1;
        int normal = spaceCount / count;
        //有这么多个单词后面要多加一个空格
        int special = spaceCount % count;
        StringBuffer normalSpacing = new StringBuffer();
        StringBuffer specialSpacing = new StringBuffer();
        for (int i = 0; i < normal; i++) {
            normalSpacing.append(" ");
            specialSpacing.append(" ");
        }
        specialSpacing.append(" ");
        for (int i = start; i < start + special; i++) {
            ans.append(words[i]);
            ans.append(specialSpacing);
        }
        for (int i = start + special; i < end - 1; i++) {
            ans.append(words[i]);
            ans.append(normalSpacing);
        }
        ans.append(words[end - 1]);
        return ans.toString();
    }

    public static void main(String[] args) {
        String[] words = {"What","must","be","acknowledgment","shall","be"};
//        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> list = fullJustify(words, maxWidth);
        System.out.println(list);
    }


}
