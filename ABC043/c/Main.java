package c;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        
        int n = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextInt();
        }
        
        int ans = IntStream.rangeClosed(-100, 100)
                           .map(x -> Arrays.stream(a).map(v -> (x - v) * (x - v)).sum())
                           .min()
                           .getAsInt();
        System.out.println(ans);
    }
}
