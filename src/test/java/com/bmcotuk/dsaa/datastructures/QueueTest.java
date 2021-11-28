package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void shouldReturnSizeForEmpty() {
        Queue<Integer> queue = new Queue<>();
        assertEquals(0, queue.size());
    }

    @Test
    void shouldReturnSizeForNonEmpty() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(3);
        queue.enqueue(5);
        assertEquals(2, queue.size());
    }

    @Test
    void shouldReturnTrueForEmpty() {
        Queue<Integer> queue = new Queue<>();
        assertTrue(queue.isEmpty());
    }

    @Test
    void shouldReturnFalseForNonEmpty() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(3);
        assertFalse(queue.isEmpty());
    }

    @Test
    void shouldEnqueueAndDequeue() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(3);
        queue.enqueue(5);
        queue.enqueue(7);
        queue.enqueue(9);
        queue.enqueue(11);
        assertEquals(5, queue.size());

        assertEquals(3, queue.dequeue());
        assertEquals(5, queue.dequeue());
        assertEquals(3, queue.size());

        assertEquals(7, queue.dequeue());
        assertEquals(9, queue.dequeue());
        assertEquals(11, queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    void shouldPeek() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(3);
        queue.enqueue(5);
        queue.enqueue(7);
        assertEquals(3, queue.peek());
    }

    @Test
    void shouldThrowExceptionOnNullArgumentForEnqueueOperation() {
        Queue<Integer> queue = new Queue<>();
        assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
    }

    @Test
    void shouldThrowExceptionOnEmptyQueueForDequeueOperation() {
        Queue<Integer> queue = new Queue<>();
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    void shouldThrowExceptionOnEmptyQueueForPeekOperation() {
        Queue<Integer> queue = new Queue<>();
        assertThrows(NoSuchElementException.class, () -> queue.peek());
    }
}
