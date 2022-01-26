package leetcode8.sort.bubble;

import leetcode8.sort.IKySort;

import static leetcode8.sort.Utils.swap;

public class BubbleSort2 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = size - 1; j > i; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }
}
