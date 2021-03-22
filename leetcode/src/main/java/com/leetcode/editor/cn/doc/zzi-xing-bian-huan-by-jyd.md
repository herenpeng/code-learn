#### 解题思路：

- **题目理解：** 
    - 字符串 `s` 是以 *Z* 字形为顺序存储的字符串，目标是按行打印。
    - 设 `numRows` 行字符串分别为 *s_1* , *s_2* ,..., *s_n*，则容易发现：按顺序遍历字符串 `s` 时，每个字符 `c` 在 *Z* 字形中对应的 **行索引** 先从 *s_1* 增大至 *s_n*，再从 *s_n* 减小至 *s_1* …… 如此反复。
    - 因此，解决方案为：模拟这个行索引的变化，在遍历 `s` 中把每个字符填到正确的行 `res[i]` 。
- **算法流程：** 按顺序遍历字符串 `s`；
    1. **`res[i] += c`：** 把每个字符 `c` 填入对应行 *s_i*；
    2. **`i += flag`：** 更新当前字符 `c` 对应的行索引；
    3. **`flag = - flag`：** 在达到 *Z* 字形转折点时，执行反向。
- **复杂度分析：**
    - 时间复杂度 *O(N)* ：遍历一遍字符串 `s`；
    - 空间复杂度 *O(N)* ：各行字符串共占用 *O(N)* 额外空间。

 ![Picture1.png](https://pic.leetcode-cn.com/c7f53f8480c33925ecae3cd91ac4b20337949de67a255663cc550bdc68ba9315-Picture1.png) ![Picture2.png](https://pic.leetcode-cn.com/bfcbaa31dc07dbf0e68a854e6da8445abe67432d3b624ae627f1195dd3c54d6e-Picture2.png) ![Picture3.png](https://pic.leetcode-cn.com/4604c49a47c1cf995f292f17313104fc5720a340a3bd649410734ecace7108a7-Picture3.png) ![Picture4.png](https://pic.leetcode-cn.com/4ecbe654add7b2b80d4dd81038e4681607b7cbef469fa27ae954fa789d13ed82-Picture4.png) ![Picture5.png](https://pic.leetcode-cn.com/d26d1faedbe13f78a94c28047fc4dc91fb72419452b3edae669e44a4d730d5ff-Picture5.png) ![Picture6.png](https://pic.leetcode-cn.com/e8fdc68fb3029017333e01f9a3e25e03675f87260e49f53fee7938b4d02ca997-Picture6.png) ![Picture7.png](https://pic.leetcode-cn.com/e1db50ce219e68d2d6a57b197b932088731dd09afc788ee818e0b38880458bb5-Picture7.png) ![Picture8.png](https://pic.leetcode-cn.com/ebbed8592bd11014e81affb8af6df3e713d88ae0e8003f4f989459d7694e475c-Picture8.png) ![Picture9.png](https://pic.leetcode-cn.com/5c7b6ebd51be1e16eab6c1ccd3121d6dae2aff3b61fa07ecb21235250c33e76c-Picture9.png) 

#### 代码：

```Python []
class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows < 2: return s
        res = ["" for _ in range(numRows)]
        i, flag = 0, -1
        for c in s:
            res[i] += c
            if i == 0 or i == numRows - 1: flag = -flag
            i += flag
        return "".join(res)
```

```Java []
class Solution {
    public String convert(String s, int numRows) {
        if(numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows -1) flag = - flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) res.append(row);
        return res.toString();
    }
}
```