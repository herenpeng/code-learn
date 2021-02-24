package com.algorithm.linked;

/**
 * @author herenpeng
 * @since 2021-02-24 21:59
 */
public class DoubleLinkedList {

    /**
     * 链表的头指针
     */
    private Node head;

    /**
     * 链表的尾部指针
     */
    private Node last;

    /**
     * 链表的实际长度
     */
    private int size;

    /**
     * 获取链表的头指针
     *
     * @return 链表的头指针
     */
    public Node head() {
        return this.head;
    }

    /**
     * 获取链表的尾部指针
     *
     * @return 链表的尾部指针
     */
    public Node last() {
        return this.last;
    }

    /**
     * 获取链表的实际长度
     *
     * @return 链表的实际长度
     */
    public int size() {
        return this.size;
    }

    /**
     * 链表的查找操作
     *
     * @param index 需要查找的链表索引
     * @return 索引对应的链表节点
     */
    public Node get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出链表的实际节点范围");
        }
        // 找到链表的头节点的位置
        Node temp = this.head;
        // 从头节点向后查找，共向后循环index-1次，即可找到index位置的节点
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 更新链表节点
     *
     * @param index 需要更新的链表位置
     * @param data  需要更新的节点的值
     */
    public void update(int index, int data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出链表的实际节点范围");
        }
        // 找到链表的头节点的位置
        Node temp = this.head;
        // 从头节点向后查找，共向后循环index-1次，即可找到index位置的节点
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        // 使用需要替换的值，替换原值
        temp.data = data;
    }

    /**
     * 直接在尾部插入
     *
     * @param data 需要插入的数据
     */
    public void insert(int data) {
        insert(size, data);
    }

    /**
     * 直接在尾部插入
     *
     * @param index 需要插入的链表位置
     * @param data  需要插入的数据
     */
    public void insert(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表的实际节点范围");
        }
        // 将需要插入的数据封装为一个链表节点
        Node insertNode = new Node(data);
        // 链表节点为空的情况
        if (size == 0) {
            // 维护链表头部节点指针
            this.head = insertNode;
            // 维护链表尾部节点指针
            this.last = insertNode;
        } else if (index == 0) {
            // 头部插入
            Node headNode = this.head;
            // 将原头节点的上一个节点指针指向新节点
            headNode.prev = insertNode;
            // 使用新节点的next指针指向头部节点
            insertNode.next = headNode;
            // 维护链表头部节点的指针
            this.head = insertNode;
        } else if (index == size) {
            // 尾部插入
            // 直接使用尾部节点指针指向新结点
            this.last.next = insertNode;
            // 新节点的上一个节点指向原尾部节点
            insertNode.prev = this.last;
            // 维护链表尾部节点的指针
            this.last = insertNode;
        } else {
            // 获取插入位置的前一个节点
            Node prevNode = get(index - 1);
            // 插入位置的节点
            Node nextNode = prevNode.next;
            nextNode.prev = insertNode;
            // 将新节点的next指针指向插入位置节点,prev指针指向前一个节点
            insertNode.next = nextNode;
            insertNode.prev = prevNode;
            // 插入位置的前一个节点的next指针指向新节点
            prevNode.next = insertNode;
        }
        // 维护链表长度
        size++;
    }

    /**
     * 删除链表的结点
     *
     * @param index 需要删除的位置
     * @return 被删除的节点
     */
    public Node remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出链表的实际节点范围");
        }
        Node removeNode = null;
        // 头部删除
        if (index == 0) {
            removeNode = this.head;
            Node nextHead = removeNode.next;
            // 置空新的头部节点的pre指针
            nextHead.prev = null;
            // 维护链表的头部指针
            this.head = nextHead;
        } else if (index == size - 1) {
            // 尾部删除
            // 获取尾部的前一个节点
            Node prevNode = get(index - 1);
            removeNode = prevNode.next;
            // 置空尾部的前一个节点的next指针
            prevNode.next = null;
            // 维护链表的尾部指针
            this.last = prevNode;
        } else {
            // 获取插入位置的前一个节点
            Node prevNode = get(index - 1);
            // 需要删除的节点
            removeNode = prevNode.next;
            // 删除节点的下一个节点
            Node nextNode = removeNode.next;
            // 将删除位置的上一个节点的next指针指向删除位置的下一个节点
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        // 维护链表长度
        size--;
        return removeNode;
    }


    /**
     * 将节点点作为链表的一个内部类
     */
    class Node {
        // 节点存储的数据data
        private int data;
        // 该指针指向上一个
        private Node prev;
        // 该指针指向下一个节点的指针next
        private Node next;

        // 构造函数
        Node(int data) {
            this.data = data;
        }

        /**
         * 返回节点中存储的数据值
         *
         * @return 节点中存储的数据值
         */
        public int data() {
            return this.data;
        }

        /**
         * 返回上一个节点的指针
         *
         * @return 上一个节点的指针
         */
        public Node prev() {
            return this.prev;
        }

        /**
         * 返回下一个节点的指针
         *
         * @return 下一个节点的指针
         */
        public Node next() {
            return this.next;
        }
    }
}
