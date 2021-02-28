package com.algorithm.tree;

import org.junit.Test;

import java.util.*;

/**
 * @author herenpeng
 * @since 2021-02-28 12:26
 */
public class BinaryTreeTest {

    @Test
    public void test01() {
        Integer[] arr = new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4};
        // 构建二叉树
        BinaryTree binaryTree = new BinaryTree(arr);
        // 获取二叉树的根节点
        BinaryTree.TreeNode root = binaryTree.root();
        System.out.println("前序遍历：");
        // 前序遍历
        preOrderTraveral(root);
        System.out.println();
        preOrderTraveralWithStack(root);
    }

    /**
     * 前序遍历
     *
     * @param node 节点
     */
    public void preOrderTraveral(BinaryTree.TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data() + "\t");
        preOrderTraveral(node.leftChild());
        preOrderTraveral(node.rightChile());
    }

    /**
     * 前序遍历，非递归
     *
     * @param root 二叉树根节点
     */
    public void preOrderTraveralWithStack(BinaryTree.TreeNode root) {
        Stack<BinaryTree.TreeNode> stack = new Stack<>();
        BinaryTree.TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                System.out.print(treeNode.data() + "\t");
                stack.push(treeNode);
                treeNode = treeNode.leftChild();
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChile();
            }
        }
    }


    @Test
    public void test02() {
        Integer[] arr = new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4};
        // 构建二叉树
        BinaryTree binaryTree = new BinaryTree(arr);
        // 获取二叉树的根节点
        BinaryTree.TreeNode root = binaryTree.root();
        System.out.println("中序遍历：");
        // 中序遍历
        inOrderTraveral(root);
        System.out.println();
        inOrderTraveralWithStack(root);
    }

    /**
     * 中序遍历
     *
     * @param node 节点
     */
    public void inOrderTraveral(BinaryTree.TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTraveral(node.leftChild());
        System.out.print(node.data() + "\t");
        inOrderTraveral(node.rightChile());
    }

    /**
     * 中序遍历，非递归
     *
     * @param root 二叉树根节点
     */
    public void inOrderTraveralWithStack(BinaryTree.TreeNode root) {
        Stack<BinaryTree.TreeNode> stack = new Stack<>();
        BinaryTree.TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.leftChild();
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                System.out.print(treeNode.data() + "\t");
                treeNode = treeNode.rightChile();
            }
        }
    }


    @Test
    public void test03() {
        Integer[] arr = new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4};
        // 构建二叉树
        BinaryTree binaryTree = new BinaryTree(arr);
        // 获取二叉树的根节点
        BinaryTree.TreeNode root = binaryTree.root();
        System.out.println("后序遍历：");
        // 后序遍历
        postOrderTraveral(root);
        System.out.println();
        postOrderTraveralWithStack(root);
    }


    /**
     * 后序遍历
     *
     * @param node 节点
     */
    public void postOrderTraveral(BinaryTree.TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderTraveral(node.leftChild());
        postOrderTraveral(node.rightChile());
        System.out.print(node.data() + "\t");
    }

    /**
     * 后序遍历，非递归
     *
     * @param root 二叉树根节点
     */
    public void postOrderTraveralWithStack(BinaryTree.TreeNode root) {
        Stack<BinaryTree.TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        BinaryTree.TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                list.add(treeNode.data());
                stack.push(treeNode);
                treeNode = treeNode.rightChile();
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.leftChild();
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + "\t");
        }
    }


    @Test
    public void test04() {
        Integer[] arr = new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4};
        // 构建二叉树
        BinaryTree binaryTree = new BinaryTree(arr);
        // 获取二叉树的根节点
        BinaryTree.TreeNode root = binaryTree.root();
        System.out.println("层序遍历：");
        // 层序遍历
        levelOrderTraveralWithQueue(root);
    }

    /**
     * 层序遍历
     *
     * @param node 二叉树节点
     */
    public void levelOrderTraveral(BinaryTree.TreeNode node) {
    }

    /**
     * 层序遍历
     *
     * @param root 二叉树根节点
     */
    public void levelOrderTraveralWithQueue(BinaryTree.TreeNode root) {
        Queue<BinaryTree.TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTree.TreeNode node = queue.poll();
            System.out.print(node.data() + "\t");
            BinaryTree.TreeNode leftChild = node.leftChild();
            if (leftChild != null) {
                queue.offer(leftChild);
            }
            BinaryTree.TreeNode rightChile = node.rightChile();
            if (rightChile != null) {
                queue.offer(rightChile);
            }
        }
    }


}
