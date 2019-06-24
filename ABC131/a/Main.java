import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        char[] s = stdin.next().toCharArray();
        if (s[0] == s[1] || s[1] == s[2] || s[2] == s[3]) {
            System.out.println("Bad");
        } else {
            System.out.println("Good");
        }
    }
}