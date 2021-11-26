package com.bmcotuk.dsaa.datastructures;

import java.util.Arrays;

/**
 * @author Mert Cotuk
 */
public class StringBuilder {

    private static final int INITIAL_CAPACITY = 10;

    private char[] array;
    private int size;

    public StringBuilder() {
        array = new char[INITIAL_CAPACITY];
        size = 0;
    }

    public void append(String s) {
        ensureCapacity();
        char[] anotherArray = s.toCharArray();
        for (char c : anotherArray) {
            add(c);
        }
    }

    private void add(char c) {
        ensureCapacity();
        array[size++] = c;
    }

    public String toString() {
        // no better way without copying exists, even Java does the same
        return String.valueOf(Arrays.copyOfRange(array, 0, size));
    }

    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }
    }
}
