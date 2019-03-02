package nikkei2019ex.b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        System.out.println(isqrt(n));
    }
    
    public static int isqrt(int n) {
        int x = n;
        int y = (x + 1) / 2;
        while (y < x) {
            x = y;
            y = (x + n / x) / 2;
        }
        return x;
    }
}
