package b;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        String s = stdin.readLine();

        int len = s.length();
        char[] t = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] u = new char[len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < t.length; j++) {
                if (s.charAt(i) == t[j]) {
                    u[i] = t[j + n];
                    break;
                }
            }
        }

        stdout.println(new String(u));
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
