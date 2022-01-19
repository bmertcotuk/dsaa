package com.bmcotuk.dsaa.algorithms;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Mert Cotuk
 */
@Component
public class Sorts {

    /**
     * Recursively pick a pivot and slide elements accordingly in place
     * <p>
     * time: O(d*(n+b))
     * ----> d: number of digits
     * ----> b: base of numbers (e.g. 10)
     * space: O(k+n)
     * ----> k is the largest element
     */
    public void radixSort(int[] arr) {
        int numbersCount = 10;
        LinkedList<Integer>[] digits = new LinkedList[numbersCount];
        for (int i = 0; i < numbersCount; ++i) {
            digits[i] = new LinkedList<>();
        }
        distribute(arr, digits, "ones");
        collect(digits, arr);
        distribute(arr, digits, "tens");
        collect(digits, arr);
    }

    private void distribute(int[] arr, LinkedList[] digits, String digitType) {
        for (int i = 0; i < arr.length; i++) {
            if ("ones".equals(digitType)) {
                digits[arr[i] % 10].addLast(arr[i]);
            } else {
                digits[arr[i] / 10].addLast(arr[i]);
            }
        }
    }

    private void collect(LinkedList[] digits, int[] arr) {
        int i = 0;
        for (int digit = 0; digit < 10; ++digit) {
            while (!digits[digit].isEmpty()) {
                arr[i++] = (Integer) digits[digit].removeFirst();
            }
        }
    }

    /**
     * Recursively pick a pivot and slide elements accordingly in place
     * <p>
     * time: O(n*log)/O(n^2)
     * space: O(logn)
     */
    public void quickSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }
        if (index < right) {
            quickSort(arr, index, right);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            // find elements to be swapped
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            // swap elements
            if (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    /**
     * For each element find the minimum at the right partition and swap.
     * <p>
     * time: O(n^2)
     * space: O(1)
     */
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    /**
     * Keep growing left partition as a sorted array as you slide elements to right when necessary.
     * <p>
     * time: O(n^2)
     * space: O(1)
     */
    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j > -1 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * time: O(n*logn)
     * space: O(n*logn)
     */
    public void mergeSort(int[] arr, int size) {
        // base case and error prevention
        if (size < 2) {
            return;
        }

        int mid = size / 2;
        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, size);

        mergeSort(leftArr, leftArr.length);
        mergeSort(rightArr, rightArr.length);
        merge(arr, leftArr, rightArr);
    }

    // replaces elements on one step bigger piece (i.e. arr itself)
    // merging of two already sorted arrays, parallel linear iterations
    private void merge(int[] arr, int[] leftArr, int[] rightArr) {
        int i = 0, j = 0, k = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }
}
