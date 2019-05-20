package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String[] line = stdin.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        
        double sum = 0;
        for (int dice = 1; dice <= n; dice++) {
            int i = dice;
            int c = 1;
            while (i < k) {
                c *= 2;
                i *= 2;
            }
            sum += 1.0d / (n * c);
        }
        
        System.out.println(sum);
    }
}
