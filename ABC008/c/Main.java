package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        int n = stdin.nextInt();
        int[] c = new int[n];
        for (int i = 0; i < n; i++) c[i] = stdin.nextInt();
        
        double answer = 0;
        for (int i = 0; i < n; i++) {
            int x = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (c[i] % c[j] == 0) x++;
            }
            if (x % 2 == 0) {
                answer += 1.0 * (x + 2) / (2 * x + 2);
            } else {
                answer += 0.5;
            }
        }
        System.out.println(answer);
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
