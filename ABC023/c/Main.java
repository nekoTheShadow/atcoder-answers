package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        int R = stdin.nextInt();
        int C = stdin.nextInt();
        int K = stdin.nextInt();
        int N = stdin.nextInt();
        int[] r = new int[N];
        int[] c = new int[N];
        for (int i = 0; i < N; i++) {
            r[i] = stdin.nextInt() - 1;
            c[i] = stdin.nextInt() - 1;
        }

        int[] countR = new int[R];
        int[] countC = new int[C];
        for (int i = 0; i < N; i++) {
            countR[r[i]]++;
            countC[c[i]]++;
        }

        int maxR = Arrays.stream(countR).max().getAsInt();
        int maxC = Arrays.stream(countC).max().getAsInt();
        long[] groupR = new long[maxR + 1];
        long[] groupC = new long[maxC + 1];
        for (int i = 0; i < R; i++) groupR[countR[i]]++;
        for (int i = 0; i < C; i++) groupC[countC[i]]++;

        long ans = 0;
        for (int i = 0; i <= maxR; i++) {
            int j = K - i;
            if (0 <= j && j <= maxC) ans += groupR[i] * groupC[j];
        }
        for (int i = 0; i < N; i++) {
            if (countR[r[i]] + countC[c[i]] == K) ans--;
            if (countR[r[i]] + countC[c[i]] == K + 1) ans++;
        }
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
