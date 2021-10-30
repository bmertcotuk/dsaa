package com.bmcotuk.dsaa.datastructures;

/**
 * @author Mert Cotuk
 */
public class Pair<T, K> {

    private T first;
    private K second;

    public Pair() {
    }

    public Pair(T first, K second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(K second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
