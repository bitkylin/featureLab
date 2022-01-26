package leetcode8.sort.quick;

import leetcode8.sort.IKySort;

import static leetcode8.sort.Utils.swap;

public class QuickSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        quickSort(arr, 0, size - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (right - left < 2) {
            if (arr[left] > arr[right])
                swap(arr, left, right);
            return;
        }
        int p = middleBy3(arr, left, right);

        quickSort(arr, left, p - 1);
        quickSort(arr, p + 1, right);
    }

    private int middleBy3(int[] arr, int left, int right) {
        int p = (left + right) / 2;
        int end = right;

        if (arr[left] > arr[p]) swap(arr, left, p);
        if (arr[left] > arr[right]) swap(arr, left, right);
        if (arr[p] > arr[right]) swap(arr, p, right);

        int temp = arr[p];
        swap(arr, p, right);

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
