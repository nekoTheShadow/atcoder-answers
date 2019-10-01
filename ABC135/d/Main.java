package d;

import java.util.Scanner;

public class Main {

    //	a / 13 = b ... c
    //	<=> a = 13 * b + c
    //	---> 10 * a + d = (13 * b + c) * 10 + d
    //
    //	10 * a + dを13でわったあまり
    //		<=> (13 * b + c) * 10 + dを13でわったあまり
    //		<=> 10 * c + dを13でわったあまり
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String s = stdin.nextLine();

        long mod = 1_000_000_007;
        long[][] dp = new long[s.length() + 1][13];
        dp[0][0] = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 13; j++) {
                if (s.charAt(i) == '?') {
                    for (int d = 0; d <= 9; d++) {
                        dp[i + 1][(10 * j + d) % 13] += dp[i][j];
                        dp[i + 1][(10 * j + d) % 13] %= mod;
                    }
                } else {
                    int x = s.charAt(i) - '0';
                    dp[i + 1][(10 * j + x) % 13] += dp[i][j];
                    dp[i + 1][(10 * j + x) % 13] %= mod;
                }
            }
        }

        System.out.println(dp[s.length()][5]);
    }

}
