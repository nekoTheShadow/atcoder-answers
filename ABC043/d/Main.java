package d;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String s = stdin.nextLine();
        
        
        for (int i = 0; i < s.length(); i++) {
            // XXパターンを探す。
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                System.out.printf("%d %d%n", i + 1, i + 2);
                System.exit(0);
            }
            
            // XYXパターンを探す
            if (i + 2 < s.length() && s.charAt(i) == s.charAt(i + 2)) {
                System.out.printf("%d %d%n", i + 1, i + 3);
                System.exit(0);
            }
        }
        
        System.out.printf("%d %d%n", -1, -1);
    }
}
