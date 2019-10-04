package b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int h = stdin.nextInt();
            if (k <= h) ans++;
        }
        System.out.println(ans);
    }
}
