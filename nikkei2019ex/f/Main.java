package nikkei2019ex.f;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        long p = stdin.nextLong();
        
        long n = 1789997546303L;
        for (int time = 0; time < 1000 - p; time++) {
            n = (n % 2 == 0) ? n / 2  : n * 3 + 1;
        }
        
        System.out.println(n);
    }
}
