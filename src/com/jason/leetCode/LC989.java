package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LC989 {
    public static void main(String[] args) {
        int[] A = {1,2,0,0};
        int K = 34;
        List<Integer> integers = addToArrayForm(A, K);
    }

    public static List<Integer> flip (List<Integer> list){
        List<Integer> flipList = new ArrayList<>();
        for (int i=0;i<list.size();i++) {
            flipList.add(list.get(list.size()-i-1));
        }
        return flipList;
    }

    public static List<Integer> addToArrayForm(int[] A, int K) {
        //不能直接加减，应该会超出对象长度
        List<Integer> B = new ArrayList();
        while(true){
            int num = K%10;
            B.add(num);
            K = K/10;
            if(K == 0){
                break;
            }
        }
        B = flip(B);
        List<Integer> temp = new ArrayList();
        int jw = 0;//进位
        //可能要补位，a和k谁大还不好说
        for(int i=0; i< B.size();i++){
            int a = 0;
            if (i < A.length) {
                a = A[A.length-i-1];
            }
            int b = B.get(B.size()-i-1);
            int sum = a+b+jw;
            jw = sum/10;
            temp.add(sum%10);
        }
        if (A.length > B.size()) {
            for(int i=0; i< A.length - B.size();i++){
                int sum = A[A.length-B.size()-i-1] + jw;
                jw = sum/10;
                temp.add(sum%10);
            }
        }
        if (jw > 0) {//可能还有一位
            temp.add(jw);
        }
        //翻转
        List<Integer> result = flip(temp);
        return result;
    }
}
