package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void shouldReturnSizeForEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        assertEquals(0, stack.size());
    }

    @Test
    void shouldReturnSizeForNonEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        assertEquals(3, stack.size());
    }

    @Test
    void shouldReturnTrueForEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        assertTrue(stack.isEmpty());
    }

    @Test
    void shouldReturnFalseForNonEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        assertFalse(stack.isEmpty());
    }

    @Test
    void shouldPushElements() {
        Stack<Integer> stack = createNonEmptyStack();
        assertEquals(4, stack.size());
    }

    @Test
    void shouldPopElements() {
        Stack<Integer> stack = createNonEmptyStack();
        assertEquals(8, stack.pop());
        assertEquals(6, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());

        assertEquals(2, stack.pop());
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    void shouldPeekElement() {
        Stack<Integer> stack = createNonEmptyStack();
        assertEquals(8, stack.peek());
    }

    @Test
    void shouldThrowExceptionOnEmptyStackForPopOperation() {
        Stack<Integer> stack = new Stack<>();
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    void shouldThrowExceptionOnEmptyStackForPeekOperation() {
        Stack<Integer> stack = new Stack<>();
        assertThrows(EmptyStackException.class, () -> stack.peek());
    }

    private Stack<Integer> createNonEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        stack.push(8);
        return stack;
    }
}
