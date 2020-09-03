package f;

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
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        int q = stdin.nextInt();
        int[] c = stdin.nextIntArray(n);
        for (int i = 0; i < n; i++) c[i]--;

        List<Deque<Integer>> last = new ArrayList<>();
        for (int i = 0; i < n; i++) last.add(new ArrayDeque<>());
        for (int i = 0; i < n; i++) last.get(c[i]).addLast(i+1);

        List<Tuple> tuples = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            Tuple tuple = new Tuple();
            tuple.i = i;
            tuple.l = stdin.nextInt();
            tuple.r = stdin.nextInt();
            tuples.add(tuple);
        }
        tuples.sort(Comparator.<Tuple>comparingInt(t -> t.r).reversed());

        BIT bit = new BIT(n);
        for (Deque<Integer> stack : last) {
            if (!stack.isEmpty()) {
                bit.add(stack.getLast(), 1);
            }
        }

        int ptr = n;
        int[] ans = new int[q];
        for (Tuple tuple : tuples) {
            while (tuple.r < ptr) {
                int color = c[ptr-1];
                bit.add(last.get(color).getLast(), -1);
                last.get(color).removeLast();
                if (!last.get(color).isEmpty()) bit.add(last.get(color).getLast(), 1);
                ptr--;
            }
            ans[tuple.i] = bit.sum(tuple.r) - bit.sum(tuple.l-1);
        }

        Arrays.stream(ans).forEach(stdout::println);
    }

    public class Tuple {
        private int i;
        private int l;
        private int r;
    }

    /**
     * 数列a1, a2, ... anについて
     * - add(i, x) - aiにxを加算
     * - sum(i)    - a1..aiの合計を取得
     */
    public class BIT {

        private int n;
        private int[] tree;

        public BIT(int n) {
            this.n = n;
            this.tree = new int[n+1];
        }

        public int sum(int i) {
            int s = 0;
            while (i > 0) {
                s += this.tree[i];
                i -= i & -i;
            }
            return s;
        }

        public void add(int i, int x) {
            while (i <= this.n) {
                this.tree[i] += x;
                i += i & -i;
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

        public void debug(Object ... objs) {
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