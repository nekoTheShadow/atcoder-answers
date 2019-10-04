package a;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = (n % 2 == 0) ? n / 2 : n / 2 + 1;
        System.out.println((double)m / n);
    }
}
