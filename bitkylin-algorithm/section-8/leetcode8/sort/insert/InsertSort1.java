package leetcode8.sort.insert;


import leetcode8.sort.IKySort;

public class InsertSort1 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
