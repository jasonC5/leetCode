package com.jason.jingsai.competition281;

public class C6012 {
    public static int countEven(int num) {
        int ans = 0;
        for (int i = 2; i <= num; i++) {
            int sum = 0, cur = i;
            while (cur > 0) {
                sum += cur % 10;
                cur /= 10;
            }
            if ((sum & 1) == 0) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countEven(30));
    }
}
