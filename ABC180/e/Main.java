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
import java.util.OptionalLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        long[] x = new long[n];
        long[] y = new long[n];
        long[] z = new long[n];
        for (int i = 0; i < n; i++) {
            x[i] = stdin.nextLong();
            y[i] = stdin.nextLong();
            z[i] = stdin.nextLong();
        }

        TSP tsp = new TSP(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tsp.add(i, j, Math.abs(x[j]-x[i]) + Math.abs(y[j]-y[i]) + Math.max(0, z[j]-z[i]));
            }
        }

        stdout.println(tsp.run(0).getAsLong());
    }

    public class TSP {
        private int n;
        private boolean[][] ok;
        private long[][] cost;
        public TSP(int n) {
            this.n = n;
            this.cost = new long[n][n];;
            this.ok = new boolean[n][n];
        }

        public void add(int from, int to, long cost) {
            this.ok[from][to] = true;
            this.cost[from][to] = cost;
        }

        public OptionalLong run(int start) {
            int m = 1 << n;
            long[][] dp = new long[m][n];
            for (long[] row : dp) Arrays.fill(row, Long.MAX_VALUE);
            dp[0][start] = 0;

            for (int bit = 0; bit < m; bit++) {
                for (int i = 0; i < n; i++) {
                    if (dp[bit][i]==Long.MAX_VALUE) continue;
                    for (int j = 0; j < n; j++) {
                        if (!this.ok[i][j]) continue;
                        dp[bit | (1<<j)][j] = Math.min(dp[bit | (1<<j)][j], dp[bit][i] + cost[i][j]);
                    }
                }
            }
            if (dp[m-1][start] == Long.MAX_VALUE) {
                return OptionalLong.empty();
            } else {
                return OptionalLong.of(dp[m-1][start]);
            }
        }
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

        public void println(Object ... objs) {
            String line = Arrays.stream(objs).map(Objects::toString).collect(Collectors.joining(" "));
            stdout.println(line);
        }

        public void printDebug(Object ... objs) {
            String line = Arrays.stream(objs).map(this::deepToString).collect(Collectors.joining(" "));
            stdout.printf("DEBUG: %s%n", line);
            stdout.flush();
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

        private void flush() {
            stdout.flush();
        }
    }
}