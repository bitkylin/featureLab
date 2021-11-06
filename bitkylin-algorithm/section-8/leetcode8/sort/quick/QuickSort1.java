package leetcode8.sort.quick;

import leetcode8.sort.KySort;

import static leetcode8.sort.Utils.swap;

public class QuickSort1 implements KySort {

    @Override
    public void kySort(int[] a, int size) {
        quickSort(a, 0, size - 1);
    }

    private void quickSort(int[] a, int left, int right) {
        if (right - left < 2) {
            if (a[left] > a[right])
                swap(a, left, right);
            return;
        }
        int p = middleBy3(a, left, right);

        quickSort(a, left, p - 1);
        quickSort(a, p + 1, right);
    }

    private int middleBy3(int[] a, int left, int right) {
        int p = (left + right) / 2;
        int end = right;

        if (a[left] > a[p]) swap(a, left, p);
        if (a[left] > a[right]) swap(a, left, right);
        if (a[p] > a[right]) swap(a, p, right);

        int temp = a[p];
        swap(a, p, right);

        while (true) {
            while (a[++left] < temp) ;
            while (a[--right] > temp) ;
            if (left >= right) break;
            else swap(a, left, right);
        }
        swap(a, left, end);
        return left;
    }
}
