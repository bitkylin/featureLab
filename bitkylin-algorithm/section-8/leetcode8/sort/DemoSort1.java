package leetcode8.sort;

public class DemoSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int i = size / 2; i >= 0; i--) {
            adjust(arr, i, size - 1);
        }
        for (int i = size - 1; i > 0; i--) {
            swap(arr, i, 0);
            adjust(arr, 0, i);
        }
    }

    private void adjust(int[] arr, int i, int n) {
        int temp = arr[i];
        for (int child = 2 * i + 1; child <= n; child = 2 * i + 1) {
            if (child < n && arr[child + 1] > arr[child]) {
                child++;
            }
            if (arr[child] < arr[i]) {
                break;
            }
            arr[i] = arr[child];
            i = child;
        }
        arr[i] = temp;
    }
}
