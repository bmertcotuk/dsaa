package com.bmcotuk.dsaa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OtherAlgorithmsTest {

    // to test console output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @ParameterizedTest
    @MethodSource("fibonacciData")
    void shouldCalculateFibonacciNumbersRecursively(int input, int output) {
        assertEquals(output, otherAlgorithms.nthFibonacciRecursive(input));
    }

    @ParameterizedTest
    @MethodSource("fibonacciData")
    void shouldCalculateFibonacciNumbersTopDownDP(int input, int output) {
        assertEquals(output, otherAlgorithms.nthFibonacciTopDownDP(input));
    }

    @ParameterizedTest
    @MethodSource("fibonacciData")
    void shouldCalculateFibonacciNumbersBottomUpDP(int input, int output) {
        assertEquals(output, otherAlgorithms.nthFibonacciBottomUpDP(input));
    }

    @Test
    void shouldPrintPermutationsOfString() {
        String expected = "ABC\n" +
                "ACB\n" +
                "BAC\n" +
                "BCA\n" +
                "CAB\n" +
                "CBA\n";
        otherAlgorithms.printPermutationsOfString("ABC");
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("primeData")
    void shouldCheckPrimeNumbersIteratively(int input, boolean output) {
        assertEquals(output, otherAlgorithms.isPrimeIterative(input));
    }

    @ParameterizedTest
    @MethodSource("primeData")
    void shouldCheckPrimeNumbersRecursively(int input, boolean output) {
        assertEquals(output, otherAlgorithms.isPrimeRecursive(input));
    }

    @Test
    void shouldPrintPowersOf2UntilN() {
        String expected = "1\n" +
                "2\n" +
                "4\n" +
                "8\n" +
                "16\n" +
                "32\n" +
                "64\n";

        otherAlgorithms.printPowersOf2UntilN(67);
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }
}
