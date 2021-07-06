//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针 排序 
// 👍 814 👎 0

package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * @author herenpeng
 * @since 2021-07-06 21:47:58
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new ThreeSumClosest().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            // 首先对数组进行一次有序排序
            Arrays.sort(nums);
            int i, l, r, sum, n = nums.length;
            // 随便获取目标值和三位数的差绝对值
            int result = nums[0] + nums[1] + nums[2];
            int cha = Math.abs(target - result), temCha;
            // 循环的最后一位是 [n-3], 最后三位相加 [n-3] + [n-2] + [n-1]
            for (i = 0; i < n - 2; i++) {
                // 在循环过程中，如果当前循环的值和前一位值相等，就可以不用循环，因为值都是一样的，结果也就是一样的
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // 获取当前循环的下一位元素
                l = i + 1;
                // 获取数组最后一位元素
                r = n - 1;
                while (l < r) {
                    sum = nums[i] + nums[l] + nums[r];
                    if (sum == target) {
                        return sum;
                    }
                    temCha = Math.abs(target - sum);
                    // 如果差值更小，将差值更小的和赋值给 result，并保存更小的差值
                    if (temCha < cha) {
                        result = sum;
                        cha = temCha;
                    }
                    // 因为是有序数组，如果值太小，则尝试增加左边的值
                    if (sum < target) {
                        l++;
                    }
                    // 因为是有序数组，如果值太小，则尝试减小右边的值
                    if (sum > target) {
                        r--;
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}