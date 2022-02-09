package leetcode8.sort;

public class DemoSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        quickSort(arr, 0, size - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (right - left <= 1) {
            if (arr[left] > arr[right]) {
                swap(arr, left, right);
            }
            return;
        }
        int point = midBy3(arr, left, right);
        quickSort(arr, left, point - 1);
        quickSort(arr, point + 1, right);
    }

    private int midBy3(int[] arr, int left, int right) {
        int mid = (right - left) / 2 + left;

        if (arr[left] > arr[right]) swap(arr, left, right);
        if (arr[left] > arr[mid]) swap(arr, left, mid);
        if (arr[mid] > arr[right]) swap(arr, mid, right);

        int temp = arr[mid];
        int end = right;
        swap(arr, mid, right);

        while (left < right) {
            while (arr[++left] < temp) ;
            while (arr[--right] > temp) ;
            swap(arr, left, end);
        }
        return left;
    }
}
