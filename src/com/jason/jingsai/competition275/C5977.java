package com.jason.jingsai.competition275;

public class C5977 {
    // 次数范围：0~1的个数
    public static int minSwaps(int[] nums) {
        int count = 0;//几个1
        int length = nums.length;
        for (int num : nums) {
            count += num;
        }
        if (count == 0 || count == 1 || count == length || count == length - 1) {
            return 0;
        }
        int current = 0;
        // 滑动窗口
        int[] zeroNum = new int[count];
        for (int i = 0; i < count; i++) {
            current += nums[i] == 0 ? 1 : 0;
            zeroNum[i] = current;
        }
        int ans = current;
        for (int i = count; i < length; i++) {
            current += nums[i] == 0 ? 1 : 0;
            current -= nums[i - count] == 0 ? 1 : 0;
            ans = Math.min(ans, current);
        }
        for (int i = length - count; i < length; i++) {
            current -= nums[i] == 0 ? 1 : 0;
            ans = Math.min(ans, current + zeroNum[i - length + count]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] num = {0,1,0,0,1,0,0,0,1};
        System.out.println(minSwaps(num));
    }
}
