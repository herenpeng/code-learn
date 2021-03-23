package com.structure.linked;

import org.junit.Test;

/**
 * @author herenpeng
 * @since 2021-02-24 21:59
 */
public class DoubleLinkedListTest {

    @Test
    public void test01() {
        DoubleLinkedList list = new DoubleLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(1, 23);
        print(list);
        list.update(3, 33);
        print(list);
        list.insert(4, 20);
        print(list);
        list.remove(2);
        print(list);
        list.remove(0);
        print(list);
    }


    public void print(DoubleLinkedList doubleLinkedList) {
        DoubleLinkedList.Node temp = doubleLinkedList.head();
        while (temp != null) {
            System.out.print(temp.data() + "\t");
            temp = temp.next();
        }
        System.out.println();
    }

}
