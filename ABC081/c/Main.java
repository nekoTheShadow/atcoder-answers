package c;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = stdin.nextInt();

        Map<Integer, Long> counter =  Arrays.stream(a).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Long> vals = counter.values().stream().sorted().collect(Collectors.toList());

        int sum = 0;
        for (int i = 0; i < vals.size() - k; i++) {
            sum += vals.get(i);
        }

        System.out.println(sum);
    }

}
