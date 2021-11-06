package leetcode8.sort.bubble;

import leetcode8.sort.KySort;

import static leetcode8.sort.Utils.swap;

public class BubbleSort1 implements KySort {

    @Override
    public void kySort(int[] a, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }
}
