//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治算法 
// 👍 3627 👎 0

package com.leetcode.editor.cn;

/**
 * @author herenpeng
 * @since 2021-01-27 21:12:49
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length1 = nums1.length;
            int length2 = nums2.length;
            // 如果nums1数组长度为0，则单独计算nums2数组的中位数
            if (length1 == 0) {
                if (length2 % 2 == 0) {
                    return (nums2[(length2 / 2) - 1] + nums2[(length2 / 2)]) / 2.0;
                } else {
                    return nums2[(length2 / 2)];
                }
            }
            // 如果nums2数组长度为0，则单独计算nums1数组的中位数
            if (length2 == 0) {
                if (length1 % 2 == 0) {
                    return (nums1[(length1 / 2) - 1] + nums1[(length1 / 2)]) / 2.0;
                } else {
                    return nums1[(length1 / 2)];
                }
            }
            int countLength = length1 + length2;
            int[] arr = new int[countLength];
            int index = 0;
            int i = 0, j = 0;
            // 如果两个数组都有数据
            while (true) {
                // 如果num1数组的数组全部都已经复制到了arr数组中
                if (i == length1) {
                    // 将num2数组剩余的数据复制到arr数组中
                    while (j != length2) {
                        arr[index++] = nums2[j++];
                    }
                    break;
                }
                // 如果num2数组的数组全部都已经复制到了arr数组中
                if (j == length2) {
                    // 将num1数组剩余的数据复制到arr数组中
                    while (i != length1) {
                        arr[index++] = nums1[i++];
                    }
                    break;
                }
                // 如果nums1对应索引的数据小于nums1对应索引的数据，将数据小的复制到arr数组中，并移动索引，达到排序的效果
                if (nums1[i] < nums2[j]) {
                    arr[index++] = nums1[i++];
                } else {
                    arr[index++] = nums2[j++];
                }
            }
            // 计算排序之后的数组的中位数即可
            if (countLength % 2 == 0) {
                return (arr[(countLength / 2) - 1] + arr[(countLength / 2)]) / 2.0;
            } else {
                return arr[(countLength / 2)];
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}