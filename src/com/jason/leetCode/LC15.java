package com.jason.leetCode;

import java.util.*;
import java.util.stream.Collectors;

public class LC15 {

    static class Info {
        int x1, x2, sum;
        String marker;

        public Info(int x1, int x2) {
            this.x1 = Math.min(x1, x2);
            this.x2 = Math.max(x1, x2);
            sum = x1 + x2;
            marker = this.x1 + "_" + this.x2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return marker.equals(info.marker);
        }

        @Override
        public int hashCode() {
            return Objects.hash(marker);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Map<Integer, Set<Info>> sumMap = new HashMap<>();
        /*两个下标组合起来，前16位1号下标，后16位2号下标*/
        sumMap.put(nums[0] + nums[1], new HashSet<Info>() {{
            this.add(new Info(nums[0], nums[1]));
        }});
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> exists = new HashSet<>();
        for (int i = 2; i < nums.length; i++) {
            Set<Info> index;
            if ((index = sumMap.get(-nums[i])) != null) {
                for (Info info : index) {// 两个下标
                    List<Integer> list = new ArrayList<>();
                    list.add(info.x1);
                    list.add(info.x2);
                    list.add(nums[i]);
                    list.sort((i1, i2) -> i1 - i2);
                    String str = list.stream().map(Object::toString).collect(Collectors.joining("-"));
                    if (!exists.contains(str)) {
                        exists.add(str);
                        ans.add(list);
                    }
                }
            }
            for (int j = 0; j < i; j++) {
                Info info = new Info(nums[i], nums[j]);
                Set<Info> infoList = sumMap.getOrDefault(info.sum, new HashSet<>());
                infoList.add(info);
                sumMap.put(info.sum, infoList);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(threeSum(nums));// 超时
    }

//    public static List<List<Integer>> threeSum2(int[] nums) {
//        Arrays.sort(nums);
//
//    }


}
