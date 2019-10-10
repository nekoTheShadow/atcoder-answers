package c;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(stdin.nextLine());

        int b = Arrays.stream(a).reduce((x, y) -> x ^ y).getAsInt();
        int ans = 0;
        for (int i = 35; i >= 0; i--) {
            if ((b & (1 << i)) == 0) continue;
            for (int j = 0; j < n; j++) {
                int k = a[j] ^ (a[j] - 1);
                if (k == (1 << (i + 1)) - 1) {
                    ans++;
                    b ^= k;
                    break;
                }
            }
        }

        System.out.println(b == 0 ? ans : -1);
    }
}
