package com.algorithm.heap;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author herenpeng
 * @since 2021-03-06 22:44
 */
public class MinHeapTest {

    @Test
    public void test01() {
        MinHeap minHeap = new MinHeap(new Integer[]{4, 12, 7, 3, 9, 1, 0, 45});
        print(minHeap);

        minHeap = new MinHeap(new Integer[]{4, 12, 7, 3, 9, 1, 0, 45});
        minHeap.insert(10);
        print(minHeap);

        minHeap = new MinHeap(new Integer[]{4, 12, 7, 3, 9, 1, 0, 45});
        minHeap.insert(10);
        minHeap.remove();
        print(minHeap);
    }

    /**
     * 打印最小堆
     *
     * @param minHeap 最小堆
     */
    private void print(MinHeap minHeap) {
        Integer temp = minHeap.remove();
        while (temp != null) {
            System.out.print(temp + "\t");
            temp = minHeap.remove();
        }
        System.out.println();
    }

}
