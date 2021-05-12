### 第一种思路

- 标签：拷贝覆盖
- 主要思路是遍历数组 nums，每次取出的数字变量为 num，同时设置一个下标 ans
- 在遍历过程中如果出现数字与需要移除的值不相同时，则进行拷贝覆盖 `nums[ans] = num`，ans 自增 1
- 如果相同的时候，则跳过该数字不进行拷贝覆盖，最后 ans 即为新的数组长度
- **这种思路在移除元素较多时更适合使用，最极端的情况是全部元素都需要移除，遍历一遍结束即可**
- 时间复杂度：*O(n)*，空间复杂度：*O(1)*

### 第一种代码

```java [-Java]
class Solution {
    public int removeElement(int[] nums, int val) {
        int ans = 0;
        for(int num: nums) {
            if(num != val) {
                nums[ans] = num;
                ans++;
            }
        }
        return ans;
    }
}
```
```javascript [-JavaScript]
/**
 * @param {number[]} nums
 * @param {number} val
 * @return {number}
 */
var removeElement = function(nums, val) {
    let ans = 0;
    for(const num of nums) {
        if(num != val) {
            nums[ans] = num;
            ans++;
        }
    }
    return ans;
};
```

### 第二种思路

- 标签：交换移除
- 主要思路是遍历数组 nums，遍历指针为 i，总长度为 ans
- 在遍历过程中如果出现数字与需要移除的值不相同时，则i自增1，继续下一次遍历
- 如果相同的时候，则将 `nums[i]与nums[ans-1]` 交换，即当前数字和数组最后一个数字进行交换，交换后就少了一个元素，故而 ans 自减 1
- **这种思路在移除元素较少时更适合使用，最极端的情况是没有元素需要移除，遍历一遍结束即可**
- 时间复杂度：*O(n)*，空间复杂度：*O(1)*

### 第二种代码

```java [-Java]
class Solution {
    public int removeElement(int[] nums, int val) {
        int ans = nums.length;
        for (int i = 0; i < ans;) {
            if (nums[i] == val) {
                nums[i] = nums[ans - 1];
                ans--;
            } else {
                i++;
            }
        }
        return ans;
    }
}
```
```javascript [-JavaScript]
/**
 * @param {number[]} nums
 * @param {number} val
 * @return {number}
 */
var removeElement = function(nums, val) {
    let ans = nums.length;
    for (let i = 0; i < ans;) {
        if (nums[i] == val) {
            nums[i] = nums[ans - 1];
            ans--;
        } else {
            i++;
        }
    }
    return ans;
};
```

### 画解

 ![frame_00001.png](https://pic.leetcode-cn.com/b16e114385718de84769e83067dbd96c12c69505f966d3e6877c7173a6721316-frame_00001.png) ![frame_00002.png](https://pic.leetcode-cn.com/040f1cf15626b864b60e918d4b03cbaf88f5c3420eed0fe3e5b125fd1c205071-frame_00002.png) ![frame_00003.png](https://pic.leetcode-cn.com/bc76acc0c875802b90b15474ad6715554089478e9a48b26120dc17e3a6e75631-frame_00003.png) ![frame_00004.png](https://pic.leetcode-cn.com/8963533e489e59a983d9b8e6e1a63f8ad4da3f885c66bbcc7031349bcfe09667-frame_00004.png) ![frame_00005.png](https://pic.leetcode-cn.com/d7eaf322d526fd8df1a418f5260f41d07fadec8f1e3d02d06105fadc90e1baa1-frame_00005.png) ![frame_00006.png](https://pic.leetcode-cn.com/fcaeb6c896a4681f3fb96531830bff930527aac439a104bc0f1032bead595571-frame_00006.png) ![frame_00007.png](https://pic.leetcode-cn.com/abceaffe6538c0e111bd7575663673a208d4d2a2363b86d330b47be1356a61b8-frame_00007.png) ![frame_00008.png](https://pic.leetcode-cn.com/3eb537c33b44c7a67c108b77c092e9b3ef56d0053d7f3114313b6bc23fe8b6e6-frame_00008.png) ![frame_00009.png](https://pic.leetcode-cn.com/d0487933b4d90eded94f3a0c6f063e38170170b76cdcc36b76993bca5ab0cdd8-frame_00009.png) 

想看大鹏画解更多高频面试题，欢迎阅读大鹏的 LeetBook：[《画解剑指 Offer 》](https://leetcode-cn.com/leetbook/detail/illustrate-lcof/)，O(∩_∩)O