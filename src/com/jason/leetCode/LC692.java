package com.jason.leetCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 给一非空的单词列表，返回前k个出现次数最多的单词。
 *
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * 示例 1：
 *
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 * 
 *
 * 示例 2：
 *
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 *     出现次数依次为 4, 3, 2 和 1 次。
 * 
 *
 * 注意：
 *
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC692 {

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> strings = Solution.topKFrequent(words, 1);
        System.out.println(strings);
    }

    static class Solution {
        public static List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> cnt = new HashMap<String, Integer>();
            for (String word : words) {
                cnt.put(word, cnt.getOrDefault(word, 0) + 1);
            }
            //1.根据count排序
            //2.根据字母顺序排序
            List<String> keyList = cnt.keySet().stream().collect(Collectors.toList());
            keyList.sort((x1,x2)->{
                Integer count1 = cnt.get(x1);
                Integer count2 = cnt.get(x2);
                return count1.equals(count2) ? x1.compareTo(x2) : count2 - count1 ;
            });
            System.out.println(keyList);
            return keyList.subList(0,k);
        }
    }
}
