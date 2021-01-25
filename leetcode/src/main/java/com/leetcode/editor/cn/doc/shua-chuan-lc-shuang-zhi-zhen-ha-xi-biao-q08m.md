# 双指针 + 哈希表解法

定义两个指针 i，j，表示当前处理到的子串是 [i,j]。[i,j] 始终满足无重复字符。

从前往后进行扫描，同时维护一个哈希表记录 [i,j] 中每个字符出现的次数。

遍历过程中，j 不断自增，同时将第 j 个字符在哈希表中出现的次数加一。

当满足 `map.get(r) > 1` 代表此前出现过第 j 位对应的字符。此时应该更新 i 的位置（使其右移），直到不满足 `map.get(r) > 1` ，代表 [i,j] 恢复满足无重复字符的条件。同时使用 [i,j] 长度更新最大长度。

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char r = s.charAt(j);
            map.put(r, map.getOrDefault(r, 0) + 1);
            while (map.get(r) > 1) {
                char l = s.charAt(i);
                map.put(l, map.get(l) - 1);
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
```

时间复杂度：虽然有两层循环，但每个字符在哈希表中最多只会被插入和删除一次，复杂度为 O(n)

空间复杂度：使用了哈希表进行字符记录，复杂度为 O(n)

***

# 最后
这是 [「宫水三叶的刷题日记」](https://mp.weixin.qq.com/s/_E_M-237pmh5K2TEmbmmag) 的 LeetCode 系列文章的第 `No.3` 篇，系列开始于 2021/01/01，截止于起始日 LeetCode 上共有 1916 道题目，部分是有锁题，我们将先将所有不带锁的题目刷完。进度为 `3/1916`

在这个系列文章里面，除了讲解解题思路以外，还会尽可能给出最为简洁的代码。如果涉及通解还会相应的代码模板。

关注 [「宫水三叶的刷题日记」](https://mp.weixin.qq.com/s/_E_M-237pmh5K2TEmbmmag) ，和三叶一起刷穿 LeetCode ~