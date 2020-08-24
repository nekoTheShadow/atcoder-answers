package d;
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

        int n = stdin.nextInt();
        Ruiseki2Builder b = new Ruiseki2Builder(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b.set(i, j, stdin.nextLong());
            }
        }
        Ruiseki2Result r = b.build();

        long[] d = new long[n*n+1];
        for (int x1 = 0; x1 <= n; x1++) {
            for (int y1 = 0; y1 <= n; y1++) {
                for (int x2 = 0; x2 <= n; x2++) {
                    for (int y2 = 0; y2 <= n; y2++) {
                        int k = Math.abs(x1-x2) * Math.abs(y1-y2);
                        long v = r.sum(x1, y1, x2, y2);
                        d[k] = Math.max(d[k], v);
                    }
                }

            }
        }

        for (int i = 1; i < d.length; i++) {
            d[i] = Math.max(d[i], d[i-1]);
        }

        int q = stdin.nextInt();
        for (int i = 0; i < q; i++) {
            int p = stdin.nextInt();
            stdout.println(d[p]);
        }
    }


    public class Ruiseki2Builder {
        private int h;
        private int w;
        private long[][] a;

        public Ruiseki2Builder(int h, int w) {
            this.h = h;
            this.w = w;
            this.a = new long[h][w];
        }

        public void set(int x, int y, long v) {
            this.a[x][y] = v;
        }

        public Ruiseki2Result build() {
            long[][] s  = new long[h+1][w+1];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    s[i+1][j+1] = s[i][j+1] + s[i+1][j] - s[i][j] + a[i][j];
                }
            }
            return new Ruiseki2Result(s);
        }
    }


    public class Ruiseki2Result {
        private long[][] s;

        public Ruiseki2Result(long[][] s) {
            this.s = s;
        }

        // クエリ [x1, x2) × [y1, y2) の長方形区域の和
        public long sum(int x1, int y1, int x2, int y2) {
            return s[x2][y2] - s[x1][y2] - s[x2][y1] + s[x1][y1];
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