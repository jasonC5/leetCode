package com.jason.leetCode;


import java.util.*;

public class LC599 {
    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        List<String> ans = new ArrayList<>();
        int minIdxSum = list1.length + list2.length;
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int idxSum = map.get(list2[i]) + i;
                if (idxSum < minIdxSum) {
                    minIdxSum = idxSum;
                    ans.clear();
                    ans.add(list2[i]);
                } else if (idxSum == minIdxSum){
                    ans.add(list2[i]);
                }
            }
        }
        return ans.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] list1 = {"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2 = {"KFC","Shogun","Burger King"};
        System.out.println(Arrays.toString(findRestaurant(list1, list2)));
    }
}
