package com.my.algorithm.queue;

public class Sample {
    public static void main(String[] args) {
//        removeLastKNodeTest();
    }

    private static void removeLastKNodeTest() {
        int k = 2;
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);

        Node head = removeLastKNode(k, root);
        while (head.next != null) {
            System.out.println(String.format("node: ", head.value));
            head = head.next;
        }
    }

    private static Node removeLastKNode(int k, Node root) {
        Node head = new Node(-1);
        head.next = root;
        Node slow = head;
        Node fast = head;
        for (int i = 0; i <= k; i++) {
            fast = fast.next;
        }
        while (fast.next !=null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head.next;
    }
}
