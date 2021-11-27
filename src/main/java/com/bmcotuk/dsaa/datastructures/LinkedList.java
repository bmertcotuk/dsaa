package com.bmcotuk.dsaa.datastructures;

import java.util.NoSuchElementException;

/**
 * @author Mert Cotuk
 */
public class LinkedList<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        size = 0;
    }

    public Node<T> getHead() {
        return head;
    }

    public void appendToTail(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> currentNode = head;
            // we are interested with the next node only
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
        size++;
    }

    // using currentNode object with reference affects the whole list, no need to return the head
    // data is still what defines an object and is checked in `equals()`
    public void remove(T data) {
        Node<T> nodeToBeRemoved = new Node<>(data);
        Node<T> currentNode = head;

        // head is a special case so deserves to be outside of the loop
        if (currentNode.equals(nodeToBeRemoved)) {
            head = head.getNext();
            size--;
            return;
        }
        while (currentNode.getNext() != null) {
            if (currentNode.getNext().equals(nodeToBeRemoved)) {
                // no problem even if it is the tail
                currentNode.setNext(currentNode.getNext().getNext());
                size--;
                return;
            }
            currentNode = currentNode.getNext();
        }
        throw new NoSuchElementException("Element to be removed is not found.");
    }

    public boolean isEmpty() {
        // head == null, size == 0
        return head == null;
    }

    public int size() {
        return size;
    }
}
