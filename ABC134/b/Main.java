package b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int d = stdin.nextInt();
        
        int div = n / (d * 2 + 1);
        int mod = n % (d * 2 + 1);
        if (mod == 0) {
            System.out.println(div);
        } else {
            System.out.println(div + 1);
        }
    }

}
