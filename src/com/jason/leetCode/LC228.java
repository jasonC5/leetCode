package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LC228 {
    /**
     * 228. 汇总区间
     * 给定一个无重复元素的有序整数数组 nums 。
     *
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     *
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     *
     * "a->b" ，如果 a != b
     * "a" ，如果 a == b
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/summary-ranges
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> request = new ArrayList();
            if (nums.length == 0) return request;
            Integer startVal = null;
            Integer cursor = null;
            String startStr = null;
            String endVal = null;
            for(int i = 0; i<nums.length;i++){
                if (startVal == null) {//startVal 为空，起始值
                    startVal = nums[i];
                    cursor = nums[i];
                    startStr = startVal.toString();
                } else if(cursor.equals(nums[i] -1)){//连续，取区间
                    //
                    cursor = nums[i];
                    endVal = String.valueOf(nums[i]);//结尾更新
                } else {//不连续，区间终止，重新取start
                    String section = startStr;
                    if(endVal != null){
                        section = section + "->"+endVal;
                    }
                    request.add(section);
                    endVal = null;
                    startVal = nums[i];
                    cursor = nums[i];
                    startStr = startVal.toString();
                }
                if(i == nums.length-1){//触发结束
                    String section = startStr;
                    if(endVal != null){
                        section = section + "->"+endVal;
                    }
                    request.add(section);
                }
            }
            return request;
        }
    }
}
