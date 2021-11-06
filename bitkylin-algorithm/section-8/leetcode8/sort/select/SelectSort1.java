package leetcode8.sort.select;

import leetcode8.sort.KySort;

import static leetcode8.sort.Utils.swap;

public class SelectSort1 implements KySort {

    @Override
    public void kySort(int[] a, int size) {
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (i != min) {
                swap(a, i, min);
            }
        }
    }
}
