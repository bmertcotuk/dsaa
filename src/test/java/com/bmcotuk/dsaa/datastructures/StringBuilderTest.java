package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringBuilderTest {

    @Test
    void shouldReturnEmptyString() {
        StringBuilder builder = new StringBuilder();
        assertEquals("", builder.toString());
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
}
