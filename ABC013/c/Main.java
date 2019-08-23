package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    
    // H 最初の満腹度
    // N
    // 普通の食事  A円出費 満腹度B
    // 質素な食事  C円出費 満腹度D
    // 何も食べない 満腹度E
    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        long n = stdin.nextLong();
        long h = stdin.nextLong();
        long a = stdin.nextLong();
        long b = stdin.nextLong();
        long c = stdin.nextLong();
        long d = stdin.nextLong();
        long e = stdin.nextLong();
        
        long answer = Long.MAX_VALUE;
        for (long i = 0; i <= n; i++) {
            long numer = e * (n - i) - h - b * i;
            if (numer > 0) {
                long denom = d + e;
                long j = numer / denom + 1;
                answer = Math.min(answer, a * i + c * j);
            } else {
                answer = Math.min(answer, a * i);
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
