package leetcode8.sort.merge;

import leetcode8.sort.KySort;

public class MergeSort1 implements KySort {

    @Override
    public void kySort(int[] a, int size) {
        sort(a, 0, size - 1, new int[a.length]);
    }

    private void sort(int[] a, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(a, left, mid, temp);
        sort(a, mid + 1, right, temp);
        merge(a, left, mid, right, temp);
    }

    private void merge(int[] a, int left, int mid, int right, int[] temp) {
        int k = left;
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= right) {
            temp[k++] = a[j++];
        }

        while (left <= right) {
            a[left] = temp[left];
            left++;
        }
    }
}
