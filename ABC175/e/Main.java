package e;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public void exec() {
        int R = stdin.nextInt();
        int C = stdin.nextInt();
        int K = stdin.nextInt();
        long[][] V = new long[R][C];
        for (int x = 0; x < K; x++) {
            int r = stdin.nextInt()-1;
            int c = stdin.nextInt()-1;
            long v = stdin.nextLong();
            V[r][c] = v;
        }

        long[][][] dp = new long[R][C][4];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dp[i][j][1] = Math.max(dp[i][j][1], V[i][j]);

                for (int k = 0; k < 4; k++) {
                    if (i+1 < R) {
                        dp[i+1][j][0] = Math.max(dp[i+1][j][0], dp[i][j][k]);
                        if(V[i+1][j] > 0) dp[i+1][j][1] = Math.max(dp[i+1][j][1], dp[i][j][k]+V[i+1][j]);
                    }

                    if (j+1 < C) {
                        dp[i][j+1][k] = Math.max(dp[i][j+1][k], dp[i][j][k]);
                        if (V[i][j+1] > 0 && k < 3) dp[i][j+1][k+1] = Math.max(dp[i][j+1][k+1], dp[i][j][k]+V[i][j+1]);
                    }
                }
            }
        }
        stdout.println(Arrays.stream(dp[R-1][C-1]).max().getAsLong());
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