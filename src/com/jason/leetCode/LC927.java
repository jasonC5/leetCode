package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/10/6 8:38:11
 * @description
 */
public class LC927 {
    public int[] threeEqualPartsXXX(int[] arr) {
        int fst, sec = arr.length - 1;
        int[] none = new int[]{-1, -1};
        int sum = Arrays.stream(arr).sum();
        if (sum % 3 != 0) {
            return none;
        } else if (sum == 0) {
            return new int[]{0, 2};
        }
        int count = 0, delegate = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                // 每个区间的二进制，都得是这个数
                delegate |= 1 << (arr.length - 1 - i);
                if (++count == sum / 3) {
                    sec = i - 1;
                    break;
                }
            }
        }
        int width = arr.length - sec - 1;
        for (fst = sec + width; fst >= 0; fst--) {
            int num = getNum(arr, fst + 1, sec);
            if (num == delegate) {
                // sec确定了
                break;
            } else if (fst == 0) {
                return none;
            } else if (arr[sec - 1] == 1) {
                return none;
            }
        }
        for (; fst >= 0; fst--) {
            int num = getNum(arr, 0, fst - 1);
            if (num == delegate) {
                return new int[]{fst, sec};
            } else if (fst == 0) {
                return none;
            } else if (arr[sec - 1] == 1) {
                return none;
            }
        }
        return none;
    }

    private int getNum(int[] arr, int i, int j) {
        int ans = 0;
        for (int k = i; k <= j; k++) {
            ans <<= 1;
            ans |= arr[k];
        }
        return ans;
    }

    class Solution {
        public int[] threeEqualParts(int[] arr) {
            int sum = Arrays.stream(arr).sum();
            if (sum % 3 != 0) {
                return new int[]{-1, -1};
            }
            if (sum == 0) {
                return new int[]{0, 2};
            }

            int partial = sum / 3;
            int first = 0, second = 0, third = 0, cur = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 1) {
                    if (cur == 0) {
                        first = i;
                    } else if (cur == partial) {
                        second = i;
                    } else if (cur == 2 * partial) {
                        third = i;
                    }
                    cur++;
                }
            }

            int len = arr.length - third;
            if (first + len <= second && second + len <= third) {
                int i = 0;
                while (third + i < arr.length) {
                    if (arr[first + i] != arr[second + i] || arr[first + i] != arr[third + i]) {
                        return new int[]{-1, -1};
                    }
                    i++;
                }
                return new int[]{first + len - 1, second + len};
            }
            return new int[]{-1, -1};
        }
    }
}
