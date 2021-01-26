//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4852 👎 0

package com.leetcode.editor.cn;

/**
 * @author herenpeng
 * @since 2021-01-23 22:33:41
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int lengthOfLongestSubstring(String s) {
            // 排除只有一个或没有字符的情况
            if (s.length() <= 1) {
                return s.length();
            }
            // 设置开始指针
            int start = 0;
            // 设置结尾指针
            int end;
            // 初始化最大长度为0
            int maxLength = 0;
            String str;
            // 从头开始遍历该字符串，知道倒数第二个字符结束
            for (int i = 0; i < s.length() - 1; i++) {
                // 设置末尾为当前指针的后一位
                end = i + 1;
                // 截取开始索引到结束索引之间的字符串
                str = s.substring(start, end);
                // 获取结束索引的字符
                char endChar = s.charAt(end);
                // 判断开始索引到结束索引之间的字符串是否包含结束索引字符
                int index = str.indexOf(endChar);
                // 如果包含结束索引字符，改变开始索引的位置为相同字符的索引+1
                if (index > -1) {
                    start = start + index + 1;
                }
                int length = end - start + 1;
                if (length > maxLength) {
                    maxLength = length;
                }
            }
            return maxLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}