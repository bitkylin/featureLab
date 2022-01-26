package leetcode8.sort;

import leetcode8.sort.Heap.HeapSort1;
import leetcode8.sort.bubble.BubbleSort1;
import leetcode8.sort.bubble.BubbleSort2;
import leetcode8.sort.bubble.BubbleSort3;
import leetcode8.sort.insert.InsertSort1;
import leetcode8.sort.insert.InsertSort2;
import leetcode8.sort.merge.MergeSort1;
import leetcode8.sort.quick.QuickSort1;
import leetcode8.sort.quick.QuickSort2;
import leetcode8.sort.select.SelectSort1;
import leetcode8.sort.shell.ShellSort1;
import leetcode8.sort.shell.ShellSort2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SortMain {

    private static int[][] demoArr = new int[][]{
            {5, 4, 3, 2, 1, 0},
            {54, 35, 48, 36, 27, 12, 44, 44, 8, 14, 26, 17, 28},
            {32, 103, 24, 88, 95, 70, 97, 15, 102, 6, 79, 46, 51, 37, 93, 108, 9, 58, 53, 58, 79, 36, 58, 91, 78, 58, 61, 81}
    };

    public static void main(String[] args) {
        randomDemoArr();
//        sort(new ShellSort1());
        sortAll();
    }

    private static void sortAll() {
        sort(new BubbleSort1());
        sort(new BubbleSort2());
        sort(new BubbleSort3());

        sort(new HeapSort1());

        sort(new InsertSort1());
        sort(new InsertSort2());

        sort(new MergeSort1());

        sort(new QuickSort1());
        sort(new QuickSort2());

        sort(new SelectSort1());

        sort(new ShellSort1());
        sort(new ShellSort2());
    }

    private static void sort(IKySort sorter) {
        log("\n\n\n\n\n\n排序开始: " + sorter.getClass().getSimpleName());
        for (int[] actual : demoArr) {
            int[] expected = Arrays.copyOf(actual, actual.length);
            Arrays.sort(expected);
            if (doSort(sorter, expected, actual)) {
                log("成功" + display("", expected));
            } else {
                return;
            }
        }
    }

    private static boolean doSort(IKySort sorter, int[] expected, int[] actual) {
        int[] arr = Arrays.copyOf(actual, actual.length);
        sorter.sort(arr, arr.length);
        if (!Arrays.equals(expected, arr)) {
            log("\n---- " + "排序异常: " + sorter.getClass().getSimpleName() + " ----");
            log(display("expected", expected));
            log(display("arr", arr));
            log(display("actual", arr));
            throw new RuntimeException();
//            return false;
        }
        return true;
    }

    private static String display(String prefix, int[] arr) {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix).append(": ");
        for (int i : arr) {
            builder.append(i).append(" ");
        }
        return builder.toString();
    }

    private static void log(String str) {
        System.out.println(str);
    }

    private static void randomDemoArr() {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int size = ThreadLocalRandom.current().nextInt(1, 30);
            int[] arr = new int[size];
            list.add(arr);
            for (int j = 0; j < size; j++) {
                arr[j] = ThreadLocalRandom.current().nextInt(100);
            }
        }
        demoArr = list.toArray(new int[0][0]);
    }
}
