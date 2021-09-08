package com.jason.leetCode;

import java.util.PriorityQueue;

public class LC502 {
    /**
     * w
     *
     * @param k       选取的轮数
     * @param w       初始资本
     * @param profits 纯利润
     * @param capital 启动该项目需要的最小资本
     * @return
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        //启动资金小顶堆
        PriorityQueue<Info> capitalHeap = new PriorityQueue<>((i1, i2) -> i1.capital - i2.capital);
        //利润大顶堆
        PriorityQueue<Info> profitHeap = new PriorityQueue<>((i1, i2) -> i2.profit - i1.profit);
        for (int i = 0; i < profits.length; i++) {
            if (profits[i] <= 0) {
                continue;
            }
            capitalHeap.offer(new Info(profits[i], capital[i]));
        }
        int ans = w;
        for (int i = 0; i < k; i++) {
            // 找到当前资金能做的最大利润的项目
            while (!capitalHeap.isEmpty() && ans >= capitalHeap.peek().capital) {
                //把能做的项目，都扔到利润大顶堆中
                profitHeap.offer(capitalHeap.poll());
            }
            if (profitHeap.isEmpty()) {
                return ans;
            }
            //利润大顶堆中找出最大的利润的项目
            Info maxProfit = profitHeap.poll();
            ans += maxProfit.profit;
        }
        return ans;
    }

    public static class Info {
        int profit;//利润
        int capital;//启动资金

        public Info(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }

    public static void main(String[] args) {
        int k = 2, w = 0;
        int[] profits = {1, 2, 3}, capital = {0, 1, 1};
        System.out.println(findMaximizedCapital(k, w, profits, capital));
    }

}
