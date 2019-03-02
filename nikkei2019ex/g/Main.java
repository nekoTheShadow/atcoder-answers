package nikkei2019ex.g;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String s = stdin.nextLine();
        
        long[] counts = new long[26]; // filled zero
        for (char ch : s.toCharArray()) counts[ch - 'a']++;
        
        long maxlen = 0;
        long odd = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] % 2 == 0) {
                maxlen += counts[i];
            } else {
                maxlen += counts[i] - 1;
                odd++;
            }
        }
        
        if (odd != 0) {
            maxlen++;
            odd--;
        }
        
        long score = maxlen * maxlen + odd;
        System.out.println(score);
    }
}
