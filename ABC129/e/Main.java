package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        
        char[] l = stdin.nextToken().toCharArray();
        int n = l.length;
        long m = 1000000007;
        long[][] dp = new long[n + 1][2];
        dp[0][1] = 1;
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] += dp[i][0] * 3;
            if (l[i] == '0') {
                dp[i + 1][1] += dp[i][1];
            } else {
                dp[i + 1][0] += dp[i][1];
                dp[i + 1][1] += dp[i][1] * 2;
            }
            
            dp[i + 1][0] %= m;
            dp[i + 1][1] %= m;
        }
        
        long ans = (dp[n][0] + dp[n][1]) % m;
        System.out.println(ans);
    }
    
    private static final class Stdin {
        private BufferedReader stdin;
        private ArrayDeque<String> tokens;
        
        public Stdin() {
            this.stdin = new BufferedReader(new InputStreamReader(System.in));
            this.tokens = new ArrayDeque<>();
        }
        
        public String nextToken() throws IOException {
            if (tokens.isEmpty()) {
                for (String token : stdin.readLine().split(" ")) {
                    tokens.add(token);
                }
            }
            return tokens.poll();
        }
        
        public int nextInt() throws NumberFormatException, IOException {
            return Integer.parseInt(nextToken());
        }
        
        public int nextLong() throws NumberFormatException, IOException {
            return Integer.parseInt(nextToken());
        }
    }
}
