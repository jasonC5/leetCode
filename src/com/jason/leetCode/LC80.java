package com.jason.leetCode;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *  
 *
 * 说明：
 *
 * 为什么返回数值是整数，但输出的答案是数组呢？
 *
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 *
 * 提示：
 *
 * 0 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按升序排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class LC80 {
    public static void main(String[] args) {
        int[]nums = {1,1,1,2,2,3};
        int length = removeDuplicates(nums);
        System.out.println(length);
    }
    public static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;//只用一个参数
//        int count = 1;//上一元素已出现次数
//        int prov = nums[0];
        //若下一个和上一个相同则看count是否==1,=1则保留，>1则删除【后面的元素挨个往前挪1】，length--
        //不相同则count=1，往后走
        for (int i = 1; i < length; i++) {
            if (nums[i] == nums[i-1]) {
                if (i>1 && nums[i]==nums[i-2] ) {//与前一个也相等，不行，最多只能重复出现一次，删除
                    //删除当前元素==后面的元素往前移
//                    removeThis(nums, i);
                    for (int j = i; j < nums.length-1; j++) {
                        nums[j] = nums[j+1];
                    }
                    i--;//删了一个，下次要再从这开始
                    length--;//总长度-1
                } else {
                    //啥也不干往下走
                }
            } else {//不同，往下走
                //不能，充值prov和count，往后走
//                count = 1;
//                prov = nums[i];
            }
        }
        return length;
    }
}