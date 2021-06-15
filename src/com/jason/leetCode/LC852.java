package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC852 {

    public static void main(String[] args) {
        //浏览器编写直接过了
    }

    public int peakIndexInMountainArray(int[] arr) {
        //前提：arr本身就是一个山脉数组
        //找到山脉顶峰，也就是 arr[i-1] < arr[i] > arr[i+1]
        //二分法：对于山脉数组中的任意一个节点只有三种情况：
        //arr[i-1] < arr[i] > arr[i+1] 【顶峰】
        //arr[i-1] < arr[i] < arr[i+1] 【上坡，顶峰在后面】
        //arr[i-1] > arr[i] > arr[i+1] 【下坡，顶峰在前面】
        int length = arr.length;
        int start = 0, end = length-1;
        while(start <= end){
            int mid = start + (((end - start)/2) >> 1);
            int check = checkTop(arr, mid);
            if (check > 0) {
                //中点上坡
                start = mid + 1;
            } else if (check < 0) {
                //中点下坡
                end = mid;
            } else {
                return mid;
            }
        }
        return start;
    }

    public int checkTop(int[] arr, int index) {
        int length = arr.length;
        if(length <= 1){
            return 0;
        }
        if (index == 0) {
            if(arr[0] > arr[1]) {
                return 0;
            } else {
                return 1;
            }
        } else if(index == (length -1)){
            if(arr[index-1] < arr[index]) {
                return 0;
            } else {
                return -1;
            }
        } else {
            //在中间，而且前后都有值
            if(arr[index-1] < arr[index] && arr[index] < arr[index+1]){
                return 1;
            } else if (arr[index-1] > arr[index] && arr[index] > arr[index+1]) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
