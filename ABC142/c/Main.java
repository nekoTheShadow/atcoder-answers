package c;

import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        int[] a = Pattern.compile(" ").splitAsStream(stdin.nextLine()).mapToInt(Integer::parseInt).toArray();
        String ans = IntStream.range(0, n)
            .boxed()
            .sorted(Comparator.comparing(i -> a[i]))
            .map(i -> Integer.toString(i + 1))
            .collect(Collectors.joining(" "));
        System.out.println(ans);
    }
}
