package b;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();

        int[] c = new int[n];
        for (int i = 0; i < m; i++) {
            int a = stdin.nextInt() - 1;
            int b = stdin.nextInt() - 1;
            c[a] += 1;
            c[b] += 1;
        }

        String answer = Arrays.stream(c).allMatch(v -> v % 2 == 0) ? "YES" : "NO";
        System.out.println(answer);
    }
}
