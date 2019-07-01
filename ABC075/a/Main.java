package a;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        int c = stdin.nextInt();
        
        if (b == c) System.out.println(a);
        if (c == a) System.out.println(b);
        if (a == b) System.out.println(c);
    }
}