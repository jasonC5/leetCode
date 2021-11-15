package com.jason.leetCode;

public class LC319 {
    // 暴力解
    public int bulbSwitch0(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                count += i % j == 0 ? 1 : 0;//
            }
            ans += count & 1;
        }
        return ans;
    }

    // 数学  i%x ==0 i/x = y, 那么一定有 i%y==0，当x!=y的时候，x、y一定是成对出现的，此时一定是偶数个，只有当存在x=y的时候 ，会多出来一个，成为奇数个
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }


}
