package com.bmcotuk.dsaa.datastructures;

import java.util.Arrays;

/**
 * @author Mert Cotuk
 */
public class ArrayList<T> {

    private static final int INITIAL_CAPACITY = 10;

    private Object[] array;
    private int size;

    public ArrayList() {
        // values initialized in constructor for space efficiency
        array = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        ensureCapacity();
        array[size++] = data;
    }

    public Object get(int index) {
        checkIndex(index);
        return array[index];
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1]; // capacity is ensured, size cannot be greater than or equal to array's length
        }
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }
}
