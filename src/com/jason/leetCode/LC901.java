package com.jason.leetCode;

import java.util.Stack;

/**
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * <p>
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * <p>
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 * <p>
 * <p>
 * <p>
 * 示例：
 *
 * @author chenjieaj
 * @date 2022/10/21 9:18:06
 * @description
 */
public class LC901 {

    class StockSpanner {
        // 单调栈
        Stack<int[]> stack;
        int cur;

        public StockSpanner() {
            stack = new Stack<>();
            cur = -1;
            stack.push(new int[]{cur, Integer.MAX_VALUE});
        }

        public int next(int price) {
            cur++;
            while (price >= stack.peek()[1]) {
                stack.pop();
            }
            int ans = cur - stack.peek()[0];
            stack.push(new int[]{cur, price});
            return ans;
        }
    }

}
