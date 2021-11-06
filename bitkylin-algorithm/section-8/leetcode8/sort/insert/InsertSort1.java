package leetcode8.sort.insert;


import leetcode8.sort.KySort;

public class InsertSort1 implements KySort {

    @Override
    public void kySort(int[] a, int size) {
        for (int i = 1; i < size; i++) {
            int temp = a[i];
            int j = i;
            while (j > 0 && a[j - 1] > temp) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
    }
}
