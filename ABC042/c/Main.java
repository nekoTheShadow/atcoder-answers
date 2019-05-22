package c;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        int[] d = new int[k];
        for (int i = 0; i < k; i++) d[i] = stdin.nextInt();
        
        while (true) {
            int x = n;
            Set<Integer> digits = new HashSet<>();
            while (x > 0) {
                digits.add(x % 10);
                x /= 10;
            }
            
            if (Arrays.stream(d).noneMatch(digits::contains)) {
                System.out.println(n);
                System.exit(0);
            }
            
            n++;
        }
    }

}
