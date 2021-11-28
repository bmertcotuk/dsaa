package com.bmcotuk.dsaa.algorithms;

import com.bmcotuk.dsaa.common.KeyValuePair;
import com.bmcotuk.dsaa.common.Node;
import com.bmcotuk.dsaa.datastructures.LinkedList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mert Cotuk
 */
@Component
public class OtherAlgorithms {

    /**
     * time: O(2^n) - branches^depth
     * space: O(n) - space required is proportional to the maximum depth of the recursion tree
     */
    public int nthFibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return nthFibonacciRecursive(n - 1) + nthFibonacciRecursive(n - 2);
    }

    /**
     * Memoization is top-down dynamic programming approach which makes recursive algorithms efficient without making
     * them iterative.
     * More at: https://dzone.com/articles/memoization-make-recursive-algorithms-efficient
     * <p>
     * time: O(n)
     * space: O(n)
     */
    public int nthFibonacciTopDownDP(int n) {
        int[] memo = new int[n + 1]; // because we directly index the array with "n"
        return nthFibonacciTopDownDPRecursion(n, memo);
    }

    private int nthFibonacciTopDownDPRecursion(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] == 0) {
            memo[n] = nthFibonacciTopDownDPRecursion(n - 1, memo) + nthFibonacciTopDownDPRecursion(n - 2, memo);
        }
        return memo[n];
    }

    /**
     * Bottom-up dynamic programming approach is iterative and the most efficient among all.
     * <p>
     * time: O(n)
     * space: O(1)
     */
    public int nthFibonacciBottomUpDP(int n) {
        if (n == 0) {
            return n;
        }
        int previous = 0;
        int current = 1;

        for (int i = 2; i < n; i++) { // loop starts from 2 since we gave the minimum requirement
            int next = previous + current;
            previous = current;
            current = next;
        }
        return current + previous;
    }

    /**
     * time: O(logn)
     * space: O(logn)
     */
    public int printPowersOf2UntilN(int n) {
        if (n < 1) { // error case
            return 0;
        } else if (n == 1) { // base case to avoid 1/2 = 0 and 0*2 = 0 failure
            System.out.println(n);
            return n;
        } else { // recursion
            int previous = printPowersOf2UntilN(n / 2);
            int current = previous * 2;
            System.out.println(current);
            return current;
        }
    }

    /**
     * time: O(n^1/2)
     * space: O(1)
     */
    public boolean isPrimeIterative(int n) {

        if (n <= 1) return false;

        int roundedSquareRoot = (int) Math.floor(Math.sqrt(n));
        for (int i = 2; i <= roundedSquareRoot; i++) { // i should not start from 1 :(
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * time: O(n^1/2)
     * space: O(n^1/2)
     */
    public boolean isPrimeRecursive(int n) {

        if (n <= 1) {
            return false;
        }
        if (n == 2) { // important to add
            return true;
        }
        return isPrimeRecursion(2, n);
    }

    private boolean isPrimeRecursion(int i, int n) {

        if (n % i == 0) { // check this first or it will fail
            return false;
        }
        if (i * i >= n) {
            return true;
        }
        return isPrimeRecursion(i + 1, n);
    }

    /**
     * time: O(logn)
     * space: O(1)
     */
    public int sumDigitsIterative(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    /**
     * time: O(logn)
     * space: O(logn)
     */
    public int sumDigitsRecursive(int n) {
        if (n == 0) {
            return 0;
        }
        return n % 10 + sumDigitsRecursive(n / 10);
    }

    /**
     * time: O(n!*n + n*n!*n) = O(n^2*n!)
     * space: O(n^2)
     */
    public void printPermutationsOfString(String str) {
        permutationRecursion(str, "");
    }

    private void permutationRecursion(String remainder, String prefix) {
        if (remainder.isEmpty()) {
            // n! calls, n each
            System.out.println(prefix);
        } else {
            // n*n! calls, n each
            for (int i = 0; i < remainder.length(); i++) {
                permutationRecursion(remainder.substring(0, i) + remainder.substring(i + 1),
                        prefix + remainder.charAt(i));
            }
        }
    }

    /**
     * a = alphabet size
     * k = length of strings
     * <p>
     * Formula `branches^depth` is the same since high school. This gives us how many calls will occur. The next step
     * is to multiply by the base case's complexity. This logic applies to all kinds of problems.
     * <p>
     * time: O(k*a^k)
     * space: O(k)
     */
    int alphabetSize = 5;

    public void printAllSortedPermutationsInTheAlphabetOfLength(int remaining, String prefix) {
        if (remaining == 0) {
            if (isInOrder(prefix)) {
                System.out.println(prefix);
            }
        } else {
            for (int i = 0; i < alphabetSize; i++) {
                printAllSortedPermutationsInTheAlphabetOfLength(remaining - 1, prefix + nthLetterOfTheAlphabet(i));
            }
        }
    }

    private char nthLetterOfTheAlphabet(int n) {
        return (char) (((int) 'a') + n);
    }

    private boolean isInOrder(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) >= s.charAt(i + 1)) { // just to avoid same characters, still can be ">"
                return false;
            }
        }
        return true;
    }

    /**
     * Equation: a^3 + b^3 = c^3 + d^3 where a, b, c, d in [1, n]
     * <p>
     * time: O(n^2)
     * space: O(n^2)
     */
    public void printAllPositiveIntegerSolutionsToCubicEquation(int n) {

        Map<Integer, List<KeyValuePair<Integer, Integer>>> resultListMap = new HashMap<>();
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                KeyValuePair<Integer, Integer> pair = new KeyValuePair<>(a, b);
                int result = a * a * a + b * b * b;
                List<KeyValuePair<Integer, Integer>> list = resultListMap.getOrDefault(result, new ArrayList<>());
                list.add(pair);
                resultListMap.put(result, list);
            }
        }
        for (Map.Entry<Integer, List<KeyValuePair<Integer, Integer>>> entry : resultListMap.entrySet()) {
            List<KeyValuePair<Integer, Integer>> resultList = entry.getValue();
            for (KeyValuePair<Integer, Integer> pair1 : resultList) {
                for (KeyValuePair<Integer, Integer> pair2 : resultList) {
                    System.out.println("Pair1" + pair1 + " - Pair2" + pair2);
                }
            }
        }
    }

    /**
     * Two sorted integer arrays, of the same size, each with all distinct elements.
     * <p>
     * time: O(n)
     * space: O(1)
     */
    public int findTheNumberOfElementsInCommon(int[] array1, int[] array2) {
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                i++;
            } else if (array1[i] > array2[j]) {
                j++;
            } else {
                count++;
                i++;
                j++;
            }
        }
        return count;
    }

    /**
     * Implementation on a singly linked list is a real challenge!
     * <p>
     * time: O(n)
     * space: O(n)
     */
    public int[] rearrangeArrayWithRunnerTechnique(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array should not be null.");
        }
        if (array.length % 2 != 0) {
            throw new IllegalArgumentException("Array's length must be an even number.");
        }

        if (array.length == 0) {
            return array;
        }

        // move pointers
        int slowPointer = 0;
        int fastPointer = 1;
        while (fastPointer != array.length - 1) {
            slowPointer++;
            fastPointer += 2;
        }
        slowPointer++;
        fastPointer = 0;

        // start weaving
        int[] arrangedArray = new int[array.length];
        int i = 0;
        while (i < arrangedArray.length) {
            arrangedArray[i] = array[fastPointer];
            i++;
            fastPointer++;
            arrangedArray[i] = array[slowPointer];
            i++;
            slowPointer++;
        }
        return arrangedArray;
    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public LinkedList<Integer> rearrangeLinkedListWithRunnerTechnique(LinkedList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("List should not be null.");
        }
        if (list.size() % 2 != 0) {
            throw new IllegalArgumentException("List's size must be an even number.");
        }

        if (list.isEmpty()) {
            return list;
        }

        // move pointers
        Node<Integer> fastPointer = list.getHead().getNext();
        Node<Integer> slowPointer = list.getHead();
        while (fastPointer.getNext() != null) {
            fastPointer = fastPointer.getNext().getNext();
            slowPointer = slowPointer.getNext();
        }
        // do not touch slow pointer here since we have only singly linked list lacking previous()
        fastPointer = list.getHead();

        // start weaving
        while (fastPointer.getNext().getNext() != null) {
            Node<Integer> temporary = slowPointer.getNext();
            slowPointer.setNext(slowPointer.getNext().getNext());
            temporary.setNext(fastPointer.getNext());
            fastPointer.setNext(temporary);

            // do not touch the slow pointer again as it will indirectly be moved forward by each weaving
            fastPointer = fastPointer.getNext().getNext();
        }
        return list;
    }
}
