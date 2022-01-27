package leetcode8.sort.quick;

import leetcode8.sort.IKySort;

public class QuickSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        if (size > 1) {
            quickSort(arr, 0, size - 1);
        }
    }

    private void quickSort(int[] arr, int left, int right) {
        if (right - left <= 1) {
            if (arr[left] > arr[right]) {
                swap(arr, left, right);
            }
            return;
        }
        int mid = calc(arr, left, right);

        quickSort(arr, left, mid - 1);
        quickSort(arr, mid + 1, right);
    }

    private int calc(int[] arr, int left, int right) {
        int mid = (right - left) / 2 + left;

        if (arr[left] > arr[mid]) swap(arr, left, mid);
        if (arr[left] > arr[right]) swap(arr, left, right);
        if (arr[mid] > arr[right]) swap(arr, mid, right);

        int temp = arr[mid];
        int end = right;
        swap(arr, mid, right);

        while (true) {
            while (arr[++left] < temp) ;
            while (arr[--right] > temp) ;
            if (left >= right) break;
            else swap(arr, left, right);
        }
        swap(arr, left, end);
        return left;
    }
}
