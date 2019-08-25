package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int[] l = new int[n];
        int[] r = new int[n];
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = stdin.nextInt() - 1;
            r[i] = stdin.nextInt() - 1;
            s[i] = stdin.nextInt();
        }
        
        int[] imos = new int[m + 1];
        for (int i = 0; i < n; i++) {
            imos[l[i]] += s[i];
            imos[r[i] + 1] -= s[i];
        }
        for (int i = 0; i < m; i++) {
            imos[i + 1] += imos[i];
        }
        
        int a = Arrays.stream(s).sum();
        int b = IntStream.range(0, m).map(i -> imos[i]).min().getAsInt();
        System.out.println(a - b);
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

