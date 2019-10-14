package d;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(stdin.nextLine());
        String answer = Arrays.stream(a).allMatch(v -> v % 2 == 0) ? "second" : "first";
        System.out.println(answer);
    }
}
