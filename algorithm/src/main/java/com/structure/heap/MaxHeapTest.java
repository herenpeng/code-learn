package com.structure.heap;

import org.junit.Test;

/**
 * @author herenpeng
 * @since 2021-03-06 22:44
 */
public class MaxHeapTest {

    @Test
    public void test01() {
        MaxHeap maxHeap = new MaxHeap(new Integer[]{4, 12, 7, 3, 9, 1, 0, 45});
        print(maxHeap);

        maxHeap = new MaxHeap(new Integer[]{4, 12, 7, 3, 9, 1, 0, 45});
        maxHeap.insert(10);
        print(maxHeap);

        maxHeap = new MaxHeap(new Integer[]{4, 12, 7, 3, 9, 1, 0, 45});
        maxHeap.insert(10);
        maxHeap.remove();
        print(maxHeap);
    }

    /**
     * 打印最小堆
     *
     * @param maxHeap 最大堆
     */
    private void print(MaxHeap maxHeap) {
        Integer temp = maxHeap.remove();
        while (temp != null) {
            System.out.print(temp + "\t");
            temp = maxHeap.remove();
        }

        System.out.println();
    }

}
