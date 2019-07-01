package a;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int x = stdin.nextInt();
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        
        int ans = (x - a) % b;
        System.out.println(ans);
    }
}
