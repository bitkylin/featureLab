package leetcode8.sort;

@FunctionalInterface
public interface IKySort {

    void sort(int[] arr, int size);

    default void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
