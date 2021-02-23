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
     * 获取下标对应的元素
     *
     * @param index 数组下标
     * @return 下标对应的元素
     */
    public int get(int index) {
        // 判断需要删除的数组下标是否在数组实际元素范围之内
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("删除元素的位置超出数组的实际元素范围");
        }
        return this.array[index];
    }

    /**
     * 更新下标对应的元素
     *
     * @param index   数组下标
     * @param element 需要更新的元素
     */
    public void update(int index, int element) {
        // 判断需要删除的数组下标是否在数组实际元素范围之内
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("删除元素的位置超出数组的实际元素范围");
        }
        this.array[index] = element;
    }

    /**
     * 直接在数组尾部插入元素
     *
     * @param element 需要插入的元素
     */
    public void add(int element) {
        add(this.size, element);
    }

    /**
     * 在数组指定位置插入元素
     *
     * @param index   数组指定的下标位置
     * @param element 需要插入的元素
     */
    public void add(int index, int element) {
        // 这样操作的目的是为了保证数组的元素全部都可以从0开始，并且中间不会出现空闲元素
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("插入元素的位置超出数组的实际元素范围");
        }
        // 如果当前数组实际元素个数大于等于数组长度，进行扩容操作
        if (size >= maxSize) {
            resize();
        }
        // 从下标index开始将元素向后移动一位
        for (int i = size; i > index; i--) {
            this.array[i] = this.array[i - 1];
        }
        // 在index位置插入需要插入的元素
        this.array[index] = element;
        // 数组实际元素个数+1
        size++;
    }

    /**
     * 扩容操作，每次扩容，新数组的长度为原数组的2倍
     */
    private void resize() {
        // 新数组的长度为原数组的2倍
        this.maxSize = this.maxSize * 2;
        // 初始化新数组
        int[] newArray = new int[maxSize];
        // 将原数组的元素复制到新数组中
        System.arraycopy(this.array, 0, newArray, 0, this.size);
        // 使用新数组替换原数组
        this.array = newArray;
    }

    /**
     * 删除数组元素
     *
     * @param index 需要删除的数组元素下标位置
     * @return 删除的元素
     */
    public int delete(int index) {
        // 判断需要删除的数组下标是否在数组实际元素范围之内
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("删除元素的位置超出数组的实际元素范围");
        }
        int deleteElement = this.array[index];
        for (int i = index; i < size - 1; i++) {
            this.array[index] = this.array[index + 1];
        }
        size--;
        return deleteElement;
    }
}
