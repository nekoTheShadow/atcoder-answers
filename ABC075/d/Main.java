package d;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        long[] x = new long[n];
        long[] y = new long[n];
        for (int i = 0; i < n; i++) {
            x[i] = stdin.nextLong();
            y[i] = stdin.nextLong();
        }

        long ans = Long.MAX_VALUE;
        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = 0; c < n; c++) {
                    for (int d = c + 1; d < n; d++) {
                        long x1 = Math.min(x[a], x[b]);
                        long x2 = Math.max(x[a], x[b]);
                        long y1 = Math.min(y[c], y[d]);
                        long y2 = Math.max(y[c], y[d]);
                        
                        int count = 0;
                        for (int i = 0; i < n; i++) {
                            if (x1 <= x[i] && x[i] <= x2 && y1 <= y[i] && y[i] <= y2) {
                                count++;
                            }
                        }

                        if (k <= count) {
                            ans = Math.min(ans, (x2 - x1) * (y2 - y1));
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }
}