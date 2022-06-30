package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/6/30 9:16:07
 * @description
 */
public class LC1175 {
    public static final int MOD = 1000000007;

    public static int numPrimeArrangements(int n) {
        int k = primeCount(n);
        long ans = 1;
        for (int i = 1; i <= k; i++) {
            ans = (ans * i) % MOD;
        }
        for (int i = 1; i <= (n - k); i++) {
            ans = (ans * i) % MOD;
        }
        return (int) ans;
    }

    private static int primeCount(int n) {
        int count = 0;
        retry:
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    continue retry;
                }
            }
            count++;
        }
        return count;
    }

    public static int numPrimeArrangements2(int n) {
        int numPrimes = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                numPrimes++;
            }
        }
        return (int) (factorial(numPrimes) * factorial(n - numPrimes) % MOD);
    }

    public static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long factorial(int n) {
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
            res %= MOD;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(numPrimeArrangements(100));
        System.out.println(numPrimeArrangements2(100));
    }

}
