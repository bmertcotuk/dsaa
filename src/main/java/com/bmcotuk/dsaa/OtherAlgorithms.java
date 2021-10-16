package com.bmcotuk.dsaa;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
     * time: O(n)
     * space: O(1)
     */
    public int nthFibonacciIterative(int n) {
        if (n == 0) { // handled here, so loop should start from 1
            return n;
        }

        int current = 1; // 1 1 2 3
        int previous = 0; // 0 1 1 2

        for (int i = 1; i < n; i++) { // 0 1 2
            int temp = current;
            current = current + previous;
            previous = temp;
        }
        return current;
    }

    /**
     * time: O(n^1/2)
     * space: O(1)
     */
    public boolean isPrime(int n) {

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
     * NOTE: without the argument `List<String> permutations`
     * <p>
     * time: O(n!*n + n*n!*n) = O(n^2*n!)
     * space: O(n^2)
     */
    public List<String> permutationsOfString(String str) {
        List<String> permutations = new ArrayList<>();
        permutation(str, "", permutations);
        return permutations;
    }

    private void permutation(String remainder, String prefix, List<String> permutations) {
        if (remainder.isEmpty()) {
            // n! calls, n each
            permutations.add(prefix);
        } else {
            // n*n! calls, n each
            for (int i = 0; i < remainder.length(); i++) {
                permutation(remainder.substring(0, i) + remainder.substring(i + 1),
                        prefix + remainder.charAt(i),
                        permutations);
            }
        }
    }

    // if there is a loop do not assign values like here, be careful
    /*private void permutationNotWorking(String remainder, String prefix) {
        if (remainder.isEmpty()) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < remainder.length(); i++) {
                prefix += remainder.charAt(i);
                remainder = remainder.substring(0, i) + remainder.substring(i + 1);
                permutation(remainder, prefix);
            }
        }
    }*/
}
