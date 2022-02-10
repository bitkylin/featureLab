package leetcode8.sort.merge;

import leetcode8.sort.IKySort;

public class MergeSort0 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        if (size > 0) {
            mergeSort(arr, 0, size - 1, new int[size]);
        }
    }

    private void mergeSort(int[] arr, int left, int right, int[] memo) {
        if (right == left) return;

        int mid = (right - left) / 2 + left;
        mergeSort(arr, left, mid, memo);
        mergeSort(arr, mid + 1, right, memo);
        merge(arr, left, mid, right, memo);
    }

    private void merge(int[] arr, int left, int mid, int right, int[] memo) {
        int i = left;
        int k = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) memo[k++] = arr[i++];
            else memo[k++] = arr[j++];
        }
        while (i <= mid) memo[k++] = arr[i++];
        while (j <= right) memo[k++] = arr[j++];
        while (left <= right) arr[left] = memo[left++];
    }
}
