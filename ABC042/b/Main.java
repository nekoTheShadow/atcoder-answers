package b;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        int c = stdin.nextInt();
        
        int[] d = IntStream.of(a, b, c).sorted().toArray();
        
        if (d[0] == 5 && d[1] == 5 && d[2] == 7) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
