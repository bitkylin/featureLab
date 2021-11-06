package leetcode8.sort.bubble;

import leetcode8.sort.KySort;

import static leetcode8.sort.Utils.swap;

public class BubbleSort2 implements KySort {

    @Override
    public void kySort(int[] a, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = size - 1; j > i; j--) {
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                }
            }
        }
    }
}
