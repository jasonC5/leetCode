package com.jason.leetCode;

class LC189 {
    // public void rotate(int[] nums, int k) {
    //     k = k % nums.length;
    //     int len = nums.length;
    //     int[] tmp = new int[len];
    //     for(int i = 0 ; i< k;i++){
    //         tmp[i] = nums[len-k+i];
    //     }
    //     for(int i = 0; i< len-k;i++ ){
    //         tmp[k+i] = nums[i];
    //     }
    //     for(int i = 0; i< len;i++){
    //         nums[i] = tmp[i];
    //     }
    // }

    public static void main(String[] args) {
        int []nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate(nums, k);
    }

    public static void rotate(int[] nums, int k) {
        //1.整体翻转
        //2.找到游标k位置，前后都做翻转
        int len = nums.length;
        k = k%len;

        turn(nums,0, len-1);
        turn(nums,0, k-1);
        turn(nums,k, len-1);
    }

    public static void turn(int[] nums, int start ,int end){
        int con = (end - start + 1)/2;
        for(int i=0;i<con;i++){
            int tmp = nums[start+i];
            nums[start+i] = nums[end-i];
            nums[end-i] = tmp;
        }
    }
}
