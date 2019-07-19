package c;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = stdin.nextInt();
        
        int l = 0;
        int r = 0;
        long c = 0;
        while (r < n) {
            while (r < n - 1 && a[r] < a[r + 1]) r++;
            
            long len = r - l + 1;
            c += (1 + len) * len / 2;
            
            l = r + 1;
            r = l;
        }
        System.out.println(c);
    }
}
