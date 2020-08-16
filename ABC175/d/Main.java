package d;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        int[] p = stdin.nextIntArray(n);
        long[] c = stdin.nextLongArray(n);

        for (int i = 0; i < n; i++) {
            p[i]--;
        }

        List<Long> answers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> ptrs = new ArrayList<>();
            ptrs.add(i);
            while (true) {
                int current = ptrs.get(ptrs.size()-1);
                int next = p[current];
                if (next == ptrs.get(0)) {
                    break;
                }
                ptrs.add(next);
            }
            ptrs.add(ptrs.remove(0));

            List<Long> sums = new ArrayList<>();
            sums.add(c[ptrs.get(0)]);
            for (int x = 1; x < ptrs.size(); x++) {
                sums.add(sums.get(sums.size()-1)+c[ptrs.get(x)]);
            }

            long ans = 0;
            if (k <= sums.size()) {
                ans = Collections.max(sums.subList(0, k));
            } else {
                if (sums.get(sums.size()-1) > 0) {
                    long score1 = sums.get(sums.size()-1) * (k/sums.size()-1);
                    score1 += Collections.max(sums);

                    long score2 = sums.get(sums.size()-1) * (k/sums.size());
                    int mod = k % sums.size();
                    if (mod != 0) {
                        score2 += Math.max(Collections.max(sums.subList(0, mod)), 0);
                    }
                    ans = Math.max(score1, score2);
                } else {
                    ans = Collections.max(sums);
                }
            }
            answers.add(ans);
        }
        stdout.println(Collections.max(answers));
    }

    private static final Stdin stdin = new Stdin();
    private static final Stdout stdout = new Stdout();

    public static void main(String[] args) {
        try {
            new Main().exec();
        } finally {
            stdout.flush();
        }
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
            try {
                if (tokens.isEmpty()) {
                    String line = stdin.readLine();
                    if (line == null) {
                        throw new UncheckedIOException(new EOFException());
                    }
                    delim.splitAsStream(line).forEach(tokens::addLast);
                }
                return tokens.pollFirst();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        public int nextInt() {
            return Integer.parseInt(nextString());
        }

        public double nextDouble() {
            return Double.parseDouble(nextString());
        }

        public long nextLong() {
            return Long.parseLong(nextString());
        }

        public String[] nextStringArray(int n) {
            String[] a = new String[n];
            for (int i = 0; i < n; i++) a[i] = nextString();
            return a;
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        public double[] nextDoubleArray(int n) {
            double[] a = new double[n];
            for (int i = 0; i < n; i++) a[i] = nextDouble();
            return a;
        }

        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }
    }

    public static class Stdout {
        private PrintWriter stdout;

        public Stdout() {
            stdout =  new PrintWriter(System.out, false);
        }

        public void printf(String format, Object ... args) {
            String line = String.format(format, args);
            if (line.endsWith(System.lineSeparator())) {
                stdout.print(line);
            } else {
                stdout.println(line);
            }
        }

        public void println(Object ... o) {
            String line = Arrays.stream(o).map(Objects::toString).collect(Collectors.joining(" "));
            stdout.println(line);
        }

        public void debug(Object ... objs) {
            String line = Arrays.stream(objs).map(this::deepToString).collect(Collectors.joining(" "));
            stdout.printf("DEBUG: %s%n", line);
        }

        private String deepToString(Object o) {
            if (o == null) {
                return "null";
            }

            // 配列の場合
            if (o.getClass().isArray()) {
                int len = Array.getLength(o);
                String[] tokens = new String[len];
                for (int i = 0; i < len; i++) {
                    tokens[i] = deepToString(Array.get(o, i));
                }
                return "{" + String.join(",", tokens) + "}";
            }

            return Objects.toString(o);
        }

        public void flush() {
            stdout.flush();
        }
    }
}