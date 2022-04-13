package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LC380 {
    static class RandomizedSet {
        int[] arr;
        int len;
        Map<Integer, Integer> map;
        Random random;

        public RandomizedSet() {
            arr = new int[200001];
            len = 0;
            map = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, len);
            arr[len++] = val;
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            Integer idx = map.remove(val);
            if (idx != len - 1) {
                map.put(arr[len - 1], idx);
                arr[idx] = arr[len - 1];
            }
            len--;
            return true;
        }

        public int getRandom() {
            int idx = random.nextInt(len);
            return arr[idx];
        }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(0));
    }


}
