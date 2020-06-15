package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {
    public void exec(Stdin stdin, PrintWriter stdout) {
        int n = stdin.readInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.readInt();
        }

        Map<Integer, Integer> s = new HashMap<>();
        for (int v : a) s.put(v, s.getOrDefault(v, 0) + 1);

        int c = 0;
        for (int v : a) {
            List<Integer> factors = new ArrayList<>();
            for (int i = 1; i * i <= v; i++) {
                if (v % i == 0) {
                    factors.add(i);
                    factors.add(v / i);
                }
            }

            boolean ok = true;
            for (int factor : factors) {
                if (factor == v) {
                    if (s.get(factor) > 1) ok = false;
                } else {
                    if (s.containsKey(factor)) ok = false;
                }
            }
            if (ok) c++;
        }

        stdout.println(c);
    }

    public static void main(String[] args) {
        Stdin stdin = new Stdin();
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().exec(stdin, stdout);
        stdout.flush();
    }


    public static class Stdin {
        private BufferedReader stdin;
        private Deque<String> tokens;
        private Pattern delim;

        public Stdin() {
            stdin = new BufferedReader(new InputStreamReader(System.in));
            tokens = new ArrayDeque<>();
            delim = Pattern.compile(" ");
        }

        public String nextString() {
            if (tokens.isEmpty()) {
                delim.splitAsStream(readLine()).forEach(tokens::addLast);
            }
            return tokens.pollFirst();
        }

        public int readInt() {
            return Integer.parseInt(nextString());
        }

        private String readLine() {
            try {
                return stdin.readLine();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }
}
