package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        String x = stdin.nextToken();
        String ans = ok(x) ? "YES" : "NO";
        System.out.println(ans);
    }
    
    private static boolean ok(String s) {
        if (s.isEmpty()) {
            return true;
        }
        
        String[] suffixes = {"ch", "o", "k", "u"};
        for (String suffix : suffixes) {
            if (s.endsWith(suffix)) {
                String t = s.substring(0, s.length() - suffix.length());
                return ok(t);
            }
        }
        return false;
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
