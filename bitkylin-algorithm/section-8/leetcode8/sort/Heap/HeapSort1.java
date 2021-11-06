package leetcode8.sort.Heap;

import leetcode8.sort.KySort;

import static leetcode8.sort.Utils.swap;

public class HeapSort1 implements KySort {

    @Override
    public void kySort(int[] a, int n) {
        for (int i = n / 2; i >= 0; i--) {
            heapAdjust(a, i, n - 1);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);
            heapAdjust(a, 0, i - 1);
        }
    }

    /**
     * @param index 父节点索引
     * @param n     尾节点索引
     */
    private void heapAdjust(int[] a, int index, int n) {
        int temp = a[index];
        for (int child = index * 2 + 1; child <= n; child = index * 2 + 1) {
            if (child < n && a[child] < a[child + 1]) {
                child++;
            }
            if (temp > a[child]) {
                break;
            }
            a[index] = a[child];
            index = child;
        }
        a[index] = temp;
    }
}
