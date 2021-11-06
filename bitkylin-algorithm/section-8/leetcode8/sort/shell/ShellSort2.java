package leetcode8.sort.shell;

import leetcode8.sort.KySort;

import static leetcode8.sort.Utils.swap;

public class ShellSort2 implements KySort {

    @Override
    public void kySort(int[] a, int size) {
        for (int step = size / 2; step > 0; step /= 2) {
            for (int i = step; i < size; i++) {
                for (int j = i; j >= step; j -= step) {
                    if (a[j - step] > a[j]) {
                        swap(a, j - step, j);
                    } else {
                        break;
                    }
                }
            }
        }

    }
}
