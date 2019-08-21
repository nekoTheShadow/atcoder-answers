package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.stream.LongStream;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int[] s = new int[n];
        int[] t = new int[m];
        for (int i = 0; i < n; i++) s[i] = stdin.nextInt();
        for (int i = 0; i < m; i++) t[i] = stdin.nextInt();

        long mod = 1000000007L;
        long[][] dp = new long[n + 1][m + 1];
        long[][] sm = new long[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 0; i <= n; i++) sm[i][0] = 1;
        for (int i = 0; i <= m; i++) sm[0][i] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s[i - 1] == t[j - 1]) {
                    dp[i][j] = sm[i - 1][j - 1];
                }

                // sm[i][j] = (sm[i - 1][j] + sm[i][j - 1] - sm[i - 1][j - 1] + dp[i][j]) % mod;
                sm[i][j] = LongStream.of(sm[i - 1][j], sm[i][j - 1], -1 * sm[i - 1][j - 1], dp[i][j])
                                     .mapToObj(BigInteger::valueOf)
                                     .reduce(BigInteger.ZERO, (x, y) -> x.add(y))
                                     .mod(BigInteger.valueOf(mod))
                                     .longValue();
            }
        }
        System.out.println(sm[n][m]);
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
