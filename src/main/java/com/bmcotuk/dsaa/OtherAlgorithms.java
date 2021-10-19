package com.bmcotuk.dsaa;

import org.springframework.stereotype.Component;

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

    private int nthFibonacciTopDownDPRecursion(int n, int memo[]) {
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
