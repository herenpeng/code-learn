//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 5533 👎 0

package com.leetcode.editor.cn;

/**
 * @author herenpeng
 * @since 2021-01-23 21:32:35
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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

        // 在提交的时候应该把这段代码注释掉
        public class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}