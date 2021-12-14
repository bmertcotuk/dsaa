package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringBuilderTest {

    @Test
    void shouldReturnEmptyString() {
        StringBuilder builder = new StringBuilder();
        assertEquals("", builder.toString());
    }

    @Test
    void shouldAppendRepeated() {
        StringBuilder builder = new StringBuilder();
        builder.appendRepeated("-", 3);
        builder.appendRepeated("a", 4);
        builder.appendRepeated(" ", 2);
        assertEquals("---aaaa  ", builder.toString());
    }

    @Test
    void shouldReturnBuiltString() {
        StringBuilder builder = new StringBuilder();
        builder.append("abc");
        builder.append("def");
        builder.append("ghi");
        assertEquals("abcdefghi", builder.toString());
    }

    @Test
    void shouldReturnBuiltStringLargerThanInitialCapacity() {
        StringBuilder builder = new StringBuilder();
        builder.append("abc");
        builder.append("def");
        builder.append("ghi");
        builder.append("jkl");
        assertEquals("abcdefghijkl", builder.toString());
    }

    @Test
    void shouldThrowExceptionOnNullArgumentForAppendOperation() {
        StringBuilder builder = new StringBuilder();
        assertThrows(IllegalArgumentException.class, () -> builder.append(null));
    }
}
