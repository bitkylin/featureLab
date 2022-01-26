package leetcode8.sort.insert;


import leetcode8.sort.IKySort;

public class InsertSort2 implements IKySort {

    @Override
    public void sort(int[] arr, int size) {
        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            for (int j = i; j >= 0; j--) {
                if (j > 0 && arr[j - 1] > temp) {
                    arr[j] = arr[j - 1];
                } else {
                    arr[j] = temp;
                    break;
                }
            }
        }
    }
}
