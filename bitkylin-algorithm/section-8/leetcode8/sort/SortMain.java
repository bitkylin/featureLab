package leetcode8.sort;

import leetcode8.sort.shell.ShellSort1;

public class SortMain {

    private KySort sorter;
    private int[] a;

    private SortMain(KySort sorter, int... values) {
        this.sorter = sorter;
        a = values;
    }

    public static void main(String[] args) {
        sort(new ShellSort1(), 5, 4, 3, 2, 1, 0);
        sort(new ShellSort1(), 54, 35, 48, 36, 27, 12, 44, 44, 8, 14, 26, 17, 28);
        sort(new ShellSort1(), 32, 103, 24, 88, 95, 70, 97, 15, 102, 6, 79, 46, 51, 37, 93, 108, 9, 58, 53, 58, 79, 36, 58, 91, 78, 58, 61, 81);
    }

    private static void sort(KySort shellSort1, int... arr) {
        SortMain sortMain = new SortMain(shellSort1, arr);
        sortMain.display();
        sortMain.sorter.kySort(arr, arr.length);
        sortMain.display();
        System.out.println("---");
    }

    private void display() {
        StringBuilder builder = new StringBuilder();
        for (int i : a) {
            builder.append(i).append(" ");
        }
        System.out.println(builder.toString());
    }
}
