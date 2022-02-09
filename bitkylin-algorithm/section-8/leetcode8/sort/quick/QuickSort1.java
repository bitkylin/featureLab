package leetcode8.sort.quick;

import leetcode8.sort.IKySort;

public class QuickSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        quickSort(arr, 0, size - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (right - left <= 1) {
            if (arr[left] > arr[right]) {
                swap(arr, left, right);
            }
            return;
        }
        int point = midBy3(arr, left, right);
        quickSort(arr, left, point - 1);
        quickSort(arr, point + 1, right);
    }

    private int midBy3(int[] arr, int left, int right) {
        int temp = arr[right];
        int end = right;
        right--;

        while (left < right) {
            while (arr[left] < temp) left++;
            while (arr[right] > temp) right--;
            swap(arr, left, right);
        }
        swap(arr, left, end);
        return left;
    }
}
