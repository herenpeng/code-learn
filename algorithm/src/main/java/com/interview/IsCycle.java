package com.interview;

import java.util.HashSet;
import java.util.Set;

/**
 * @author herenpeng
 * @since 2021-04-20 20:03
 */
public class IsCycle {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        System.out.println(isCycle1(node1));
        System.out.println(isCycle2(node1));
        System.out.println(isCycle3(node1));
    }

    private static boolean isCycle1(Node head) {
        Node currentNode = head.next;
        int currentIndex = 1;
        while (currentNode != null) {
            Node node = head;
            int i = 0;
            while (node != null && i < currentIndex) {
                if (node == currentNode) {
                    return true;
                }
                node = node.next;
                i++;
            }
            currentNode = currentNode.next;
            currentIndex++;
        }
        return false;
    }

    private static boolean isCycle2(Node head) {
        Set<Node> set = new HashSet<>();
        while (head != null) {
            if (set.remove(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    private static boolean isCycle3(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

}
