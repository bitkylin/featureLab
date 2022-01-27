package leetcode8.sort.Heap;

import leetcode8.sort.IKySort;

public class HeapSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int i = size / 2; i >= 0; i--) {
            heapAdjust(arr, i, size - 1);
        }
        for (int i = size - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapAdjust(arr, 0, i - 1);
        }
    }

    /**
     * @param i 父节点索引
     * @param n 尾节点索引
     */
    private void heapAdjust(int[] a, int i, int n) {
        int temp = a[i];
        for (int child = i * 2 + 1; child <= n; child = i * 2 + 1) {
            if (child < n && a[child] < a[child + 1]) {
                child++;
            }
            if (temp > a[child]) {
                break;
            }
            a[i] = a[child];
            i = child;
        }
        a[i] = temp;
    }
}
