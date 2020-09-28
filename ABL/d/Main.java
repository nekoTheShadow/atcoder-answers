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
import java.util.function.LongBinaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        int k = stdin.nextInt();

        int max = 510000;
        Segtree seg = new Segtree(max, Long::max, 0);
        for (int i = 0; i < n; i++) {
            int a = stdin.nextInt();
            int left = Math.max(0, a-k);
            int right = Math.min(a+k+1, max);
            seg.update(a, seg.get(left, right) + 1);
        }

        stdout.println(seg.get(0, max));
    }

    /**
     * https://tsutaj.hatenablog.com/entry/2017/03/29/204841
     * https://atcoder.github.io/ac-library/document_ja/segtree.html
     */
    public class Segtree {
        private long[] node;
        private int n;
        private LongBinaryOperator op;
        private long e;


        public Segtree(int size, LongBinaryOperator op, long e) {
            this.n = 1;
            while (this.n < size) this.n *= 2;

            this.node = new long[2*this.n-1];
            Arrays.fill(this.node, e);
            this.op = op;
            this.e = e;
        }

        public void update(int x, long v) {
            x += (n - 1);
            node[x] = v;
            while(x > 0) {
                x = (x - 1) / 2;
                node[x] = op.applyAsLong(node[2*x+1], node[2*x+2]);
            }
        }

        // 要求区間 [a, b) 中の要素の最小値を答える
        public long get(int a, int b) {
            return get(a, b, 0, 0, n);
        }

        private long get(int a, int b, int k, int l, int r) {
            if(r <= a || b <= l) return e;
            if(a <= l && r <= b) return node[k];

            long vl = get(a, b, 2*k+1, l, (l+r)/2);
            long vr = get(a, b, 2*k+2, (l+r)/2, r);
            return op.applyAsLong(vl, vr);
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