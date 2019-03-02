package nikkei2019ex.d;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        
        StringBuilder ans = new StringBuilder("1");
        for (int time = 1; time < n; time++) {
            ans.append(0);
        }
        System.out.println(ans.toString());
    }
}
