package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        int n = stdin.nextInt();
        String s = stdin.nextToken();

        String t = "b";
        int i = 1;
        while (true) {
            if (s.length() <= t.length()) break;
            if (i % 3 == 0) t = "b" + t + "b";
            if (i % 3 == 1) t = "a" + t + "c";
            if (i % 3 == 2) t = "c" + t + "a";
            i++;
        }

        if (s.equals(t)) {
            System.out.println(i - 1);
        } else {
            System.out.println(-1);
        }
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
