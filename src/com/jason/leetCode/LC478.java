package com.jason.leetCode;

import java.util.Random;

public class LC478 {
    class Solution {
        Random random;
        double x, y, r;

        public Solution(double radius, double x_center, double y_center) {
            random = new Random();
            x = x_center;
            y = y_center;
            r = radius;
        }

        public double[] randPoint() {
            for (; ; ) {
                double px = random.nextDouble() * 2 * r - r;
                double py = random.nextDouble() * 2 * r - r;
                if (px * px + py * py <= r * r) {
                    return new double[]{px + x, py + y};
                }
            }
        }
    }
}
