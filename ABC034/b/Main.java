package b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        if (n % 2 == 0) {
            System.out.println(n - 1);
        } else {
            System.out.println(n + 1);
        }
    }
}
