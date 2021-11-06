package leetcode8.sort;

public class Utils {

    private Utils() {
    }

    public static void swap(int[] a, int i, int j) {
        int k = a[i];
        a[i] = a[j];
        a[j] = k;
    }
}
