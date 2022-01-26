package leetcode8.sort.shell;

import leetcode8.sort.IKySort;

import static leetcode8.sort.Utils.swap;

public class ShellSort2 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int step = size / 2; step > 0; step /= 2) {
            for (int i = step; i < size; i++) {
                for (int j = i; j >= step; j -= step) {
                    if (arr[j - step] > arr[j]) {
                        swap(arr, j - step, j);
                    } else {
                        break;
                    }
                }
            }
        }

    }
}
