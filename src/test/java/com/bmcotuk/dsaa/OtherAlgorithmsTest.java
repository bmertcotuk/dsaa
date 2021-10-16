package com.bmcotuk.dsaa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OtherAlgorithmsTest {

    private static Stream<Arguments> fibonacciData() {
        return Stream.of(
                Arguments.arguments(0, 0),
                Arguments.arguments(1, 1),
                Arguments.arguments(2, 1),
                Arguments.arguments(3, 2),
                Arguments.arguments(4, 3),
                Arguments.arguments(5, 5),
                Arguments.arguments(6, 8)
        );
    }

    private static Stream<Arguments> primeData() {
        return Stream.of(
                Arguments.arguments(0, false),
                Arguments.arguments(1, false),
                Arguments.arguments(2, true),
                Arguments.arguments(3, true),
                Arguments.arguments(4, false),
                Arguments.arguments(5, true),
                Arguments.arguments(6, false),
                Arguments.arguments(7, true),
                Arguments.arguments(8, false),
                Arguments.arguments(9, false),
                Arguments.arguments(10, false),
                Arguments.arguments(11, true),
                Arguments.arguments(12, false),
                Arguments.arguments(13, true),
                Arguments.arguments(14, false),
                Arguments.arguments(15, false),
                Arguments.arguments(16, false),
                Arguments.arguments(17, true)
        );
    }

    @InjectMocks
    private OtherAlgorithms otherAlgorithms;

    @ParameterizedTest
    @MethodSource("fibonacciData")
    void shouldCalculateFibonacciNumbersRecursively(int input, int output) {
        assertEquals(output, otherAlgorithms.nthFibonacciRecursive(input));
    }

    @ParameterizedTest
    @MethodSource("fibonacciData")
    void shouldCalculateFibonacciNumbersIteratively(int input, int output) {
        assertEquals(output, otherAlgorithms.nthFibonacciIterative(input));
    }

    @Test
    void shouldCalculatePermutationsOfString() {
        List<String> expected = List.of("ABC", "ACB", "BAC", "BCA", "CAB", "CBA");
        List<String> actual = otherAlgorithms.permutationsOfString("ABC");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("primeData")
    void shouldCheckPrimeNumbers(int input, boolean output) {
        assertEquals(output, otherAlgorithms.isPrime(input));
    }
}
