package d;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         Scanner stdin = new Scanner(System.in);
         int n = Integer.parseInt(stdin.nextLine());
         String s = stdin.nextLine();
         
         boolean a = s.startsWith("R");
         boolean b = s.endsWith("B");
         boolean c = !s.contains("RB");
         boolean d = !s.contains("GG");
         if (a && b && c && d) {
             System.out.println("Yes");
         } else {
              System.out.println("No");
         }
    }
}
