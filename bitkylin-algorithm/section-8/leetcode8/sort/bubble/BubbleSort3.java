package leetcode8.sort.bubble;


import leetcode8.sort.KySort;

import static leetcode8.sort.Utils.swap;


public class BubbleSort3 implements KySort {

    @Override
    public void kySort(int[] a, int size) {
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }
}
