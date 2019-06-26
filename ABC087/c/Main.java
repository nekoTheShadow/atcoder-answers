package c;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[][] a = new int[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = stdin.nextInt();
            }
        }
        
        int[][] dp = new int[2][n];
        dp[0][0] = a[0][0];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < 2) {
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] + a[i + 1][j]);
                }
                
                if (j + 1 < n) {
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j] + a[i][j + 1]);
                }
            }
        }
        
        System.out.println(dp[1][n - 1]);
    }
}
