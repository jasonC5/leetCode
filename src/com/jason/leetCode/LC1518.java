package com.jason.leetCode;

public class LC1518 {
    public static int numWaterBottles(int numBottles, int numExchange) {
        int rest = numBottles, ans = numBottles;
        while (rest >= numExchange) {
            int exchange = rest / numExchange;
            ans += exchange;
            rest = exchange + (rest % numExchange);
        }
        return ans;
    }

    public static void main(String[] args) {
        int numBottles = 9, numExchange = 3;
        System.out.println(numWaterBottles(numBottles, numExchange));
    }
}
