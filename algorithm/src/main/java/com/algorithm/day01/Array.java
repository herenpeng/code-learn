package com.algorithm.day01;

/**
 * @author herenpeng
 * @since 2021-02-22 22:24
 */
public class Array {
    /**
     * 数组
     */
    private int[] array;
    /**
     * 数组的最大元素个数，即为数组的长度
     */
    private int maxSize;
    /**
     * 数组的实际元素个数
     */
    private int size;

    /**
     * 构造函数
     *
     * @param capacity 初始化数组的容量大小，即maxSize大小
     */
    public Array(int capacity) {
        // 初始化数组长度
        this.maxSize = capacity;
        // 初始化数组，开辟数组空间
        this.array = new int[maxSize];
        // 初始化数组实际元素个数，为0
        this.size = 0;
    }

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
     * 直接在数组尾部插入元素
     *
     * @param element 需要插入的元素
     */
    public void add(int element) throws Exception {
        add(this.size, element);
    }

    /**
     * 在数组指定位置插入元素
     *
     * @param index   数组指定的下标位置
     * @param element 需要插入的元素
     */
    public void add(int index, int element) throws Exception {
        if (index < 0 || index > maxSize - 1) {
            throw new Exception("数组越界");
        }
    }


}
