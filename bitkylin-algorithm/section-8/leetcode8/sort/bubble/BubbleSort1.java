package leetcode8.sort.bubble;

import leetcode8.sort.IKySort;

import static leetcode8.sort.Utils.swap;

public class BubbleSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
}
