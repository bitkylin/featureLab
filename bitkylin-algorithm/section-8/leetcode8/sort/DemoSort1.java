package leetcode8.sort;

public class DemoSort1 implements IKySort {

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

    private void heapAdjust(int[] arr, int i, int n) {
        int temp = arr[i];
        for (int child = 2 * i + 1; child <= n; child = 2 * i + 1) {
            if (child < n && arr[child] < arr[child + 1]) {
                child++;
            }
            if (arr[child] < temp) {
                break;
            }
            arr[i] = arr[child];
            i = child;
        }
        arr[i] = temp;
    }
}
