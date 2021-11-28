package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.Node;

import java.util.EmptyStackException;

/**
 * @author Mert Cotuk
 */
public class Stack<T> {

    private Node<T> top;
    private int size;

    public Stack() {
        size = 0;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    public T peek() {
        validateNotEmpty();
        return top.getData();
    }

    public T pop() {
        T data = peek();
        top = top.getNext();
        size--;
        return data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void validateNotEmpty() {
        if (top == null) {
            throw new EmptyStackException();
        }
    }
}
