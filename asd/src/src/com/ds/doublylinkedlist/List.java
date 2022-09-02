package com.ds.doublylinkedlist;

public class List<E> {
    ListNode<E> head = null, tail = null;
    int size;

    public void add(E data) {
        ListNode<E> newNode = new ListNode<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        ++size;
    }

    public int size() {
        return size;
    }

    public E front() {
        return this.head.getData();
    }

    public ListNode<E> getLastNode() {
        return this.tail;
    }

    public void remove(E data) {
        ListNode<E> nodeIter = head;
        while (nodeIter != null && nodeIter.getData() != data) {
            nodeIter = nodeIter.getNext();
        }
        this.remove(nodeIter);
    }

    public void remove (ListNode<E> node) {
        if (node != null) {
            if (node == head) {
                head = node.getNext();
                if (head != null) {
                    head.setPrev(null);
                } else {
                    tail = null;
                }
            } else if (node == tail) {
                tail = node.getPrev();
                tail.setNext(node.getNext());
            } else {
                //node.prev.next = node.next;
                node.getPrev().setNext(node.getNext());
                //node.next.prev = node.prev;
                node.getNext().setPrev(node.getPrev());
            }
            --size;
        }
    }

    public void printList() {
        ListNode<E> n = head;
        while (n != null) {
            System.out.printf("%d --> ", n.getData());
            n = n.getNext();
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        List<Integer> list = new List<>();
        int [] a = {1, 2, 3, 4, 5};
        for (int i : a) {
            list.add(i);
        }
        System.out.println("List items are : ");
        list.printList();
        System.out.println("Removing the item 3");
        list.remove(3);
        list.printList();
        System.out.println("Removing the last item");
        list.remove(list.getLastNode());
        list.printList();
    }
}
