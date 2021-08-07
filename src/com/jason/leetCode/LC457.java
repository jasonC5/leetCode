package com.jason.leetCode;

/**
 * 存在一个不含 0 的 环形 数组nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
 * <p>
 * 如果 nums[i] 是正数，向前 移动 nums[i] 步
 * 如果nums[i] 是负数，向后 移动 nums[i] 步
 * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
 * <p>
 * 数组中的 循环 由长度为 k 的下标序列 seq ：
 * <p>
 * 遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * 所有 nums[seq[j]] 应当不是 全正 就是 全负
 * k > 1
 * 如果 nums 中存在循环，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,-1,1,2,2]
 * 输出：true
 * 解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 * 示例 2：
 * <p>
 * 输入：nums = [-1,2]
 * 输出：false
 * 解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
 * 示例 3:
 * <p>
 * 输入：nums = [-2,1,-1,-2,-2]
 * 输出：false
 * 解释：按下标 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为 nums[1] 是正数，而 nums[2] 是负数。
 * 所有 nums[seq[j]] 应当不是全正就是全负。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/circular-array-loop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC457 {

    public boolean circularArrayLoop(int[] nums) {
        //1.每个环里面需要方向相同
        //2.一个数组中可能存在多个换，所以需要每个数组都遍历，若发现有环但不满足条件，则整条环全部置空然后处理下一个
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            //如果之前来过，但是不满足条件，continue
            if (nums[i] == 0) {
                continue;
            }
            //slow每次走一步，fast每次走两步
            int slow = i, fast = next(nums, i);
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, /*slow*/fast)] > 0) {//fast每次跳两步，如果不比中间跳过的，就没机会对比了
                if (slow == fast) {
                    if (slow != next(nums, slow)) {
                        return true;
                    } else {
                        //将沿途所有的标0
                        int add = i;
                        while (nums[add] * nums[next(nums, add)] > 0) {
                            int tmp = add;
                            add = next(nums, add);
                            nums[tmp] = 0;
                        }
                    }
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }
        }
        return false;
    }

    private int next(int[] nums, int idx) {
        int length = nums.length;
        return ((idx + nums[idx]) % length + length) % length;
    }

}
