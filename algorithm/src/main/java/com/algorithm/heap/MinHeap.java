package com.algorithm.heap;

/**
 * @author herenpeng
 * @since 2021-03-06 18:24
 */
public class MinHeap {
    /**
     * 用于存储二叉堆的数组
     */
    private Integer[] array;
    /**
     * 当前二叉堆的最大节点个数，即数组长度
     */
    private int maxSize;

    /**
     * 当前二叉堆的节点个数
     */
    private int size;

    /**
     * 获取数组的实际元素个数
     *
     * @return 数组的实际元素个数
     */
    public int size() {
        return this.size;
    }

    /**
     * 获取数组的长度
     *
     * @return 数组的长度
     */
    public int length() {
        return this.maxSize;
    }

    /**
     * 最小堆的构造函数
     *
     * @param arr 数组
     */
    public MinHeap(Integer[] arr) {
        buildMinHeap(arr);
    }

    /**
     * 构建最小堆
     *
     * @param arr 数组
     */
    private void buildMinHeap(Integer[] arr) {
        this.size = arr.length;
        this.maxSize = arr.length;
        this.array = arr;
        // 获取最后一个节点的下标
        int lastIndex = arr.length - 1;
        // 从最后一个非叶子节点开始进行下沉
        for (int i = getParentIndex(lastIndex); i >= 0; i--) {
            downAdjust(i);
        }
    }

    /**
     * 在堆的末尾插入一个数据
     *
     * @param data 数值
     */
    public void insert(int data) {
        if (size >= maxSize) {
            resize();
        }
        this.array[this.size++] = data;
        upAdjust();
    }

    /**
     * 数组扩容操作
     */
    private void resize() {
        // 新数组的长度为原数组的2倍
        this.maxSize = this.maxSize * 2;
        // 初始化新数组
        Integer[] newArray = new Integer[maxSize];
        // 将原数组的元素复制到新数组中
        System.arraycopy(this.array, 0, newArray, 0, this.size);
        // 使用新数组替换原数组
        this.array = newArray;
    }

    /**
     * 上浮操作
     */
    private void upAdjust() {
        // 数组最后一个节点所在的下标，即需要上浮的节点
        int currentIndex = this.size - 1;
        int parentIndex = getParentIndex(currentIndex);
        // 将当前孩子节点使用临时变量保存
        int temp = this.array[currentIndex];
        // 如果当前孩子节点的下标大于0，并且当前孩子节点的值小于父节点的值
        while (currentIndex > 0 && temp < this.array[parentIndex]) {
            // 将父节点的值和当前孩子节点的值进行交换
            this.array[currentIndex] = this.array[parentIndex];
            // 将当前孩子节点的值修改为之前的父节点的值
            currentIndex = parentIndex;
            // 维护父节点的下标
            parentIndex = getParentIndex(currentIndex);
        }
        this.array[currentIndex] = temp;
    }

    /**
     * 移除堆顶的元素
     *
     * @return 堆顶的元素
     */
    public Integer remove() {
        if (this.size == 0) {
            return null;
        }
        Integer removeData = this.array[0];
        // 获取最后一个节点
        this.array[0] = this.array[size - 1];
        downAdjust(0);
        this.array[--size] = null;
        return removeData;
    }

    /**
     * 下沉操作
     *
     * @param parentIndex 需要下沉的节点下标
     */
    private void downAdjust(int parentIndex) {
        // 获取左孩子节点的下标
        int childIndex = getLeftChildIndex(parentIndex);
        // 将需要下沉的节点使用临时变量保存
        int temp = this.array[parentIndex];
        // 如果左孩子节点的下标还在堆的有效个数之内，
        while (childIndex < this.size) {
            // 右孩子节点为左孩子节点的下一位
            int rightChildIndex = childIndex + 1;
            // 如果有右孩子节点，并右孩子节点比左孩子节点的值小，则定位到右孩子节点
            if (rightChildIndex < this.size && this.array[rightChildIndex] < this.array[childIndex]) {
                childIndex++;
            }
            // 如果需要下沉的节点小于孩子节点中最小的那个节点的值，直接打断程序，不继续下沉
            if (temp <= this.array[childIndex]) {
                break;
            }
            // 调换父子节点的值
            this.array[parentIndex] = this.array[childIndex];
            // 将父节点的下标修改为子节点的下标
            parentIndex = childIndex;
            // 维护左孩子节点的下标
            childIndex = getLeftChildIndex(parentIndex);
        }
        this.array[parentIndex] = temp;
    }

    /**
     * 通过孩子节点下标，获取父节点下标
     *
     * @param childIndex 孩子节点下标
     * @return 父节点下标
     */
    private int getParentIndex(int childIndex) {
        // 通过取余数的方式，直接获取左右孩子节点的父节点
        return (childIndex - 1) / 2;
    }

    /**
     * 通过父节点下标，获取左孩子节点下标
     *
     * @param parentIndex 父节点下标
     * @return 左孩子节点下标
     */
    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /**
     * 通过父节点下标，获取右孩子节点下标
     *
     * @param parentIndex 父节点下标
     * @return 右孩子节点下标
     */
    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

}
