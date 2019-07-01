package b;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        long ans = IntStream.rangeClosed(a, b).filter(Main::isPalindromic).count();
        System.out.println(ans);
    }
    
    public static boolean isPalindromic(int x) {
        List<Integer> digits = new ArrayList<>();
        while (x > 0) {
            digits.add(x % 10);
            x /= 10;
        }
        
        int len = digits.size();
        return IntStream.range(0, len).allMatch(i -> digits.get(i).equals(digits.get(len - i - 1)));
    }
}
