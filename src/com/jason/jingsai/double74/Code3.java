package com.jason.jingsai.double74;

import java.math.BigDecimal;
import java.util.PriorityQueue;

public class Code3 {
    // 堆
    public static int halveArray(int[] nums) {
        PriorityQueue<Double> heap = new PriorityQueue<>((d1, d2) -> d2.compareTo(d1));
        double sum = 0D;
        for (int num : nums) {
            sum += num;
            heap.add(num + 0.0);
        }
        double checkSum = sum;
        int count = 0;
        while (checkSum > sum / 2) {
            count++;
            Double max = heap.poll();
            max /= 2;
            heap.add(max);
            checkSum -= max;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] n = {5, 19, 8, 1};
        System.out.println(halveArray(n));
    }
}
