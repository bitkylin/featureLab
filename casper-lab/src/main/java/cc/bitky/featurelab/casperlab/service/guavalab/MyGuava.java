package cc.bitky.featurelab.casperlab.service.guavalab;

/**
 * @author liMingLiang
 * @date 2019-05-11
 */
public class MyGuava {

    public static void main(String[] args) {
        int[] a1 = {1, 6, 3, 8, 2, 7};
        int[] a = {54, 35, 48, 36, 27, 12, 44, 44, 8, 14, 26, 17, 28};
        new MyGuava().quickSort(a, 0, a.length - 1);
        System.out.println();
    }

    private void swap(int[] a, int i, int j) {
        int k = a[i];
        a[i] = a[j];
        a[j] = k;
    }

    private void quickSort(int[] a, int left, int right) {
        if (right - left <= 1) {
            sort(a, left, right);
            return;
        }

        int p = middleByThree(a, left, right);
        quickSort(a, left, p - 1);
        quickSort(a, p, right);
    }

    private int middleByThree(int[] a, int left, int right) {
        int middle = (left + right) / 2;

        sort(a, left, middle, right);
        int temp = a[middle];
        swap(a, middle, --right);

        while (true) {
            while (a[++left] < temp) ;
            while (a[--right] > temp) ;
            if (left >= right) {
                break;
            } else {
                swap(a, left, right);
            }
        }
        return left;
    }

    private void sort(int[] a, int x, int y) {
        if (a[x] > a[y]) {
            swap(a, x, y);
        }
    }

    private void sort(int[] a, int x, int y, int z) {
        sort(a, x, y);
        sort(a, x, z);
        sort(a, y, z);
    }
}
