package leetcode8.sort;

public class DemoSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        mergeSort(arr, 0, size - 1, new int[size]);
    }

    private void mergeSort(int[] arr, int left, int right, int[] memo) {
        if (right - left <= 0) {
            return;
        }
        int mid = (right - left) / 2 + left;
        mergeSort(arr, left, mid, memo);
        mergeSort(arr, mid + 1, right, memo);
        merge(arr, left, mid, right, memo);
    }

    private void merge(int[] arr, int left, int mid, int right, int[] memo) {
        int head = left;
        int i1 = left;
        int i2 = mid + 1;
        while (i1 <= mid && i2 <= right) {
            if (arr[i1] < arr[i2]) {
                memo[left++] = arr[i1++];
            } else {
                memo[left++] = arr[i2++];
            }
        }
        while (i1 <= mid) {
            memo[left++] = arr[i1++];
        }
        while (i2 <= right) {
            memo[left++] = arr[i2++];
        }
        while (head <= right) {
            arr[head] = memo[head++];
        }
    }
}
