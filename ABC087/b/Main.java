package b;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int a = stdin.nextInt(); // 500
        int b = stdin.nextInt(); // 100
        int c = stdin.nextInt(); // 50
        int x = stdin.nextInt();
        
        int ans = 0;
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {
                for (int k = 0; k <= c; k++) {
                    if (i * 500 + j * 100 + k * 50 == x) ans++;
                }
            }
        }
        
        System.out.println(ans);
    }
}
