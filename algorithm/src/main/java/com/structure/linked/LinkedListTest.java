package com.structure.linked;

import org.junit.Test;

/**
 * @author herenpeng
 * @since 2021-02-24 21:06
 */
public class LinkedListTest {

    @Test
    public void test01() {
        LinkedList linkedList = new LinkedList();
        linkedList.insert(3);
        linkedList.insert(4);
        linkedList.insert(5);
        linkedList.insert(2, 10);
        linkedList.insert(2, 11);
        print(linkedList);
        linkedList.update(4, 50);
        print(linkedList);
        linkedList.remove(4);
        print(linkedList);
        linkedList.remove(2);
        print(linkedList);
    }

    public void print(LinkedList linkedList) {
        LinkedList.Node temp = linkedList.head();
        while (temp != null) {
            System.out.print(temp.data() + "\t");
            temp = temp.next();
        }
        System.out.println();
    }

}
