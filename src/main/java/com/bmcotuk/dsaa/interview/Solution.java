package com.bmcotuk.dsaa.interview;

/**
 * @author Mert Cotuk
 */
class Solution {
    public int solution(int A, int B, int K) {
        // write your code in Java SE 8

        // number of multiples of K in (0, B]
        int untilB = B / K;
        // number of multiples of K in (0, A]
        int untilA = A / K;
        // not to lose A if it is a multiple
        int correction = (A % K == 0) ? 1 : 0;
        return untilB - untilA + correction;
    }
}














