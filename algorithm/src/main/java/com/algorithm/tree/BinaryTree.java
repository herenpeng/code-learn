package com.algorithm.tree;

/**
 * @author herenpeng
 * @since 2021-02-27 11:01
 */
public class BinaryTree {

    /**
     * 二叉树根节点指针
     */
    private TreeNode root;

    /**
     * 获取二叉树的根节点
     *
     * @return 二叉树的根节点
     */
    public TreeNode root() {
        return this.root;
    }

    /**
     * 通过数组创建二叉树时数组的索引位置
     */
    private int createBinaryTreeByArrIndex = 0;

    /**
     * 通过数组创建二叉树的构造方法
     *
     * @param arr 数组
     */
    public BinaryTree(Integer[] arr) {
        // 通过数组构建二叉树，并保存二叉树根节点的指针
        this.root = createBinaryTree(arr);
    }

    /**
     * 创建二叉树的方法
     *
     * @param arr 数组
     * @return root根节点指针
     */
    public TreeNode createBinaryTree(Integer[] arr) {
        TreeNode node = null;
        // 如果数组为空，或者索引越界，则直接返回空的二叉树
        if (arr == null || arr.length == 0 || createBinaryTreeByArrIndex < 0
                || createBinaryTreeByArrIndex > arr.length - 1) {
            return null;
        }
        // 通过索引获取数组对应位置的数据
        Integer data = arr[createBinaryTreeByArrIndex++];
        if (data != null) {
            // 如果这个数据不为空，则创建节点，并返回
            node = new TreeNode(data);
            // 数组的下一个节点为该节点的左孩子节点
            node.leftChild = createBinaryTree(arr);
            // 数组的下一个节点为该节点的右孩子节点
            node.rightChile = createBinaryTree(arr);
        }
        return node;
    }

    /**
     * 二叉树节点
     */
    class TreeNode {
        /**
         * 二叉树节点存储节点数据的变量
         */
        private Integer data;
        /**
         * 二叉树节点左孩子节点指针
         */
        private TreeNode leftChild;
        /**
         * 二叉树节点右孩子节点指针
         */
        private TreeNode rightChile;

        /**
         * 获取节点数据的方法
         *
         * @return 节点数据
         */
        public Integer data() {
            return this.data;
        }

        /**
         * 获取节点左孩子节点指针的方法
         *
         * @return 节点左孩子节点指针
         */
        public TreeNode leftChild() {
            return this.leftChild;
        }

        /**
         * 获取节点右孩子节点指针的方法
         *
         * @return 节点右孩子节点指针
         */
        public TreeNode rightChile() {
            return this.rightChile;
        }

        /**
         * 节点构造方法
         *
         * @param data 节点数据
         */
        TreeNode(Integer data) {
            this.data = data;
        }
    }
}
