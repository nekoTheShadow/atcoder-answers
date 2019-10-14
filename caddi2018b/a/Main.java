package a;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String s = stdin.nextLine();
        long ans = s.chars().filter(c -> c == '2').count();
        System.out.println(ans);
    }
}
