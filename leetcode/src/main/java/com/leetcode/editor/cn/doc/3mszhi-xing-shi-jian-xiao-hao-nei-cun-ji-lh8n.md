### 解题思路
```
   分三种情况讨论：
	1. 为一字型字符串，直接返回原字符串
	2. 为两行字符串构成，直接双循环，利用奇偶性将字符加入 StringBuilder对象
	3. 为带间隔的Z字型字符串，先for循环 numRows 次，从每行的每个字符的间隔找规律
		除第一行与第numRows行每个字符间隔(numRows*2-2)外，发现其它行每行第2*i(i>=0)字符与
		第2*i+1个字符间隔为2 * (numRows-i-1), 第2*i+1个字符与第2*i+2个字符间隔为 2*i
	复杂度分析：
		因只遍历字符串s的每个字符一遍，所以时间复杂度为 O(n)
		每次都在for循环中定义使用了变量j
		所以空间复杂度为 O(n)
```
### 执行结果 
![SharedScreenshot.jpg](https://pic.leetcode-cn.com/1614050980-DNbGOP-SharedScreenshot.jpg)

### 代码

```java
class Solution {
    public String convert(String s, int numRows) {
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
```