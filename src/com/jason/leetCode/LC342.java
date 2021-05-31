package com.jason.leetCode;

public class LC342 {
    public static void main(String[] args) {
//        System.out.println(isPowerOfFour(1));
//        System.out.println(isPowerOfFour(4));
//        System.out.println(isPowerOfFour(5));
//        System.out.println(isPowerOfFour(8));
//        System.out.println(isPowerOfFour(16));

//        System.out.println(isPowerOfFour2(1));
//        System.out.println(isPowerOfFour2(4));
//        System.out.println(isPowerOfFour2(5));
//        System.out.println(isPowerOfFour2(8));
//        System.out.println(isPowerOfFour2(16));
        int times = 10000;
        int range = 1000_000_000;
        for (int i = 0; i < times; i++) {
            int num = (int)(Math.random() * range) - (int)(Math.random() * range);
            if (isPowerOfFour(num) != isPowerOfFour2(num)) {
                System.out.println("failed!! num = " +num);
            }
        }
        System.out.println("complete!!!");
        return;

    }

    public static boolean isPowerOfFour(int n) {
        // if(n <= 0){
        //     return false;
        // }
        //1.n>0
        //2.n是二的倍数
        //3.这个n必须出现在奇数位上 如：  0001  0100   010000   mask = 8个 0101  与出来 ！=0

        return (n > 0)&&((n&(-n))==n)&&(n & 0x55555555 )!= 0;
    }
    //对数器
    public static boolean isPowerOfFour2(int n) {
        while (n >= 4){
            if (n % 4 != 0){
                return false;
            }
            n = n/4;
        }
        return n==1;
    }

}
