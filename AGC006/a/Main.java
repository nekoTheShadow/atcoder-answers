package a;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        String s = stdin.nextLine();
        String t = stdin.nextLine();

        for (int i = 0; i < n; i++) {
            if (s.substring(i).equals(t.substring(0, n - i))) {
                System.out.println(i + n);
                System.exit(0);
            }
        }
        System.out.println(n * 2);
    }

}
