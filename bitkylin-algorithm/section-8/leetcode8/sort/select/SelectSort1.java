package leetcode8.sort.select;

import leetcode8.sort.IKySort;

import static leetcode8.sort.Utils.swap;

public class SelectSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                swap(arr, i, min);
            }
        }
    }
}
