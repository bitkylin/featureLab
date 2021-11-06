package leetcode8.sort.insert;


import leetcode8.sort.KySort;

public class InsertSort2 implements KySort {

    @Override
    public void kySort(int[] a, int size) {
        for (int i = 1; i < size; i++) {
            int temp = a[i];
            for (int j = i; j >= 0; j--) {
                if (j > 0 && a[j - 1] > temp) {
                    a[j] = a[j - 1];
                } else {
                    a[j] = temp;
                    break;
                }
            }
        }
    }
}
