package a;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int a = stdin.nextInt();
        
        int min = (a % 3 == 0) ? a / 3 : a / 3 + 1;
        int max = Math.min(n / 3, a);
        
        System.out.printf("%d %d%n", min, max);
    }
}
