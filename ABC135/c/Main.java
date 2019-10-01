package c;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        long[] a = Pattern.compile(" ").splitAsStream(stdin.nextLine()).mapToLong(Long::parseLong).toArray();
        long[] b = Pattern.compile(" ").splitAsStream(stdin.nextLine()).mapToLong(Long::parseLong).toArray();
        long[] c = a.clone();

        for (int i = 0; i < n; i++) {
            if (a[i] < b[i]) {
                b[i] -= a[i];
                a[i] = 0;
                a[i + 1] = Math.max(0, a[i + 1] - b[i]);
            } else {
                a[i] -= b[i];
            }
        }

        long ans = IntStream.range(0, n + 1).mapToLong(i -> c[i] - a[i]).sum();
        System.out.println(ans);
    }

}
