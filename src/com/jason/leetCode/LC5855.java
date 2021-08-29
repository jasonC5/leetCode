package com.jason.leetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个字符串数组 nums 和一个整数 k 。nums 中的每个字符串都表示一个不含前导零的整数。
 * <p>
 * 返回 nums 中表示第 k 大整数的字符串。
 * <p>
 * 注意：重复的数字在统计时会视为不同元素考虑。例如，如果 nums 是 ["1","2","2"]，那么 "2" 是最大的整数，"2" 是第二大的整数，"1" 是第三大的整数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = ["3","6","7","10"], k = 4
 * 输出："3"
 * 解释：
 * nums 中的数字按非递减顺序排列为 ["3","6","7","10"]
 * 其中第 4 大整数是 "3"
 * 示例 2：
 * <p>
 * 输入：nums = ["2","21","12","1"], k = 3
 * 输出："2"
 * 解释：
 * nums 中的数字按非递减顺序排列为 ["1","2","12","21"]
 * 其中第 3 大整数是 "2"
 * 示例 3：
 * <p>
 * 输入：nums = ["0","0"], k = 2
 * 输出："0"
 * 解释：
 * nums 中的数字按非递减顺序排列为 ["0","0"]
 * 其中第 2 大整数是 "0"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-kth-largest-integer-in-the-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC5855 {


    public static void main(String[] args) {
//        String[] arr = {"3", "6", "7", "10"};
//        System.out.println(kthLargestNumber(arr, 4));
        String[] arr = {"0", "1", "1"};
        System.out.println(kthLargestNumber(arr, 1));
    }

    public static String kthLargestNumber(String[] nums, int k) {

        Comparator<String> comparator = (s1, s2) -> {
            if (s1.length() != s2.length()) {
                return s1.length() - s2.length();
            }
            if (s1.equals(s2)) {
                return 0;
            }
            for (int i = 0; i < s1.length(); i++) {
                char c1, c2;
                if ((c1 = s1.charAt(i)) != (c2 = s2.charAt(i))) {
                    return c1 - c2;
                }
            }
            return 0;
        };

        //小根堆
        PriorityQueue<String> heap = new PriorityQueue<String>(comparator);
        for (String num : nums) {
            if (heap.size() < k || comparator.compare(num, heap.peek()) > 0) {
                heap.add(num);
            }
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.poll();
    }

}
