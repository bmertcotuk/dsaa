package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.Node;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void shouldAppendElements() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        list.appendToTail("d");
        list.appendToTail("e");

        assertEquals(new Node<>("a"), list.getHead());
        assertEquals(new Node<>("b"), list.getHead().getNext());
        assertEquals(new Node<>("c"), list.getHead().getNext().getNext());
        assertEquals(new Node<>("d"), list.getHead().getNext().getNext().getNext());
        assertEquals(new Node<>("e"), list.getHead().getNext().getNext().getNext().getNext());
        assertEquals(5, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void shouldRemoveElementFromHead() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        list.remove("a");

        assertEquals(new Node<>("b"), list.getHead());
        assertEquals(new Node<>("c"), list.getHead().getNext());
        assertNull(list.getHead().getNext().getNext());
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void shouldRemoveElementBetweenHeadAndTail() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        list.remove("b");

        assertEquals(new Node<>("a"), list.getHead());
        assertEquals(new Node<>("c"), list.getHead().getNext());
        assertNull(list.getHead().getNext().getNext());
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void shouldRemoveElementFromTail() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        list.remove("c");

        assertEquals(new Node<>("a"), list.getHead());
        assertEquals(new Node<>("b"), list.getHead().getNext());
        assertNull(list.getHead().getNext().getNext());
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void shouldReturnSizeForNonEmpty() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        assertEquals(3, list.size());
    }

    @Test
    void shouldReturnTrueForEmpty() {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    void shouldReturnFalseForNonEmpty() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        assertFalse(list.isEmpty());
    }

    @Test
    void shouldThrowExceptionOnElementNotFound() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        assertThrows(NoSuchElementException.class, () -> list.remove("x"));
        assertEquals(new Node<>("a"), list.getHead());
        assertEquals(new Node<>("b"), list.getHead().getNext());
        assertEquals(new Node<>("c"), list.getHead().getNext().getNext());
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void shouldThrowExceptionOnNullArgumentForAppendOperation() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThrows(IllegalArgumentException.class, () -> list.appendToTail(null));
    }

    @Test
    void shouldThrowExceptionOnNullArgumentForRemoveOperation() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThrows(IllegalArgumentException.class, () -> list.remove(null));
    }
}
