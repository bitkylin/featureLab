package leetcode8.sort;

public class DemoSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int step = size / 2; step > 0; step /= 2) {
            for (int i = step; i < size; i += step) {
                int temp = arr[i];
                int j = i;
                while (j >= step && arr[j - step] > temp) {
                    arr[j] = arr[j - step];
                    j -= step;
                }
                arr[j] = temp;
            }
        }
    }
}
