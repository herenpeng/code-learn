执行用时：9 ms，在 Longest Substring Without Repeating Characters 的 Java 提交中击败了 98.66% 的用户
内存消耗：35.8 MB，在 Longest Substring Without Repeating Characters 的 Java 提交中击败了 99.52% 的用户

## 总体思路
遍历字符串，每次以 i 值记录，不回溯 i 值，用flag记录遍历过程找到的重复的字符的位置。如果遇到重复字符，i-flag 即为子串长度，此时flag重新定位到子串中重复字符的位置，i 继续往后遍历。这里length跟result记录长度。我感觉代码可以更简洁一点的，但是好像写懵了？

## 图解

![](https://pic.leetcode-cn.com/36efcc3cc13acb0bc572a1ed9139b51cd00ad4502c70ac0a98d38c7de4765076-%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20190516154813.png)



```Java []
class Solution {
	public int lengthOfLongestSubstring(String s) {
		int i = 0;
		int flag = 0;
		int length = 0;
		int result = 0;
		while (i < s.length()) {
			int pos = s.indexOf(s.charAt(i),flag);
			if (pos < i) {
				if (length > result) {
					result = length;
				}
				if (result >= s.length() - pos - 1) {
					return result;
				}
				length = i - pos - 1;
				flag = pos + 1;
			}
			length++;
			i++;
		}
		return length;
	}
}
```