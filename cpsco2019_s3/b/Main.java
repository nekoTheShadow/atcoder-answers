package b;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextInt();
        }
        
        Arrays.sort(a);
        int i = n - 1;
        int c = 0;
        while (m > 0) {
            c++;
            m -= a[i];
            i--;
        }
        
        System.out.println(c);
    }
}
