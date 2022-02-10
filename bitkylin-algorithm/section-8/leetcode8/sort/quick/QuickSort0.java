package leetcode8.sort.quick;

import leetcode8.sort.IKySort;

public class QuickSort0 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        quickSort(arr, 0, size - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int point = solve(arr, left, right);
        quickSort(arr, left, point - 1);
        quickSort(arr, point + 1, right);
    }

    private int solve(int[] arr, int left, int right) {
        int p = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < arr[right]) {
                swap(arr, i, p++);
            }
        }
        swap(arr, right, p);
        return p;
    }
}
