package leetcode8.sort.bubble;

import leetcode8.sort.IKySort;

/**
 * 从前到后循环，将最大值往后排
 */
public class BubbleSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
}
