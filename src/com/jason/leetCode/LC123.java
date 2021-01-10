package com.jason.leetCode;

public class LC123 {
    public static void main(String[] args) {
        int[] prices = {2,1,2,0,1};
        int maxProfit = maxProfit(prices);
        System.out.println(maxProfit);
    }

    public static int maxProfit(int[] prices) {
        if(prices.length < 2)
            return 0;
        //最多两笔交易，也就是说，0笔（一直跌） 1笔、2笔
        int maxProfit = 0;
        int len = prices.length;
        for (int i = 0; i<len; i++){//一个窗口，跑两部分方案
            int tmp1 = findMxWin(prices,0,i);
            int tmp2 = findMxWin(prices,i,len);
            if((tmp1 + tmp2) > maxProfit) {
                maxProfit = tmp1 + tmp2;
            }
        }
        //一次性两个窗口方案： btm1 top1 win1 | btm2 top2 win2  位置 ：btm1<top1<btm2<top2

        return maxProfit;
    }
    //function 找出指定窗口中的最大差额(增额)
    public static int findMxWin(int[] prices, int start, int end){//一个窗口
        if(end - start < 2){//只有0个或者1个的时候，不存在买入卖出
            return 0;
        }
        // int incrept = 0;
        //优化，复杂度太高
        // for(int i = start; i < end-1 ; i++){
        //     for(int j = i+1; j < end ; j++){
        //         int tmp = prices[j] - prices[i];
        //         if(tmp > incrept) {
        //             incrept = tmp;
        //         }
        //     }
        // }
        int bottom = prices[start];//买入日期的价格，
        // int top = prices[start];
        int maxWin = 0;
        for(int i = start; i < end ; i++){//一次性，只往前走，也就是模拟卖出日期，
            // if(prices[i] >top){//比之前的卖出价格高，重新计算
            //     top = prices[i];
            //     maxWin = prices[i] - bottom;
            // }
            //上述不对，不用比对top，可能top没变，但是buttom变小了，只比对差值，top无意义
            int tmp = prices[i] - bottom;
            if(tmp > maxWin){
                maxWin = tmp;
            }
            if(prices[i] < bottom){//买入价格新低，但是不影响之前的买卖计算，只影响往后的计算
                bottom = prices[i];
            }
        }
        return maxWin;
    }

}
