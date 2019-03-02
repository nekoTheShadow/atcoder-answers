package nikkei2019ex.h;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        long n = stdin.nextLong();
        
        boolean[] win = {false, true, false, true, false, true, false, true, true};
        int mod = (int) (n % 9);
        String ans = win[mod] ? "Win" : "Lose";
        System.out.println(ans);
    }
}
