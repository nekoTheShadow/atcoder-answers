package a;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        String s = stdin.readLine();
        if (s.equals("SUN")) stdout.println(7);
        if (s.equals("MON")) stdout.println(6);
        if (s.equals("TUE")) stdout.println(5);
        if (s.equals("WED")) stdout.println(4);
        if (s.equals("THU")) stdout.println(3);
        if (s.equals("FRI")) stdout.println(2);
        if (s.equals("SAT")) stdout.println(1);
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
