### 思路

- 标签：滑动窗口
- 暴力解法时间复杂度较高，会达到 *O(n^2)*，故而采取滑动窗口的方法降低时间复杂度
- 定义一个 map 数据结构存储 (k, v)，其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
- 我们定义不重复子串的开始位置为 start，结束位置为 end
- 随着 end 不断遍历向后，会遇到与 [start, end] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value 值，并更新 start，此时 [start, end] 区间内不存在重复字符
- 无论是否更新 start，都会更新其 map 数据结构和结果 ans。
- 时间复杂度：*O(n)*

### 代码

```Java []
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }
}
```

### 画解

 ![frame_00001.png](https://pic.leetcode-cn.com/2847c2d9fb9a6326fecfcf8831ed1450046f1e10967cde9d8681c42393d745ff-frame_00001.png) ![frame_00002.png](https://pic.leetcode-cn.com/159cc7509e4a5acbfaf5c59b4b5cb1674f1a31fb87cc41528ca6e6df6132b1dc-frame_00002.png) ![frame_00003.png](https://pic.leetcode-cn.com/a62a6d9c878b4c856db1467b4282b936ee677d02a3b47ac4c67dfb4269a158f6-frame_00003.png) ![frame_00004.png](https://pic.leetcode-cn.com/7b672e389b1659d3ff2ba77101cf49de120a21732dd7aed5a707d8b33d6b2fb6-frame_00004.png) ![frame_00005.png](https://pic.leetcode-cn.com/ff8f38005f548beb5bd45a2e5e327f71acf069c8ad6e9680caeee655af71533a-frame_00005.png) ![frame_00006.png](https://pic.leetcode-cn.com/2f054f105ebcbe7a1cf3cce1a4ab8c0d85cef70fe674bb90a1c83e92dc6b1274-frame_00006.png) ![frame_00007.png](https://pic.leetcode-cn.com/018b08f276a746262cf64fa1cf0748d815f3cabe9c29c61f4973b6e6dd44e2c8-frame_00007.png) ![frame_00008.png](https://pic.leetcode-cn.com/385c6b1623b2d686e42e11882be13e6e717975fd0399712113992a318d7ca1e5-frame_00008.png) 

想看大鹏画解更多高频面试题，欢迎阅读大鹏的 LeetBook：[《画解剑指 Offer 》](https://leetcode-cn.com/leetbook/detail/illustrate-lcof/)，O(∩_∩)O