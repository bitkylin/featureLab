package leetcode8.sort.quick;

import leetcode8.sort.IKySort;

public class QuickSort2 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        quickSort(arr, 0, size - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int temp = arr[left];
        int i = left;
        int j = right;

        while (i < j) {
            while (i < j && arr[j] >= temp) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }

            while (i < j && arr[i] <= temp) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = temp;
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
}
