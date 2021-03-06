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
            // 如果
            if (numRows == 1) {
                return s;
            } else if (numRows == 2) {
                StringBuilder builder = new StringBuilder();
                int len = s.length();
                for (int i = 0; i < numRows; i++) {
                    for (int j = i; j < len; j+=2) {
                        builder.append(s.charAt(j));
                    }
                }
                return builder.toString();
            } else {
                StringBuilder builder = new StringBuilder();
                int len = s.length();
                int n = numRows * 2 - 2;
                for (int i = 0; i < numRows; i++) {
                    int j = i;
                    while(j < len) {
                        builder.append(s.charAt(j));
                        if (i == 0 || i == numRows-1) {
                            j += n;
                        } else {
                            j += 2 * (numRows-i-1);
                            if (j < len) {
                                builder.append(s.charAt(j));
                            }
                            j += 2*i;
                        }
                    }
                }
                return builder.toString();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}