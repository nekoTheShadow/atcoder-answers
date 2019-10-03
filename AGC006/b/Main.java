package b;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int x = stdin.nextInt();

        if (x == 1 || x == 2 * n - 1) {
            System.out.println("No");
            System.exit(0);
        }

        List<Integer> digits = IntStream.range(2, 2 * n - 1).filter(v -> v != x).boxed().collect(Collectors.toList());


        int len = digits.size();
        int mid = len / 2;

        System.out.println("Yes");
        for (int i = 0; i < mid; i++) System.out.println(digits.get(i));
        System.out.println(1);
        System.out.println(x);
        System.out.println(2 * n - 1);
        for (int i = mid; i < len; i++) System.out.println(digits.get(i));
    }

}
