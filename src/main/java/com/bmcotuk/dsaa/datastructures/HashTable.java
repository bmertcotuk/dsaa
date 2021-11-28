package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.KeyValuePair;
import com.bmcotuk.dsaa.common.Node;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Mert Cotuk
 */
public class HashTable<K, V> {

    private static final int ARRAY_SIZE = 5;

    private LinkedList<KeyValuePair<K, V>>[] arrayOfLinkedLists;
    private int size;

    public HashTable() {
        arrayOfLinkedLists = new LinkedList[ARRAY_SIZE];
        size = 0;
    }

    public V get(K key) {
        validateKey(key);
        int index = getIndex(key);
        ensureNotNullAtIndex(index);
        Node<KeyValuePair<K, V>> currentNode = arrayOfLinkedLists[index].getHead();
        while (currentNode != null) {
            if (currentNode.getData().getKey().equals(key)) {
                return currentNode.getData().getValue();
            }
            currentNode = currentNode.getNext();
        }
        throw new NoSuchElementException();
    }

    public void put(K key, V value) {
        validateKey(key);
        validateValue(value);
        int index = getIndex(key);
        ensureNotNullAtIndex(index);
        arrayOfLinkedLists[index].appendToTail(new KeyValuePair<>(key, value));
        size++;
    }

    public void remove(K key) {
        validateKey(key);
        V value = get(key);
        int index = getIndex(key);
        // guaranteed to be not null already from the get() call
        arrayOfLinkedLists[index].remove(new KeyValuePair<>(key, value));
        size--;
    }

    public boolean containsKey(K key) {
        validateKey(key);
        int index = getIndex(key);
        ensureNotNullAtIndex(index);
        Node<KeyValuePair<K, V>> currentNode = arrayOfLinkedLists[index].getHead();
        while (currentNode != null) {
            if (currentNode.getData().getKey().equals(key)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureNotNullAtIndex(int index) {
        if (arrayOfLinkedLists[index] == null) {
            arrayOfLinkedLists[index] = new LinkedList<>();
        }
    }

    private int getIndex(K key) {
        // Math.floorMod() is the safest option to avoid negatives
        return Math.floorMod(Objects.hashCode(key), ARRAY_SIZE);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private void validateValue(V value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }
    }
}
