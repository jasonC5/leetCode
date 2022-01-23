package com.jason.leetCode;

import java.util.*;

public class LC2034 {
    class StockPrice {
        TreeMap<Integer, Integer> priceCountOrderedTable;//price count
        HashMap<Integer, Integer> timePriceMap;//time price
        int newTime;

        public StockPrice() {
            priceCountOrderedTable = new TreeMap<>();
            newTime = 0;
            timePriceMap = new HashMap<>();
        }

        // 时间 --> 股票价格
        public void update(int timestamp, int price) {
            newTime = Math.max(newTime, timestamp);
            int prevPrice = timePriceMap.getOrDefault(timestamp, 0);
            timePriceMap.put(timestamp, price);
            if (prevPrice > 0) {
                priceCountOrderedTable.put(prevPrice, priceCountOrderedTable.get(prevPrice) - 1);
                if (priceCountOrderedTable.get(prevPrice) == 0) {
                    priceCountOrderedTable.remove(prevPrice);
                }
            }
            priceCountOrderedTable.put(price, priceCountOrderedTable.getOrDefault(price, 0) + 1);
        }

        // 最新价格
        public int current() {
            return timePriceMap.get(newTime);
        }

        // 最高价格
        public int maximum() {
            return priceCountOrderedTable.lastKey();
        }

        // 最低价格
        public int minimum() {
            return priceCountOrderedTable.firstKey();
        }
    }


}
