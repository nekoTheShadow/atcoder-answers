package c;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] s = new int[n];
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = stdin.nextInt();
            t[i] = stdin.nextInt();
        }
        
        int m = Arrays.stream(t).max().getAsInt() + 1;
        int[] imos = new int[m];
        for (int i = 0; i < n; i++) {
            imos[s[i]]++;
            imos[t[i]]--;
        }
        
        for (int i = 0; i < m - 1; i++) {
            imos[i + 1] += imos[i];
        }
        
        int c = 0;
        for (int i = 0; i < m - 1; i++) {
            if (imos[i] == 0 && imos[i + 1] > 0) {
                c++;
            }
        }
        
        System.out.println(c);
    }
}
