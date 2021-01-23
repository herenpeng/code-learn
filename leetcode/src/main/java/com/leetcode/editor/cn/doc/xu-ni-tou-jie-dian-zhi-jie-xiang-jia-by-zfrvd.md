### 解题思路
下边注释写的很清楚了！
重点：1.使用虚拟头结点dummyHead，返回dummyHead.next即可
     2.注意中间进位的处理
     3.链表到尾部，值使用0代替

### 代码

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);//虚拟头部
        ListNode p = dummyHead;
        ListNode p1 = l1, p2 = l2;
        int carry = 0;//进位
        int sum = 0;//两个节点相加的值
        while (p1 != null || p2 != null || carry > 0) {//l1,l2任一链表未到尾部，或者进位不为0都要继续
            //如果一个列表已经到尾部，则值用0代替
            sum = (p1 == null ? 0 : p1.val) + (p2 == null ? 0 : p2.val) + carry;
            //判断sum是否有进位
            carry = sum / 10;
            //创建新节点，并将头结点指向该节点
            p.next = new ListNode(sum % 10);
            //移动两个链表
            p1 = p1 == null ? null : p1.next;
            p2 = p2 == null ? null : p2.next;
            //移动头节点
            p = p.next;
        }
        return dummyHead.next;
    }
}
```