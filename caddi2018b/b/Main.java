package b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long answer = 0;
        Scanner stdin = new Scanner(System.in);
        long n = stdin.nextLong();
        long h = stdin.nextLong();
        long w = stdin.nextLong();
        for (long i = 0; i < n; i++) {
            long a = stdin.nextLong();
            long b = stdin.nextLong();
            if (h <= a && w <= b) answer++;
        }
        System.out.println(answer);
    }
}
