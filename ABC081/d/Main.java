package d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextInt();
        }

        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        if (!isPositive(a) && !isNegative(a)) {
            int max = IntStream.range(0, n).boxed().collect(Collectors.maxBy(Comparator.comparing(i -> a[i]))).get().intValue();
            int min = IntStream.range(0, n).boxed().collect(Collectors.minBy(Comparator.comparing(i -> a[i]))).get().intValue();
            if (Math.abs(a[min]) <= Math.abs(a[max])) {
                for (int i = 0; i < n; i++) {
                    x.add(max);
                    y.add(i);
                    a[i] += a[max];
                }
            } else {
                for (int i = 0; i < n; i++) {
                    x.add(min);
                    y.add(i);
                    a[i] += a[min];
                }
            }
        }

        if (isPositive(a)) {
            for (int i = 0; i < n - 1; i++) {
                x.add(i);
                y.add(i + 1);
            }
        }

        if (isNegative(a)) {
            for (int i = n - 1; i > 0; i--) {
                x.add(i);
                y.add(i - 1);
            }
        }

        System.out.println(x.size());
        for (int i = 0; i < x.size(); i++) {
            System.out.printf("%d %d%n", x.get(i) + 1, y.get(i) + 1);
        }
    }

    public static boolean isPositive(int[] a) {
        return Arrays.stream(a).allMatch(i -> i >= 0);
    }

    public static boolean isNegative(int[] a) {
        return Arrays.stream(a).allMatch(i -> i <= 0);
    }
}
