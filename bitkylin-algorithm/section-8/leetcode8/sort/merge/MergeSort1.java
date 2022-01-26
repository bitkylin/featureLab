package leetcode8.sort.merge;

import leetcode8.sort.IKySort;

public class MergeSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        sort(arr, 0, size - 1, new int[arr.length]);
    }

    private void sort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(arr, left, mid, temp);
        sort(arr, mid + 1, right, temp);
        merge(arr, left, mid, right, temp);
    }

    private void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int k = left;
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        while (left <= right) {
            arr[left] = temp[left];
            left++;
        }
    }
}
