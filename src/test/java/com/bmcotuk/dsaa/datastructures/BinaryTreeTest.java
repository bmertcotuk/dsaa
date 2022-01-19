package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    // to test console output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.out.println("CONSOLE OUTPUT:\n" + outContent.toString());
    }

    @Test
    void shouldAddMultiple() {
        BinaryTree tree = new BinaryTree();
        tree.addMultiple(5, 3, 7, 8, 2);

        assertEquals(5, tree.getRoot().getData());

        assertEquals(3, tree.getRoot().getLeft().getData());
        assertEquals(7, tree.getRoot().getRight().getData());

        assertEquals(2, tree.getRoot().getLeft().getLeft().getData());
        assertNull(tree.getRoot().getLeft().getRight());
        assertNull(tree.getRoot().getRight().getLeft());
        assertEquals(8, tree.getRoot().getRight().getRight().getData());

        assertNull(tree.getRoot().getLeft().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getLeft().getRight());
        assertNull(tree.getRoot().getRight().getRight().getLeft());
        assertNull(tree.getRoot().getRight().getRight().getRight());
    }

    @Test
    void shouldAddOneByOne() {
        BinaryTree tree = new BinaryTree();
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(8);
        tree.add(2);

        assertEquals(5, tree.getRoot().getData());

        assertEquals(3, tree.getRoot().getLeft().getData());
        assertEquals(7, tree.getRoot().getRight().getData());

        assertEquals(2, tree.getRoot().getLeft().getLeft().getData());
        assertNull(tree.getRoot().getLeft().getRight());
        assertNull(tree.getRoot().getRight().getLeft());
        assertEquals(8, tree.getRoot().getRight().getRight().getData());

        assertNull(tree.getRoot().getLeft().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getLeft().getRight());
        assertNull(tree.getRoot().getRight().getRight().getLeft());
        assertNull(tree.getRoot().getRight().getRight().getRight());
    }

    @Test
        // Degenerate Binary Tree: Every parent node has only one child node
    void shouldAddAndCreateDegenerateTree() {
        BinaryTree tree = new BinaryTree();
        tree.addMultiple(5, 4, 3);

        assertEquals(5, tree.getRoot().getData());

        assertEquals(4, tree.getRoot().getLeft().getData());
        assertNull(tree.getRoot().getRight());

        assertEquals(3, tree.getRoot().getLeft().getLeft().getData());
        assertNull(tree.getRoot().getLeft().getRight());

        assertNull(tree.getRoot().getLeft().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getLeft().getRight());
    }

    @Test
    void shouldCreateEmptyTree() {
        BinaryTree tree = new BinaryTree();
        assertNull(tree.getRoot());
    }

    @Test
    void shouldCreateOneNodeTree() {
        BinaryTree tree = new BinaryTree();
        tree.add(5);
        assertEquals(5, tree.getRoot().getData());
        assertNull(tree.getRoot().getLeft());
        assertNull(tree.getRoot().getRight());
    }

    @Test
    void shouldReturnSize() {
        BinaryTree tree = buildTree();
        assertEquals(8, tree.size());
    }

    @Test
    void shouldReturnEmptyOrNot() {
        BinaryTree tree = new BinaryTree();
        assertTrue(tree.isEmpty());
        tree.addMultiple(2, 5);
        assertFalse(tree.isEmpty());
    }

    @Test
    void shouldReturnLevel() {
        BinaryTree tree = new BinaryTree();
        assertEquals(0, tree.getLevel());
        tree.add(8);
        assertEquals(1, tree.getLevel());
        tree.add(11);
        assertEquals(2, tree.getLevel());
        tree.add(7);
        assertEquals(2, tree.getLevel());
        tree.add(14);
        assertEquals(3, tree.getLevel());
        tree.add(22);
        assertEquals(4, tree.getLevel());
        tree.add(13);
        assertEquals(4, tree.getLevel());
        tree.add(3);
        assertEquals(4, tree.getLevel());
        tree.add(5);
        assertEquals(4, tree.getLevel());
    }

    @Test
    void shouldReturnDepth() {
        BinaryTree tree = new BinaryTree();
        assertEquals(0, tree.getDepth());
        tree.add(8);
        assertEquals(0, tree.getDepth());
        tree.add(11);
        assertEquals(1, tree.getDepth());
        tree.add(7);
        assertEquals(1, tree.getDepth());
        tree.add(14);
        assertEquals(2, tree.getDepth());
        tree.add(22);
        assertEquals(3, tree.getDepth());
        tree.add(13);
        assertEquals(3, tree.getDepth());
        tree.add(3);
        assertEquals(3, tree.getDepth());
        tree.add(5);
        assertEquals(3, tree.getDepth());
    }

    @Test
    void shouldReturnContains() {
        BinaryTree tree = buildTree();
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(7));
        assertTrue(tree.contains(8));
        assertTrue(tree.contains(11));
        assertTrue(tree.contains(13));
        assertTrue(tree.contains(14));
        assertTrue(tree.contains(22));
        assertFalse(tree.contains(0));
        assertFalse(tree.contains(12));
        assertFalse(tree.contains(-1));
    }

    @Test
    void shouldTraversePreOrderDFS() {
        String expected = "8 7 3 5 11 14 13 22 ";
        BinaryTree tree = buildTree();
        tree.traversePreOrderDFS(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldTraverseInOrderDFS() {
        String expected = "3 5 7 8 11 13 14 22 ";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldTraversePostOrderDFS() {
        String expected = "5 3 7 13 22 14 11 8 ";
        BinaryTree tree = buildTree();
        tree.traversePostOrderDFS(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldTraverseLevelOrderBFS() {
        String expected = "8 7 11 3 14 5 13 22 ";
        BinaryTree tree = buildTree();
        tree.traverseLevelOrderBFS(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldPrintTreeProperly() {
        String expected =
                "       8               \n" +
                        "      / \\       \n" +
                        "     /   \\      \n" +
                        "    /     \\     \n" +
                        "   /       \\    \n" +
                        "   7       11       \n" +
                        "  /         \\   \n" +
                        " /           \\  \n" +
                        " 3           14   \n" +
                        "  \\         / \\ \n" +
                        "  5         13 22 \n" +
                        "                                \n" +
                        "\n";
        BinaryTree tree = buildTree();
        System.out.println(tree);
        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldPrintDegenerateTreeProperly() {
        String expected = "   5       \n" +
                "  /     \n" +
                " /      \n" +
                " 4       \n" +
                "/       \n" +
                "3       \n" +
                "                \n" +
                "\n";
        BinaryTree tree = new BinaryTree();
        tree.addMultiple(5, 4, 3);
        System.out.println(tree);
        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldNotRemoveIfValueNotFound() {
        String expected = "3 5 7 8 11 13 14 22 \n" +
                "3 5 7 8 11 13 14 22 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertFalse(tree.contains(99));
        tree.remove(99);
        assertEquals(8, tree.size());
        assertFalse(tree.contains(99));
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldRemoveRoot() {
        String expected = "3 5 7 8 11 13 14 22 \n" +
                "3 5 7 11 13 14 22 \n" +
                "3 5 7 13 14 22 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(8);
        assertFalse(tree.contains(8));
        assertEquals(7, tree.size());
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(11);
        assertFalse(tree.contains(11));
        assertEquals(6, tree.size());
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldRemoveBetween() {
        String expected = "3 5 7 8 11 13 14 22 \n" +
                "3 5 8 11 13 14 22 \n" +
                "5 8 11 13 14 22 \n" +
                "5 8 11 13 22 \n" +
                "5 8 13 22 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(7);
        assertFalse(tree.contains(7));
        assertEquals(7, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(3);
        assertFalse(tree.contains(3));
        assertEquals(6, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(14);
        assertFalse(tree.contains(14));
        assertEquals(5, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(11);
        assertFalse(tree.contains(11));
        assertEquals(4, tree.size());
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldRemoveLeaves() {
        String expected = "3 5 7 8 11 13 14 22 \n" +
                "3 7 8 11 13 14 22 \n" +
                "3 7 8 11 13 14 \n" +
                "3 7 8 11 14 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(5);
        assertFalse(tree.contains(5));
        assertEquals(7, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(22);
        assertFalse(tree.contains(22));
        assertEquals(6, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(13);
        assertFalse(tree.contains(13));
        assertEquals(5, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void shouldRemoveDeepestRightmost() {
        String expected = "3 5 7 8 11 13 14 22 \n" +
                "3 5 7 8 11 13 14 \n" +
                "3 5 7 8 11 14 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(22);
        assertFalse(tree.contains(22));
        assertEquals(7, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(13);
        assertFalse(tree.contains(13));
        assertEquals(6, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void temp() {
        BinaryTree tree = buildTree();
        System.out.println(tree);
        tree.remove(5);
        int maxLevel = tree.maxLevelFromNode(tree.getRoot());
        int maxLevelFromLeft = tree.maxLevelFromNode(tree.getRoot().getLeft());
        int maxLevelFromRight = tree.maxLevelFromNode(tree.getRoot().getRight());
        return;
    }

    private BinaryTree buildTree() {
        BinaryTree tree = new BinaryTree();
        tree.addMultiple(8, 11, 7, 14, 22, 13, 3, 5);
        return tree;
    }
}
