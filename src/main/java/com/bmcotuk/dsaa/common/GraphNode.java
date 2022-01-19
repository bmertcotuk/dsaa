package com.bmcotuk.dsaa.common;

/**
 * @author Mert Cotuk
 */
public class GraphNode<T> {

    private T data;
    private GraphNode<T>[] children;

    public GraphNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public GraphNode<T>[] getChildren() {
        return children;
    }

    public void setChildren(GraphNode<T>[] children) {
        this.children = children;
    }
}
