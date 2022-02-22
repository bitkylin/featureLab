package leetcode8.sort;

public class DemoSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            for (; j > 0; j--) {
                if (arr[j - 1] > temp) {
                    arr[j] = arr[j - 1];
                }
                else break;
            }
            arr[j] = temp;
        }
    }
}
