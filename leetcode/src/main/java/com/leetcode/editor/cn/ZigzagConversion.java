//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 
// 👍 1014 👎 0

package com.leetcode.editor.cn;

/**
 * @author herenpeng
 * @since 2021-02-18 18:22:44
 */
public class ZigzagConversion {
    public static void main(String[] args) {
        Solution solution = new ZigzagConversion().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convert(String s, int numRows) {
            // 如果行数为1，直接返回原字符串
            if (numRows == 1) {
                return s;
            } else if (numRows == 2) {
                // 如果行数为2，则按照索引奇偶数的顺序进行排列，因为索引从0开始，所以第一行为偶数索引，第二行为奇数索引
                StringBuilder builder = new StringBuilder();
                int len = s.length();
                // 循环2次，第一次i=0（第一行），第二次i=2（第二行）
                for (int i = 0; i < numRows; i++) {
                    // 索引从0开始或者从1开始，每次跳跃一位字符
                    for (int j = i; j < len; j += 2) {
                        builder.append(s.charAt(j));
                    }
                }
                return builder.toString();
            } else {
                // 其他情况，将一个N字形的字符串拆分为V字形处理
                StringBuilder builder = new StringBuilder();
                int len = s.length();
                // 计算出首行和最后一行的字符间隔
                int n = numRows * 2 - 2;
                // 循环行数次数
                for (int i = 0; i < numRows; i++) {
                    int j = i;
                    // 只要当前索引小于字符串长度
                    while (j < len) {
                        // 开始拼接每一行的字符
                        builder.append(s.charAt(j));
                        // 如果是第一行或者是最后一行，直接跳跃n个字符
                        if (i == 0 || i == numRows - 1) {
                            j += n;
                        } else {
                            // 其他行数
                            // 每行的第一个字符和第二个字符的跳跃长度为2 * (行数 - 当前行数 - 1)
                            j += 2 * (numRows - i - 1);
                            if (j < len) {
                                // 如果对应的字符串索引还在字符串长度范围之内，则继续拼接
                                builder.append(s.charAt(j));
                            }
                            // 每行的第二个字符和第三个字符的跳跃长度为2 * 当前行数
                            j += 2 * i;
                        }
                    }
                }
                return builder.toString();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}