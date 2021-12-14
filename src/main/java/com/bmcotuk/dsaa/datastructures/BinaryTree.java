package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.BinaryTreeNode;

/**
 * @author Mert Cotuk
 */
public class BinaryTree {

    private BinaryTreeNode root;
    private int size;

    public BinaryTree() {
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(int data) {
        root = new BinaryTreeNode(data);
    }

    public void addMultiple(int... multiple) {
        for (int data : multiple) {
            add(data);
        }
    }

    public void add(int data) {
        root = addRecursion(root, data);
    }

    // assigning and returning solves "pass by value" issues
    private BinaryTreeNode addRecursion(BinaryTreeNode node, int data) {
        if (node == null) {
            node = new BinaryTreeNode(data);
            size++;
        } else {
            if (data <= node.getData()) {
                node.setLeft(addRecursion(node.getLeft(), data));
            } else {
                node.setRight(addRecursion(node.getRight(), data));
            }
        }
        return node;
    }

    public boolean contains(int data) {
        return containsRecursion(root, data);
    }

    // always have a clear "else" block to avoid "missing return statement"
    private boolean containsRecursion(BinaryTreeNode node, int data) {
        if (node == null) {
            return false;
        } else {
            if (data < node.getData()) {
                return containsRecursion(node.getLeft(), data);
            } else if (data > node.getData()) {
                return containsRecursion(node.getRight(), data);
            } else {
                return true;
            }
        }
    }

    // we only use deepest node in order to maintain the balance of a binary tree
    public void remove(int data) {
        root = removeRecursion(root, data);
    }

    private BinaryTreeNode removeRecursion(BinaryTreeNode node, int data) {
        if (node == null) {
            return null;
        }

        // recursion will work like find, it will only touch the affected sub-tree and remove the value
        if (data < node.getData()) {
            node.setLeft(removeRecursion(node.getLeft(), data));
            return node;
        } else if (data > node.getData()) {
            node.setRight(removeRecursion(node.getRight(), data));
            return node;
        } else {
            // size should be decremented when nulling or setting, two children case is just a step for recursion
            // has no child
            if (node.getLeft() == null && node.getRight() == null) {
                size--;
                return null;
            }
            // has one child
            if (node.getLeft() == null) {
                size--;
                return node.getRight();
            }
            if (node.getRight() == null) {
                size--;
                return node.getLeft();
            }
            // has two children
            // smallest of the right sub-tree will replace the deleted node to keep the tree balanced
            int replacementValue = findSmallestValue(node.getRight());
            node.setData(replacementValue);
            // we used the value, now we can delete it, last argument (data to be deleted) has changed!
            node.setRight(removeRecursion(node.getRight(), replacementValue));
            return node;
        }
    }

    private int findSmallestValue(BinaryTreeNode node) {
        return node.getLeft() == null ? node.getData() : findSmallestValue(node.getLeft());
    }

    public void traversePreOrderDFS(BinaryTreeNode node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            traversePreOrderDFS(node.getLeft());
            traversePreOrderDFS(node.getRight());
        }
    }

    public void traverseInOrderDFS(BinaryTreeNode node) {
        if (node != null) {
            traverseInOrderDFS(node.getLeft());
            System.out.print(node.getData() + " ");
            traverseInOrderDFS(node.getRight());
        }
    }

    public void traversePostOrderDFS(BinaryTreeNode node) {
        if (node != null) {
            traversePostOrderDFS(node.getLeft());
            traversePostOrderDFS(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }

    public void traverseLevelOrderBFS(BinaryTreeNode node) {
        Queue<BinaryTreeNode> queue = new Queue<>();
        if (node != null) {
            queue.enqueue(node);
        }
        while (!queue.isEmpty()) {
            BinaryTreeNode currentNode = queue.dequeue();
            if (currentNode != null) {
                System.out.print(currentNode.getData() + " ");
                if (currentNode.getLeft() != null) {
                    queue.enqueue(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    queue.enqueue(currentNode.getRight());
                }
            }
        }
    }

    public int getDepth() {
        return Math.max(getLevel() - 1, 0);
    }

    public int getLevel() {
        return maxLevelFromNode(root);
    }

    private int maxLevelFromNode(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxLevelFromNode(node.getLeft()), maxLevelFromNode(node.getRight())) + 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // BFS is not an option here, null nodes can be missed if tree is not balanced
    // recursion starting from the root is the only option
    // based on the solution here: https://stackoverflow.com/a/4973083/9300818
    @Override
    public String toString() {
        int maxLevel = maxLevelFromNode(root);
        ArrayList<BinaryTreeNode> nodes = new ArrayList<>();
        nodes.add(root);

        StringBuilder stringBuilder = new StringBuilder();
        toStringRecursion(nodes, 1, maxLevel, stringBuilder);
        return stringBuilder.toString();
    }

    private void toStringRecursion(ArrayList<BinaryTreeNode> nodes, int currentLevel, int maxLevel,
                                   StringBuilder stringBuilder) {

        if (nodes.isEmpty() || hasOnlyNullElements(nodes)) {
            return;
        }

        int currentDepth = maxLevel - currentLevel;
        int edgeLines = (int) Math.pow(2, (Math.max(currentDepth - 1, 0)));
        int leftmostSpaces = (int) Math.pow(2, currentDepth) - 1;
        int betweenSpaces = (int) Math.pow(2, currentDepth + 1) - 1;

        stringBuilder.appendRepeated(" ", leftmostSpaces);
        ArrayList<BinaryTreeNode> newNodes = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            BinaryTreeNode currentNode = (BinaryTreeNode) nodes.get(i);
            if (currentNode != null) {
                stringBuilder.append(String.valueOf(currentNode.getData()));
                newNodes.addWithoutNullCheck(currentNode.getLeft());
                newNodes.addWithoutNullCheck(currentNode.getRight());
            } else {
                // we need these, they'll be checked on the next iteration at this condition
                stringBuilder.append(" ");
                newNodes.addWithoutNullCheck(null);
                newNodes.addWithoutNullCheck(null);
            }
            stringBuilder.appendRepeated(" ", betweenSpaces);
        }
        stringBuilder.append("\n");
        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                stringBuilder.appendRepeated(" ", leftmostSpaces - i);

                BinaryTreeNode currentNode = (BinaryTreeNode) nodes.get(j);
                if (currentNode == null) {
                    stringBuilder.appendRepeated(" ", 2 * edgeLines + i + 1);
                    continue;
                }

                if (currentNode.getLeft() != null) {
                    stringBuilder.append("/");
                } else {
                    stringBuilder.append(" ");
                }
                stringBuilder.appendRepeated(" ", 2 * i - 1);

                if (currentNode.getRight() != null) {
                    stringBuilder.append("\\");
                } else {
                    stringBuilder.append(" ");
                }
                stringBuilder.appendRepeated(" ", 2 * edgeLines - i);
            }
            stringBuilder.append("\n");
        }
        toStringRecursion(newNodes, currentLevel + 1, maxLevel, stringBuilder);
    }

    private boolean hasOnlyNullElements(ArrayList<BinaryTreeNode> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) != null) {
                return false;
            }
        }
        return true;
    }
}
